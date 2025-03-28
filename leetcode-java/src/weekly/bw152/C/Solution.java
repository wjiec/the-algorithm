package weekly.bw152.C;

import common.Checker;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 3485. Longest Common Prefix of K Strings After Removal
 *
 * https://leetcode.cn/contest/biweekly-contest-152/problems/longest-common-prefix-of-k-strings-after-removal/
 *
 * You are given an array of strings words and an integer k.
 *
 * For each index i in the range [0, words.length - 1], find the length of the longest
 * common prefix among any k strings (selected at distinct indices) from the remaining
 * array after removing the ith element.
 *
 * Return an array answer, where answer[i] is the answer for ith element. If removing
 * the ith element leaves the array with fewer than k strings, answer[i] is 0.
 */

public class Solution {

    public int[] longestCommonPrefix(String[] words, int k) {
        // 如果我们将 words 排序, 那么 k 个字符串的 LCP 实际上就是相邻的字符串
        //  - 对于连续子数组的 LCP, 实际上就等于第一个和最后一个的 LCP (因为排序后, 相同的前缀是逐渐变少的)
        // 所以找到最大的一组 LCP, 如果删除的元素不在最大的 LCP 对应的子数组里, 那么就是取最大的
        // 否则删除的元素在最大的 LCP 对应的子数组里, 分类讨论:
        //  - 如果次大的 LCP 子数组和最大的没有重叠, 那么就是取次大的 LCP
        //  - 如果次大的 LCP 子数组和最大的 LCP 数组有重叠
        //      - 那么就意味着次大的 LCP 子数组中有一部分是在最大的 LCP 子数组中
        //      - 最大的 LCP 子数组是的第一个元素 A, 最后一个元素 B, 次大的 LCP 子数组中的第一个元素 C, 最后一个元素 D
        //          - 我们有 LCP(A, B) = FIRST, LCP(C, D) = SECONDARY
        //          - 因为有重叠, 且 C 在 A 和 B 之间, 所以 LCP(A, D) = SECONDARY
        //      - 从最大的 LCP 子数组中移除一个, 那必然可以从次大的子数组中取一个到次大的中, 最终答案还是次大的 LCP
        Integer[] sorted = new Integer[words.length];
        for (int i = 0; i < words.length; i++) sorted[i] = i;
        Arrays.sort(sorted, Comparator.comparing(i -> words[i]));

        // 找到最大和次大的 LCP 以及最大 LCP 的对应位置
        int first = 0, secondary = 0, firstIndex = 0;
        for (int i = 0; i + k - 1 < words.length; i++) {
            int curr = lcp(words[sorted[i]].toCharArray(), words[sorted[i + k - 1]].toCharArray());
            if (curr > first) { secondary = first; first = curr; firstIndex = i; }
            else if (curr > secondary) { secondary = curr; }
        }

        int[] ans = new int[words.length];
        Arrays.fill(ans, first);

        // 然后将在最大 LCP 中的元素改成次大的 LCP
        for (int i = 0; i < k; i++) ans[sorted[firstIndex + i]] = secondary;
        return ans;
    }

    private int lcp(char[] a, char[] b) {
        for (int i = 0; i < a.length && i < b.length; i++) {
            if (a[i] != b[i]) return i;
        }
        return Math.min(a.length, b.length);
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().longestCommonPrefix(new String[]{"jump","run","run","jump","run"}, 2), new int[]{3,4,4,3,4});
        assert Checker.check(new Solution().longestCommonPrefix(new String[]{"dog","racer","car"}, 2), new int[]{0,0,0});
    }

}
