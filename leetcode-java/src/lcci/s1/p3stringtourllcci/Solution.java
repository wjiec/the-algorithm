package lcci.s1.p3stringtourllcci;

/**
 * 面试题 01.03. URL化
 *
 * https://leetcode-cn.com/problems/string-to-url-lcci/
 *
 * URL化。编写一种方法，将字符串中的空格全部替换为%20。假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。
 * （注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
 */

public class Solution {

    public String replaceSpaces(String S, int length) {
        return S.substring(0, length).replace(" ", "%20");
    }

    public static void main(String[] args) {
        assert new Solution().replaceSpaces("Mr John Smith    ", 13).equals("Mr%20John%20Smith");
        assert new Solution().replaceSpaces("               ", 5).equals("%20%20%20%20%20");
    }

}
