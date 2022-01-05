package lcci.s16.p15mastermindlcci;

import common.Checker;

/**
 * 面试题 16.15. 珠玑妙算
 *
 * https://leetcode-cn.com/problems/master-mind-lcci/
 *
 * 珠玑妙算游戏（the game of master mind）的玩法如下。
 *
 * 计算机有4个槽，每个槽放一个球，颜色可能是红色（R）、黄色（Y）、绿色（G）或蓝色（B）。
 *
 * 例如，计算机可能有RGGB 4种（槽1为红色，槽2、3为绿色，槽4为蓝色）。
 *
 * 作为用户，你试图猜出颜色组合。打个比方，你可能会猜YRGB。
 *
 * 要是猜对某个槽的颜色，则算一次“猜中”；要是只猜对颜色但槽位猜错了，则算一次“伪猜中”。注意，“猜中”不能算入“伪猜中”。
 *
 * 给定一种颜色组合solution和一个猜测guess，编写一个方法，返回猜中和伪猜中的次数answer，
 * 其中answer[0]为猜中的次数，answer[1]为伪猜中的次数。
 */

public class Solution {

    public int[] masterMind(String solution, String guess) {
        int hit = 0, pseudo = 0, r = 0, g = 0, b = 0, y = 0;
        char[] chars1 = solution.toCharArray(), chars2 = guess.toCharArray();
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] == chars2[i]) {
                hit++;
                chars2[i] = ' ';
            } else {
                switch (chars1[i]) {
                    case 'R': r++; break;
                    case 'G': g++; break;
                    case 'B': b++; break;
                    case 'Y': y++; break;
                }
            }
        }

        for (int i = 0; i < chars2.length; i++) {
            switch (chars2[i]) {
                case 'R': if (r-- > 0) { pseudo++; } break;
                case 'G': if (g-- > 0) { pseudo++; } break;
                case 'B': if (b-- > 0) { pseudo++; } break;
                case 'Y': if (y-- > 0) { pseudo++; } break;
            }
        }

        return new int[]{hit, pseudo};
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().masterMind("RGBY", "GGRR"), new int[]{1,1});
    }

}
