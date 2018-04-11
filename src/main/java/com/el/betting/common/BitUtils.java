package com.el.betting.common;

public class BitUtils {

    /**
     * Returns true if given bit is set to 1 for a given number.
     * @param number the number which bit value should be evaluated
     * @param bitIndex the index of bit, starts from 0
     * @return returns true if the bit value of index is 1, otherwise 0
     */
    public static boolean isBitSet(int number, int bitIndex) {
        if (bitIndex < 0) {
            throw new IndexOutOfBoundsException("bitIndex < 0: " + bitIndex);
        }

        return ((number & (1L << bitIndex)) != 0);
    }

}
