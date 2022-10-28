package offer.p60ngetouzidedianshulcof;

import common.Checker;

import java.util.TreeMap;

/**
 * 剑指 Offer 60. n个骰子的点数
 *
 * https://leetcode.cn/problems/nge-tou-zi-de-dian-shu-lcof/
 *
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 *
 * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 */

public class Solution {

    public double[] dicesProbability(int n) {
        TreeMap<Integer, Double> map = probability(n);
        double[] ans = new double[map.size()]; int idx = 0;
        for (var v : map.values()) ans[idx++] = v;
        return ans;
    }

    private TreeMap<Integer, Double> probability(int n) {
        if (n == 0) return new TreeMap<>(){{ put(0, 1.0); }};

        double step = 1.0 / 6.0;
        TreeMap<Integer, Double> map = new TreeMap<>();
        for (var kv : probability(n - 1).entrySet()) {
            for (int i = 1; i <= 6; i++) {
                map.merge(i + kv.getKey(), kv.getValue() * step, Double::sum);
            }
        }

        return map;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().dicesProbability(1), new double[]{
            0.16667,0.16667,0.16667,0.16667,0.16667,0.16667
        });

        assert Checker.check(new Solution().dicesProbability(2), new double[]{
            0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778
        });
    }

}
