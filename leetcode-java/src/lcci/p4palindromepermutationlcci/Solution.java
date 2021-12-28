package lcci.p4palindromepermutationlcci;

/**
 * 面试题 01.04. 回文排列
 *
 * https://leetcode-cn.com/problems/palindrome-permutation-lcci/
 *
 * 给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
 *
 * 回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
 *
 * 回文串不一定是字典当中的单词。
 */

public class Solution {

    public boolean canPermutePalindrome(String s) {
        int[] map = new int[128];
        for (var c : s.toCharArray()) map[c]++;
        boolean single = true;
        for (var n : map) {
            if (n % 2 == 1) {
                if (single) {
                    single = false;
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().canPermutePalindrome("tactcoa");
    }

}
