package weekly.w474.D;

import common.Preposing;

import java.util.Arrays;

/**
 * Q4. Lexicographically Smallest Palindromic Permutation Greater Than Target
 *
 * https://leetcode.cn/contest/weekly-contest-474/problems/lexicographically-smallest-palindromic-permutation-greater-than-target/
 *
 * You are given two strings s and target, each of length n, consisting of lowercase English letters.
 *
 * Return the lexicographically smallest string that is both a palindromic permutation of s and
 * strictly greater than target. If no such permutation exists, return an empty string.
 */

public class Solution {

    @Preposing(value = weekly.w472.C.Solution.class)
    public String lexPalindromicPermutation(String s, String target) {
        // 将 s 重新排列为一个大于 target 且在所有可行的排列中最小的回文序列
        //  - 首先检查 s 是否能构成回文串, 然后再从右枚举找到第一个大于 target 的元素
        int[] freq = new int[128];
        for (var v : s.toCharArray()) freq[v]++;

        char middle = '#';
        for (char c = 'a'; c <= 'z'; c++) {
            if (freq[c] % 2 == 0) continue;
            if (middle != '#') return "";
            middle = c; freq[c]--;
        }

        // 先假设左侧与target的左侧完全相同, 检查这种情况是否能比 target 大
        int n = s.length(), ln = n / 2, rn = n - ln;
        for (int i = 0; i < ln; i++) freq[target.charAt(i)] -= 2;
        // 如果此时满足条件, 则就直接找到答案了
        if (Arrays.stream(freq).allMatch(v -> v >= 0)) {
            char[] left = new char[ln];
            for (int i = 0; i < ln; i++) left[i] = target.charAt(i);

            char[] right = new char[rn];
            if (middle != '#') right[0] = middle;
            for (int i = 0; i < ln; i++) right[rn - 1 - i] = left[i];

            if (new String(right).compareTo(target.substring(ln)) > 0) {
                return new String(left) + new String(right);
            }
        }

        // 否则从右往左枚举
        for (int i = ln - 1; i >= 0; i--) {
            freq[target.charAt(i)] += 2;
            if (Arrays.stream(freq).anyMatch(a -> a < 0)) continue;

            // 尝试将答案中的 i 位置增大到 j
            for (char j = (char) (target.charAt(i) + 1); j <= 'z'; j++) {
                if (freq[j] == 0) continue;

                // 找到答案了
                freq[j] -= 2;
                StringBuilder sb = new StringBuilder();
                sb.append(target, 0, i).append(j);
                // 中间的可以按顺序填
                for (char k = 'a'; k <= 'z'; k++) {
                    sb.append(String.valueOf(k).repeat(freq[k] / 2));
                }

                // 添加中间字符和右半边元素
                String right = new StringBuilder(sb).reverse().toString();
                return sb + (middle != '#' ? String.valueOf(middle) : "") + right;
            }
        }
        return "";
    }

    public static void main(String[] args) {
        assert new Solution().lexPalindromicPermutation("abb", "bba").isEmpty();
        assert new Solution().lexPalindromicPermutation("z", "a").equals("z");
        assert new Solution().lexPalindromicPermutation("aac", "abb").equals("aca");
        assert new Solution().lexPalindromicPermutation("baba", "abba").equals("baab");
    }

}
