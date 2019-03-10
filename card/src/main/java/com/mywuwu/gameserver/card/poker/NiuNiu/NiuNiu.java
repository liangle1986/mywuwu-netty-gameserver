package com.mywuwu.gameserver.card.poker.NiuNiu;

import com.mywuwu.gameserver.card.poker.Poker;
import com.mywuwu.gameserver.card.poker.PokerNumber;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @program mywuwu-netty-gameserver
 * @description: 牛牛核心算法
 * @author: lianglele
 * @create: 2019/03/07 20:13
 */
public class NiuNiu implements Comparable<NiuNiu> {


    private List<Poker> cards = new ArrayList<>();

    public NiuNiu(Poker card1, Poker card2, Poker card3, Poker card4, Poker card5) {
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.sort(Comparator.comparing(Poker::getNumber));
    }

    /**
     * 获取牛牛对象
     *
     * @return
     */
    private NiuNiuType getNiuNiuType() {
        List<Poker> lessThanTen = new ArrayList<Poker>();
        for (Poker card : cards) {
            if (card.getNumber().ordinal() < PokerNumber.Ten.ordinal() && card.getNumber().ordinal() >= PokerNumber.two.ordinal() || card.getNumber().equals(PokerNumber.A)) {
                lessThanTen.add(card);
            }
        }
        // 获取牛数
        int number = this.getNiuNumber(lessThanTen);
        if (number > 0) {
            if (this.ShunZiNiu() > 0) {
                number = this.ShunZiNiu();
            }
        }
        //获取炸弹牛
        if (this.ZhaDanNiu() > 0) {
            number = this.ZhaDanNiu();
        }
        return NiuNiuType.getNiuNiuType(number);
    }

    /**
     * 获取牛的数字 11 5花牛
     */
    private int getNiuNumber(List<Poker> lessThanTen) {
        // 牛牛
        if (lessThanTen.size() == 0) {
            return 10;
        }
        //获取一张的牛数
        if (lessThanTen.size() == 1) {
            return getOneCardNum(lessThanTen.get(0));
        }
        if (lessThanTen.size() == 2) {
            return getNiuFrom2(lessThanTen);
        }
        if (lessThanTen.size() == 3) {
            return getNiuFrom3(lessThanTen);
        }
        if (lessThanTen.size() == 4) {
            return getNiuFrom4(lessThanTen);
        }
        if (lessThanTen.size() == 5) {
            return getNiuFrom5(lessThanTen);
        }
        return -1;

    }

    /**
     * 取一张的值
     */
    private int getOneCardNum(Poker card) {
        return PokerNumber.getNumberPoker(card.getNumber());
    }

    /**
     * 两张牌算牛几
     *
     * @param pokerList 两张
     * @return
     */
    private int getNiuFrom2(List<Poker> pokerList) {
        if (pokerList != null && pokerList.size() == 2) {
            int number = (this.getOneCardNum(pokerList.get(0)) + this.getOneCardNum(pokerList.get(1))) % 10;
            if (number == 0) {
                return 10;
            }
            return number;
        }
        return -1;
    }


    /**
     * 三张牌算牛几
     *
     * @param pokerList 三张
     * @return
     */
    private int getNiuFrom3(List<Poker> pokerList) {
        if (pokerList.size() == 3) {
            // 3
            int niuNumber = (getOneCardNum(pokerList.get(0)) + getOneCardNum(pokerList.get(1)) + getOneCardNum(pokerList.get(2))) % 10;
            if (niuNumber == 0) {
                return 10;
            }

            // 2+1
            List<List<Poker>> subListCards2 = this.getAllSubList(pokerList, 2);
            for (List<Poker> listCard : subListCards2) {
                niuNumber = getNiuFrom2(listCard);
                if (niuNumber == 10) {
                    List<Poker> subList = this.getSubList(pokerList, listCard);
                    return getOneCardNum(subList.get(0));
                }
            }
            return -1;

        } else {
            return -1;
        }
    }


    /**
     * 从4账牌里面找牛  3 + 1 2 + 2
     */
    private int getNiuFrom4(List<Poker> lessThanTen) {
        if (lessThanTen.size() == 4) {
            // 3 + 1
            List<List<Poker>> subListCards3 = this.getAllSubList(lessThanTen, 3);
            for (List<Poker> listCard : subListCards3) {
                int niuNumber = getNiuFrom3(listCard);
                if (niuNumber == 10) {
                    Poker card = this.getSubList(lessThanTen, listCard).get(0);
                    return getOneCardNum(card);
                }
            }

            // 2 + 2
            List<List<Poker>> subListCards2 = this.getAllSubList(lessThanTen, 2);
            for (List<Poker> listCard : subListCards2) {
                int niuNumber = getNiuFrom2(listCard);
                if (niuNumber == 10) {
                    List<Poker> subList = this.getSubList(lessThanTen, listCard);
                    return getNiuFrom2(subList);
                }
            }

            return -1;
        } else {
            return -1;
        }
    }

    /**
     * 从五张里面找牛 3+2 2+2+1
     */
    private int getNiuFrom5(List<Poker> lessThanTen) {
        if (lessThanTen.size() == 5) {
            // 3+2
            List<List<Poker>> subListCards3 = this.getAllSubList(lessThanTen, 3);
            for (List<Poker> listCard : subListCards3) {
                int niuNumber = getNiuFrom3(listCard);
                if (niuNumber == 10) {
                    List<Poker> subList = this.getSubList(lessThanTen, listCard);
                    return getNiuFrom2(subList);
                }
            }

            // 2+2+1
            List<List<Poker>> subListCards2 = this.getAllSubList(lessThanTen, 2);
            for (List<Poker> listCard : subListCards2) {
                int niuNumber = getNiuFrom2(listCard);
                if (niuNumber == 10) {
                    List<Poker> subList = this.getSubList(lessThanTen, listCard);
                    niuNumber = getNiuFrom2(subList);
                    if (niuNumber == 10) {
                        Poker card = this.getSubList(lessThanTen, listCard).get(0);
                        return getOneCardNum(card);
                    }
                }
            }
            return -1;
        } else {
            return -1;
        }
    }

    /**
     * 顺子牛
     *
     * @return
     */
    private int ShunZiNiu() {
        //先排序
        cards.sort(Comparator.comparing(Poker::getNumber));
        Poker poker = cards.get(0);
        int number = 11;
        for (int i = 1; i < cards.size(); i++) {
            if (poker.getNumber().ordinal() + 1 != cards.get(i).getNumber().ordinal()) {
                number = -1;
                break;
            }
        }
        return number;
    }

    /**
     * 炸弹牛
     *
     * @return
     */
    private int ZhaDanNiu() {
        //先排序
        cards.sort(Comparator.comparing(Poker::getNumber));

        if (cards.get(0).getNumber().equals(cards.get(1).getNumber())) {
            if (cards.get(0).getNumber().equals(cards.get(2).getNumber())) {
                if (cards.get(0).getNumber().equals(cards.get(3).getNumber())) {
                    return 12;
                }
            }
        } else if (cards.get(0).getNumber().equals(cards.get(2).getNumber())) {
            if (cards.get(0).getNumber().equals(cards.get(3).getNumber())) {
                if (cards.get(0).getNumber().equals(cards.get(4).getNumber())) {
                    return 12;
                }
            }
        } else if (cards.get(1).getNumber().equals(cards.get(2).getNumber())) {
            if (cards.get(1).getNumber().equals(cards.get(3).getNumber())) {
                if (cards.get(1).getNumber().equals(cards.get(4).getNumber())) {
                    return 12;
                }
            }
        }
        return -1;
    }


    @Override
    public int compareTo(NiuNiu o) {
        if (this.cards.size() != o.cards.size()) {
            throw new RuntimeException("两手牌的数量不一样不能比较");
        }
        this.cards.sort(Comparator.comparing(Poker::getNumber));
        o.cards.sort(Comparator.comparing(Poker::getNumber));

        if (this.getNiuNiuType().ordinal() > o.getNiuNiuType().ordinal()) {
            return 1;
        } else if (this.getNiuNiuType().ordinal() == o.getNiuNiuType().ordinal()) {
            // 先比数字
            if (this.commCompareToNoColor(o) == 0) {
                //在比花色
                if (this.compartToColor(o) > 0) {
                    return -1;
                } else {
                    return 1;
                }
            } else if (this.commCompareToNoColor(o) > 0) {
                return -1;
            } else {
                return 1;
            }
        }
        return -1;
    }


    /**
     * 通用比较一手牌的大小
     */
    public int commCompareToNoColor(NiuNiu o) {
        if (this.cards.size() != o.cards.size()) {
            throw new RuntimeException("两手牌的数量不一样不能比较");
        }

        for (int i = 0; i < this.cards.size(); i++) {
            int valueIndexOne = this.cards.get(i).getNumber().ordinal();
            int valueIndexTwo = o.cards.get(i).getNumber().ordinal();
            if (valueIndexOne != valueIndexTwo) {
                return valueIndexTwo - valueIndexOne;
            }
        }
        return 0;
    }

    /**
     * 在比花色
     */
    private int compartToColor(NiuNiu o) {
        for (int i = 0; i < this.cards.size(); i++) {
            int colorIndexOne = this.cards.get(i).getCardType().ordinal();
            int colorIndexTwo = o.cards.get(i).getCardType().ordinal();
            if (colorIndexOne != colorIndexTwo) {
                return colorIndexOne - colorIndexTwo;
            }
        }
        return 0;
    }


    /**
     * 获取list 的 元素个数为 count的 全部子集
     */
    @SuppressWarnings("unchecked")
    private <T> List<List<T>> getAllSubList(List<T> list, int count) {
        List<List<T>> dataList = new ArrayList<List<T>>();
        if (list.size() < count) {
            return null;
        }
        if (list.size() == count) {
            dataList.add(list);
            return dataList;
        }
        for (int i = 0; i < list.size(); i++) {

            for (int j = i + count - 1; j < list.size(); j++) {
                List subList = new ArrayList();
                for (int key = i; key < i + count - 1; key++) {
                    subList.add(list.get(key));
                }
                subList.add(list.get(j));
                dataList.add(subList);
                if (j == i) {
                    break;
                }
            }
        }
        return dataList;
    }


    @SuppressWarnings("unchecked")
    private <T> List<T> getSubList(List<T>... one) {
        if (one.length < 2) {
            throw new RuntimeException("parameter length is error");
        }
        List subList = new ArrayList();

        subList.addAll(one[0]);
        for (int i = 1; i < one.length; i++) {
            subList.removeAll(one[i]);
        }
        return subList;
    }

}