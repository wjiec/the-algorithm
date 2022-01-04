package lcci.s5.p3reversebitslcci;

/**
 * 面试题 05.03. 翻转数位
 *
 * https://leetcode-cn.com/problems/reverse-bits-lcci/
 *
 * 给定一个32位整数 num，你可以将一个数位从0变为1。
 *
 * 请编写一个程序，找出你能够获得的最长的一串1的长度。
 */

public class Solution {

    public int reverseBits(int num) {
        int curr = 0, reversed = 0, ans = 1;
        for (int i = 0; i < 32; i++) {
            if ((num & (1 << i)) != 0) {
                curr++;
                reversed++;
            } else {
                reversed = curr + 1;
                curr = 0;
            }

            ans = Math.max(ans, reversed);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().reverseBits(1775) == 8;
        assert new Solution().reverseBits(7) == 4;
    }

}
