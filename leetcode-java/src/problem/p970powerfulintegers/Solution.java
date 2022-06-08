package problem.p970powerfulintegers;

import common.Checker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 970. Powerful Integers
 *
 * https://leetcode.cn/problems/powerful-integers/
 *
 * Given three integers x, y, and bound, return a list of all the powerful integers
 * that have a value less than or equal to bound.
 *
 * An integer is powerful if it can be represented as xi + yj for some integers i >= 0 and j >= 0.
 *
 * You may return the answer in any order. In your answer, each value should occur at most once.
 */

public class Solution {

    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        List<Integer> xSorted = new ArrayList<>();
        List<Integer> ySorted = new ArrayList<>();
        if (x == 1) xSorted.add(1);
        else for (int v = 1; v <= bound; v *= x) xSorted.add(v);

        if (y == 1) ySorted.add(1);
        else for (int v = 1; v <= bound; v *= y) ySorted.add(v);

        Set<Integer> ans = new HashSet<>();
        for (var a : xSorted) {
            for (var b : ySorted) {
                if (a + b <= bound) ans.add(a + b);
                else break;
            }
        }
        return new ArrayList<>(ans);
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().powerfulIntegers(2, 3, 10), List.of(2,3,4,5,7,9,10));
        assert Checker.anyOrder(new Solution().powerfulIntegers(3, 5, 15), List.of(2,4,6,8,10,14));
    }

}
