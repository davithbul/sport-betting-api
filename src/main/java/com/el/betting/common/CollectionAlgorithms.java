package com.el.betting.common;

import com.google.common.base.Preconditions;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Provides methods for doing finding combinations(order doesn't matter) using elements from list.
 * For Permutation(If the order does matter) operations could be used - PermutationCollection
 */
public class CollectionAlgorithms {

    /**
     * Returns all possible elements' combinations from the given list.
     */
    public static <T> List<List<T>> getAllCombinations(final List<T> elements) {
        int combinationCount = (int) Math.pow(2, elements.size()) - 1;
        List<List<T>> combinations = new ArrayList<>(combinationCount);
        for (int combinationNumber = 1; combinationNumber <= combinationCount; combinationNumber++) {
            List<T> combination = new ArrayList<>();
            for (int elementIndex = 0; elementIndex < elements.size(); elementIndex++) {
                if (BitUtils.isBitSet(combinationNumber, elementIndex)) {
                    combination.add(elements.get(elementIndex));
                }
            }
            combinations.add(combination);
        }
        return combinations;
    }

    /**
     * Returns all possible unique combinations having k elements.
     */
    public static <T> List<List<T>> getCombinations(final List<T> elements, int k) {
        // get the length of the array
        // e.g. for {'A','B','C','D'} => N = 4
        int N = elements.size();
        Preconditions.checkArgument(k <= N);

        //will keep combinations in this list
        List<List<T>> combinations = new ArrayList<>();

        // get the combination by index
        // e.g. 01 --> AB , 23 --> CD
        int combination[] = new int[k];

        // position of current index
        //  if (r = 1)              r*
        //  index ==>        0   |   1   |   2
        //  element ==>      A   |   B   |   C
        int r = 0;
        int index = 0;

        while (r >= 0) {
            // possible indexes for 1st position "r=0" are "0,1,2" --> "A,B,C"
            // possible indexes for 2nd position "r=1" are "1,2,3" --> "B,C,D"

            // for r = 0 ==> index < (4+ (0 - 2)) = 2
            if (index <= (N + (r - k))) {
                combination[r] = index;

                // if we are at the last position print and increase the index
                if (r == k - 1) {

                    //do something with the combination e.g. add to list or print
                    List<T> collect = Arrays.stream(combination)
                            .mapToObj(elements::get)
                            .collect(Collectors.toList());

                    combinations.add(collect);
                    index++;
                } else {
                    // select index for next position
                    index = combination[r] + 1;
                    r++;
                }
            } else {
                r--;
                if (r > 0)
                    index = combination[r] + 1;
                else
                    index = combination[0] + 1;
            }
        }

        return combinations;
    }

    public static <T> Map<String, Predicate<T>> createPredicatesCombination(List<Map.Entry<String, Predicate<T>>> predicatesWithName) {
        Map<String, Predicate<T>> combinedPredicates = new HashMap<>();
        StringBuilder andPredicateName = new StringBuilder();
        StringBuilder orPredicateName = new StringBuilder();
        Predicate<T> andPredicates = null;
        Predicate<T> orPredicates = null;
        for (Map.Entry<String, Predicate<T>> predicateEntry : predicatesWithName) {
            if (andPredicates == null) {
                andPredicateName.append(predicateEntry.getKey());
                andPredicates = predicateEntry.getValue();
            } else {
                andPredicateName.append(" && ").append(predicateEntry.getKey());
                andPredicates = andPredicates.and(predicateEntry.getValue());
            }

            if (orPredicates == null) {
                orPredicateName.append(predicateEntry.getKey());
                orPredicates = predicateEntry.getValue();
            } else {
                orPredicateName.append(" || ").append(predicateEntry.getKey());
                orPredicates = orPredicates.or(predicateEntry.getValue());
            }
        }

        combinedPredicates.putIfAbsent(andPredicateName.toString(), andPredicates);
//        combinedPredicates.putIfAbsent(orPredicateName.toString(), orPredicates);

        return combinedPredicates;
    }
}
