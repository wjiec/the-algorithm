package offer2.p32;

/**
 * 剑指 Offer II 032. 有效的变位词
 *
 * https://leetcode-cn.com/problems/dKk3P7/
 *
 * 给定两个字符串 s 和 t ，编写一个函数来判断它们是不是一组变位词（字母异位词）。
 *
 * 注意：若 s 和 t 中每个字符出现的次数都相同且字符顺序不完全相同，则称 s 和 t 互为变位词（字母异位词）。
 */

public class Solution {

    public boolean isAnagram(String s, String t) {
        if (s.equals(t)) return false;
        int[] chars = new int[128];
        for (var c : s.toCharArray()) chars[c]++;
        for (var c : t.toCharArray()) chars[c]--;
        for (var n : chars) if (n != 0) return false;
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().isAnagram("anagram", "nagaram");
        assert !new Solution().isAnagram("rat", "car");
        assert !new Solution().isAnagram("a", "a");
    }

}
