package offer2.p5;

/**
 * 剑指 Offer II 005. 单词长度的最大乘积
 *
 * https://leetcode.cn/problems/aseY1I/
 *
 * 给定一个字符串数组 words，请计算当两个字符串 words[i] 和 words[j] 不包含相同字符时，它们长度的乘积的最大值。
 * 假设字符串中只包含英语的小写字母。如果没有不包含相同字符的一对字符串，返回 0。
 */

public class Solution {

    public int maxProduct(String[] words) {
        int n = words.length;
        int[] hashes = new int[n];
        for (int i = 0; i < n; i++) {
            for (var c : words[i].toCharArray()) {
                hashes[i] |= 1 << (c - 'a');
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && (hashes[i] & hashes[j]) == 0) {
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxProduct(new String[]{"abcw","baz","foo","bar","fxyz","abcdef"}) == 16;
        assert new Solution().maxProduct(new String[]{"a","ab","abc","d","cd","bcd","abcd"}) == 4;
        assert new Solution().maxProduct(new String[]{"a","aa","aaa","aaaa"}) == 0;
    }

}
