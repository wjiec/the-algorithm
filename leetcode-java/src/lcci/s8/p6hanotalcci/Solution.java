package lcci.s8.p6hanotalcci;

import java.util.List;

/**
 * 面试题 08.06. 汉诺塔问题
 *
 * https://leetcode-cn.com/problems/hanota-lcci/
 *
 * 在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。
 * 一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。移动圆盘时受到以下限制:
 *
 * (1) 每次只能移动一个盘子;
 * (2) 盘子只能从柱子顶端滑出移到下一根柱子;
 * (3) 盘子只能叠在比它大的盘子上。
 *
 * 请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。
 *
 * 你需要原地修改栈。
 */

public class Solution {

    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        move(A.size(), A, B, C);
    }

    private void move(int n, List<Integer> a, List<Integer> b, List<Integer> c) {
        if (n == 1) {
            c.add(0, a.remove(0));
            return;
        }

        move(n - 1, a, c, b);
        c.add(0, a.remove(0));
        move(n - 1, b, a, c);
    }

    public static void main(String[] args) {
        new Solution().hanota(List.of(2,1,0), List.of(), List.of());
    }

}
