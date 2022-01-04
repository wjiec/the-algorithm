package lcci.s5.p6convertintegerlcci;

/**
 * 面试题 05.06. 整数转换
 *
 * https://leetcode-cn.com/problems/convert-integer-lcci/
 *
 * 整数转换。编写一个函数，确定需要改变几个位才能将整数A转成整数B。
 */

public class Solution {

    public int convertInteger(int A, int B) {
        int v = A ^ B, ans = 0;
        for (int i = 0; i < 32; i++) {
            if ((v & (1 << i)) != 0) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().convertInteger(826966453, -729934991) == 14;

        assert new Solution().convertInteger(29, 15) == 2;
        assert new Solution().convertInteger(1, 2) == 2;
    }

}
