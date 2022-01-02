package lcci5.p1insertintobitslcci;

/**
 * 面试题 05.01. 插入
 *
 * https://leetcode-cn.com/problems/insert-into-bits-lcci/
 *
 * 给定两个整型数字 N 与 M，以及表示比特位置的 i 与 j（i <= j，且从 0 位开始计算）。
 *
 * 编写一种方法，使 M 对应的二进制数字插入 N 对应的二进制数字的第 i ~ j 位区域，不足之处用 0 补齐。
 */

public class Solution {

    public int insertBits(int N, int M, int i, int j) {
        for (int b = 0; b < 32; b++) {
            if (i <= b && b <= j) {
                if ((M & 1) << b == 0) {
                    N &= ~(1 << b);
                } else {
                    N |= (M & 1) << b;
                }
                M >>= 1;
            }
        }
        return N;
    }

    public static void main(String[] args) {
        // 1000100001000111111011000001101
        // 11111000010011001001
        assert new Solution().insertBits(1143207437, 1017033, 11, 31) == 2082885133;

        assert new Solution().insertBits(1024, 19, 2, 6) == 1100;
        assert new Solution().insertBits(0, 31, 0, 4) == 31;
    }

}
