package com.mywuwu.game.core.list;

import java.util.ArrayList;

public class CircularList<E> extends ArrayList<E> {

    private int index = 0;


    public E element() {
        return this.get(index);
    }

    public E poll() {

        if (index == (this.size() - 1)) {
            index = 0;
        } else {
            index++;
        }
        return this.get(index);
    }
}