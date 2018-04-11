package com.el.betting.common;

import com.google.common.primitives.Ints;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayUtils {

    /**
     * Copies elements from given array to new array by ignoring specified elements
     */
    public static int[] copyWithExclusion(int[] array, int... excludedElements) {
        final List<Integer> excludedElementList = Ints.asList(excludedElements);
        int newArrayLength = 0;
        for (int i : array) {
            if(!excludedElementList.contains(i)) {
                newArrayLength++;
            }
        }

        final int[] newArray = new int[newArrayLength];
        int j = 0;
        for (int i : array) {
            if(!excludedElementList.contains(i)) {
                newArray[j++] = i;
            }
        }
        return newArray;
    }
}
