package offer.p58zuoxuanzhuanzifuchuanlcof;

/**
 * 剑指 Offer 58 - II. 左旋转字符串
 *
 * https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/
 *
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
 *
 * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 */

public class Solution {

    public String reverseLeftWords(String s, int n) {
        char[] chars = new char[s.length()];
        for (int i = 0, l = s.length(); i < l; i++) {
            chars[i] = s.charAt((i + n) % l);
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        assert new Solution().reverseLeftWords("abcdefg", 2).equals("cdefgab");
        assert new Solution().reverseLeftWords("lrloseumgh", 6).equals("umghlrlose");
    }

}

