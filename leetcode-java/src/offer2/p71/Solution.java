package offer2.p71;

import java.util.Random;

/**
 * 剑指 Offer II 071. 按权重生成随机数
 *
 * https://leetcode.cn/problems/cuyjEf/
 *
 * 给定一个正整数数组 w ，其中 w[i] 代表下标 i 的权重（下标从 0 开始），请写
 * 一个函数 pickIndex ，它可以随机地获取下标 i，选取下标 i 的概率与 w[i] 成正比。
 *
 * 例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3) = 0.25 （即，25%），而
 * 选取下标 1 的概率为 3 / (1 + 3) = 0.75（即，75%）。
 *
 * 也就是说，选取下标 i 的概率为 w[i] / sum(w) 。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {
    private final Random random;
    private final double[] picks;
    public Solution(int[] w) {
        random = new Random();

        double tot = 0;
        for (var v : w) tot += v;

        picks = new double[w.length];
        for (int i = 0, s = 0; i < w.length; i++) {
            picks[i] = (s += w[i]) / tot;
        }
    }

    public int pickIndex() {
        double choice = random.nextDouble();
        int l = 0, r = picks.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (picks[mid] >= choice) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    public static void main(String[] args) {
        Solution solution = new Solution(new int[]{1});
        assert solution.pickIndex() == 0;
        assert solution.pickIndex() == 0;
        assert solution.pickIndex() == 0;

        solution = new Solution(new int[]{1, 3});
        int first = 0, second = 0, total = 1_000_000;
        for (int i = 0; i < total; i++) {
            switch (solution.pickIndex()) {
                case 0 -> first++;
                case 1 -> second++;
            }
        }
        System.out.printf("First: %f, (%d of %d)\n", (double) first / total, first, total);
        System.out.printf("Second: %f, (%d of %d)\n", (double) second / total, second, total);
    }

}
