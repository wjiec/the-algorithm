package problem.p1238circularpermutationinbinaryrepresentation;

import common.Checker;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 1238. Circular Permutation in Binary Representation
 *
 * https://leetcode.cn/problems/circular-permutation-in-binary-representation/
 *
 * Given 2 integers n and start. Your task is return any permutation p of (0,1,2.....,2^n -1) such that :
 *
 * p[0] = start
 * p[i] and p[i+1] differ by only one bit in their binary representation.
 * p[0] and p[2^n -1] must also differ by only one bit in their binary representation.
 */

public class Solution {

    public List<Integer> circularPermutation(int n, int start) {
        Queue<Integer> gray = new ArrayDeque<>();
        for (int i = 0, x = 1 << n; i < x; i++) {
            gray.add(i ^ (i >> 1));
        }

        while (!gray.isEmpty()) {
            if (gray.peek() == start) {
                break;
            }
            gray.add(gray.remove());
        }

        return new ArrayList<>(gray);
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().circularPermutation(2, 3), List.of(3,2,0,1));
        assert Checker.check(new Solution().circularPermutation(3, 2), List.of(2,6,7,5,4,0,1,3));
    }

}
