package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) {

        int offset = 0;
        int counter = 0;
        try {
            if (y.equals(null)) throw new NullPointerException();
            for (Object o : x) {
                for (int j = offset; j < y.size(); j++) {
                    if (o.equals(y.get(j))) {
                        offset = j + 1;
                        counter++;
                        break;
                    }
                }
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException();
        }
        return counter == x.size();
    }
}
