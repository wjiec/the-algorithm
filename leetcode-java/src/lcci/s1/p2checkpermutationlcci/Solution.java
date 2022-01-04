package lcci.s1.p2checkpermutationlcci;

/**
 * 面试题 01.02. 判定是否互为字符重排
 *
 * https://leetcode-cn.com/problems/check-permutation-lcci/
 *
 * 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
 */

public class Solution {

    public boolean CheckPermutation(String s1, String s2) {
        int[] map = new int[128];
        for (var c : s1.toCharArray()) map[c]++;
        for (var c : s2.toCharArray()) map[c]--;
        for (var n : map) if (n != 0) return false;
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().CheckPermutation("abc", "bca");
        assert !new Solution().CheckPermutation("abc", "bad");
    }

}
