package problem.p2007findoriginalarrayfromdoubledarray;

import ability.Ability;
import common.Checker;

import java.util.TreeMap;

/**
 * 2007. Find Original Array From Doubled Array
 *
 * https://leetcode.cn/problems/find-original-array-from-doubled-array/
 *
 * An integer array original is transformed into a doubled array changed by
 * appending twice the value of every element in original, and then randomly
 * shuffling the resulting array.
 *
 * Given an array changed, return original if changed is a doubled array.
 * If changed is not a doubled array, return an empty array.
 * The elements in original may be returned in any order.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private final int[] BAD_ANSWER = new int[0];

    public int[] findOriginalArray(int[] changed) {
        if (changed.length % 2 != 0) return BAD_ANSWER;

        TreeMap<Integer, Integer> count = new TreeMap<>();
        for (var v : changed) count.merge(v, 1, Integer::sum);

        int idx = 0;
        int[] ans = new int[changed.length / 2];
        if (count.containsKey(0)) {
            int c = count.get(0);
            if (c % 2 != 0) return BAD_ANSWER;
            idx = c / 2; count.remove(0);
        }

        while (!count.isEmpty()) {
            int first = count.firstKey();
            if (!count.containsKey(first * 2)) return BAD_ANSWER;

            int c1 = count.get(first);
            int c2 = count.get(first * 2);
            if (c2 < c1) return BAD_ANSWER;

            for (int i = 0; i < c1; i++) ans[idx++] = first;
            count.remove(first);
            count.merge(first * 2, c1, Ability::subtract);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().findOriginalArray(new int[]{1,3,4,2,6,8}), new int[]{1,3,4});
        assert Checker.anyOrder(new Solution().findOriginalArray(new int[]{6,3,0,1}), new int[]{});
        assert Checker.anyOrder(new Solution().findOriginalArray(new int[]{1}), new int[]{});
    }

}
