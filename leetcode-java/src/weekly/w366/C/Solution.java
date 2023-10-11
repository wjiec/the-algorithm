package weekly.w366.C;

import java.util.ArrayList;
import java.util.List;

/**
 * 2896. Apply Operations to Make Two Strings Equal
 *
 * https://leetcode.cn/contest/weekly-contest-366/problems/apply-operations-to-make-two-strings-equal/
 *
 * You are given two 0-indexed binary strings s1 and s2, both of length n, and a positive integer x.
 *
 * You can perform any of the following operations on the string s1 any number of times:
 *
 * Choose two indices i and j, and flip both s1[i] and s1[j]. The cost of this operation is x.
 * Choose an index i such that i < n - 1 and flip both s1[i] and s1[i + 1]. The cost of this operation is 1.
 * Return the minimum cost needed to make the strings s1 and s2 equal, or return -1 if it is impossible.
 *
 * Note that flipping a character means changing it from 0 to 1 or vice-versa.
 */

public class Solution {

    public int minOperations(String s1, String s2, int x) {
        if (s1.equals(s2)) return 0;

        List<Integer> xor = new ArrayList<>();
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) xor.add(i);
        }
        if (xor.size() % 2 == 1) return -1;

        int a = 0, b = x;
        for (int i = 1; i < xor.size(); i++) {
            int c = Math.min(b + x, a + ((xor.get(i) - xor.get(i - 1)) * 2));
            a = b; b = c;
        }
        return b / 2;
    }

    public static void main(String[] args) {
        assert new Solution().minOperations("11001011111", "01111000110", 2) == 4;

        assert new Solution().minOperations("1100011000", "0101001010", 2) == 4;
        assert new Solution().minOperations("10110", "00011", 4) == -1;
    }

}
