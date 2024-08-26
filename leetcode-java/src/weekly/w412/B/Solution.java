package weekly.w412.B;

import java.util.HashSet;
import java.util.Set;

/**
 * 3266. Final Array State After K Multiplication Operations II
 *
 * https://leetcode.cn/contest/weekly-contest-412/problems/final-array-state-after-k-multiplication-operations-ii/
 *
 * You are given an integer array nums, an integer k, and an integer multiplier.
 *
 * You need to perform k operations on nums. In each operation:
 *
 * Find the minimum value x in nums. If there are multiple occurrences of the
 * minimum value, select the one that appears first.
 * Replace the selected minimum value x with x * multiplier.
 * After the k operations, apply modulo 109 + 7 to every value in nums.
 *
 * Return an integer array denoting the final state of nums after performing
 * all k operations and then applying the modulo.
 */

/** @noinspection DuplicatedCode*/
public class Solution {

    public int countPairs(int[] nums) {
        int ans = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (almostEquals(nums[i], nums[j])) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private boolean almostEquals(int a, int b) {
        String sa = String.valueOf(a), sb = String.valueOf(b);
        int maxLen = Math.max(sa.length(), sb.length());

        return exchange(padding(sa.toCharArray(), maxLen)).contains(sb)
            || exchange(padding(sb.toCharArray(), maxLen)).contains(sa);
    }

    private Set<String> exchange(char[] chars) {
        Set<String> ans = new HashSet<>();
        ans.add(new String(chars));

        for (int i = 0; i < chars.length; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                swap(chars, i, j);
                ans.add(new String(chars));
                swap(chars, i, j);
            }
        }

        return ans;
    }

    private void swap(char[] chars, int a, int b) {
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
    }

    private char[] padding(char[] chars, int len) {
        if (chars.length == len) return chars;

        int pads = len - chars.length;
        char[] ans = new char[len];
        for (int i = 0; i < len; i++) {
            if (i >= pads) ans[i] = chars[i - pads];
            else ans[i] = '0';
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countPairs(new int[]{3,12,30,17,21}) == 2;
        assert new Solution().countPairs(new int[]{1,1,1,1,1}) == 10;
        assert new Solution().countPairs(new int[]{123,231}) == 0;
    }

}
