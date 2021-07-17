package offer.p50diyigezhichuxianyicidezifulcof;

/**
 * 剑指 Offer 50. 第一个只出现一次的字符
 *
 * https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/
 *
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 */

public class Solution {

    public char firstUniqChar(String s) {
        int[] chars = new int[255];
        for (var ch : s.toCharArray()) chars[ch]++;
        for (var ch : s.toCharArray()) if (chars[ch] == 1) return ch;
        return ' ';
    }

    public static void main(String[] args) {
        assert new Solution().firstUniqChar("abaccdeff") == 'b';
    }

}
