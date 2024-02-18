package weekly.w384.C;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 3035. Maximum Palindromes After Operations
 *
 * https://leetcode.cn/contest/weekly-contest-384/problems/maximum-palindromes-after-operations/
 *
 * You are given a 0-indexed string array words having length n and containing 0-indexed strings.
 *
 * You are allowed to perform the following operation any number of times (including zero):
 *
 * Choose integers i, j, x, and y such that 0 <= i, j < n, 0 <= x < words[i].length, 0 <= y < words[j].length,
 * and swap the characters words[i][x] and words[j][y].
 *
 * Return an integer denoting the maximum number of palindromes words can contain, after performing some operations.
 *
 * Note: i and j may be equal during an operation.
 */

public class Solution {

    public int maxPalindromesAfterOperations(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));

        int[] count = new int[128];
        for (var word : words) {
            for (var c : word.toCharArray()) count[c]++;
        }

        int single = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (var c : count) {
            single += c & 1;
            pq.add(((c >> 1) << 1));
        }
        while (!pq.isEmpty() && pq.peek() == 0) pq.remove();

        int ans = 0, remain = 0;
        for (var word : words) {
            int c = word.length() - (word.length() & 1);
            while (c != 0 && !pq.isEmpty()) {
                int x = pq.remove();
                int r = Math.min(c, x);
                c -= r; x -= r;
                if (x != 0) pq.add(x);
            }
            if (c == 0) ans++;
            if (c == 1) remain++;
        }
        return ans + Math.min(single, remain);
    }

    public static void main(String[] args) {
        assert new Solution().maxPalindromesAfterOperations(new String[]{"ac","a","bb","ac","ca"}) == 5;
        assert new Solution().maxPalindromesAfterOperations(new String[]{"abbb","ba","aa"}) == 3;
        assert new Solution().maxPalindromesAfterOperations(new String[]{"abc","ab"}) == 2;
        assert new Solution().maxPalindromesAfterOperations(new String[]{"a", "bc", "de"}) == 1;
    }

}
