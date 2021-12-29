package lcci.p6compressstringlcci;

/**
 * 面试题 01.06. 字符串压缩
 *
 * https://leetcode-cn.com/problems/compress-string-lcci/
 *
 * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。
 *
 * 比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。
 *
 * 你可以假设字符串中只包含大小写英文字母（a至z）。
 */

public class Solution {

    public String compressString(String S) {
        StringBuilder sb = new StringBuilder();
        char curr = ' '; int count = 0;
        for (var c : S.toCharArray()) {
            if (c != curr) {
                if (count != 0) sb.append(curr).append(count);
                curr = c; count = 1;
            } else count++;
        }
        sb.append(curr).append(count);

        return sb.length() >= S.length() ? S : sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().compressString("aabcccccaaa").equals("a2b1c5a3");
        assert new Solution().compressString("abbccd").equals("abbccd");
    }

}
