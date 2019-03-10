package com.mywuwu.gameserver.card.poker.NiuNiu;

public enum NiuNiuType {
    None,
    Niu_0,
    Niu_1,
    Niu_2,
    Niu_3,
    Niu_4,
    Niu_5,
    Niu_6,
    Niu_7,
    Niu_8,
    Niu_9,
    Niu_Niu,
    ShunZi_Niu,
    WuHua_Niu,
    ZHaDan_Niu;


    public static NiuNiuType getNiuNiuType(int number){
        switch (number) {
            case 0:
                return NiuNiuType.Niu_0;
            case 1:
                return NiuNiuType.Niu_1;
            case 2:
                return NiuNiuType.Niu_2;
            case 3:
                return NiuNiuType.Niu_3;
            case 4:
                return NiuNiuType.Niu_4;
            case 5:
                return NiuNiuType.Niu_5;
            case 6:
                return NiuNiuType.Niu_6;
            case 7:
                return NiuNiuType.Niu_7;
            case 8:
                return NiuNiuType.Niu_8;
            case 9:
                return NiuNiuType.Niu_9;
            case 10:
                return NiuNiuType.Niu_Niu;
            case 11:
                return NiuNiuType.ShunZi_Niu;
            case 12:
                return NiuNiuType.ZHaDan_Niu;
            default:
                return NiuNiuType.None;
        }
    }
}
