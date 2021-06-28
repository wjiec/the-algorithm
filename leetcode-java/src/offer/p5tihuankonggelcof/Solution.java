package offer.p5tihuankonggelcof;

/**
 * 剑指 Offer 05. 替换空格
 *
 * https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/
 *
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 */

public class Solution {

    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (var c : s.toCharArray()) {
            if (c == ' ') sb.append("%20");
            else sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().replaceSpace("We are happy.").equals("We%20are%20happy.");
    }

}
