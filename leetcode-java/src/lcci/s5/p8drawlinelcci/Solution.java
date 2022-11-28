package lcci.s5.p8drawlinelcci;

import common.Checker;

/**
 * 面试题 05.08. 绘制直线
 *
 * https://leetcode.cn/problems/draw-line-lcci/
 *
 * 已知一个由像素点组成的单色屏幕，每行均有 w 个像素点，所有像素点初始为 0，左上角位置为 (0,0)。
 *
 * 现将每行的像素点按照「每 32 个像素点」为一组存放在一个 int 中，再依次存入长度为 length 的一维数组中。
 *
 * 我们将在屏幕上绘制一条从点 (x1,y) 到点 (x2,y) 的直线（即像素点修改为 1），请返回绘制过后的数组。
 *
 * 注意：
 * 用例保证屏幕宽度 w 可被 32 整除（即一个 int 不会分布在两行上）
 */

public class Solution {

    public int[] drawLine(int length, int w, int x1, int x2, int y) {
        int[] ans = new int[length];
        // w / 32 计算出每一行有多少个整数
        // (w / 32) * y 计算出前面有多少行的 0 被跳过了
        // x1 / 32 计算出在划线的行前面被跳过了多少个 0
        int idx = (w / 32) * y + (x1 / 32);
        if (idx < length) {
            // 第一组不是负数的长度
            if (x1 % 32 != 0) {
                int len = Math.min(32 - (x1 % 32), x2 - x1 + 1);
                ans[idx++] = ((1 << len) - 1) << (32 - (x1 % 32) - len);
                x1 += len;
            }

            // 计算得到 -1 的数量
            int negative = (x2 - x1 + 1) / 32; x1 += 32 * negative;
            for (; negative > 0; negative--) ans[idx++] = -1;

            // 最后一组为负数
            int len = x2 - x1 + 1;
            if (len != 0) ans[idx] = ((1 << (len + 1)) - 1) << (32 - len);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().drawLine(45, 160, 32, 159, 8),
            new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,-1,-1,-1});

        assert Checker.check(new Solution().drawLine(24, 96, 2, 19, 5),
            new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1073737728,0,0,0,0,0,0,0,0});

        assert Checker.check(new Solution().drawLine(1, 32, 30, 31, 0), new int[]{3});
        assert Checker.check(new Solution().drawLine(3, 96, 0, 95, 0), new int[]{-1, -1, -1});
    }

}
