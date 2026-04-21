package weekly.w483.C;

/**
 * Q3. Minimum Cost to Make Two Binary Strings Equal
 *
 * https://leetcode.cn/contest/weekly-contest-483/problems/minimum-cost-to-make-two-binary-strings-equal/
 *
 * You are given two binary strings s and t, both of length n, and three
 * positive integers flipCost, swapCost, and crossCost.
 *
 * You are allowed to apply the following operations any number of times (in any order) to the strings s and t:
 *
 * Choose any index i and flip s[i] or t[i] (change '0' to '1' or '1' to '0'). The cost of this operation is flipCost.
 * Choose two distinct indices i and j, and swap either s[i] and s[j] or t[i] and t[j]. The cost of this operation is swapCost.
 * Choose an index i and swap s[i] with t[i]. The cost of this operation is crossCost.
 *
 * Return an integer denoting the minimum total cost needed to make the strings s and t equal.
 */

public class Solution {

    public long minimumCost(String s, String t, int flipCost, int swapCost, int crossCost) {
        int n = s.length();
        // 当 s[i] != t[i] 时, 我们可以选择以下操作中的一个使得这个位置变为相同
        //  - 1: 翻转 s[i] 或者 t[i], 花费 flipCost
        //  - 2: 与另一对不相同的 s[j] != t[j] 执行 swap(s[i], s[j]) 或者 swap(s[j], t[j]), 花费 swapCost
        //  - 3: 选择一对不相同的 s[j] != t[j] 执行 swap(s[j], t[j]), 花费 crossCost
        //
        // 很明显, 我们有以下方案解决
        //  - 将所有 s[i] != t[i] 的错误对分组, 再按照 s[i] 进行分组, 有 s[i] = 0 | 1 两组
        //  - 1. 我们直接执行 1 操作完成所有错误对的纠正, 花费 len * flipCost
        //  - 2. 可以选择 mn = min(s0, s1) 次 2 操作, 花费 mn * swapCost + (s0 + s1 - 2 * mn) * flipCost
        //  - 3. 或者执行 s0 的一部分与 t 交换, 花费 k * crossCost, 然后再执行其他操作
        long badS0 = 0, badS1 = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (s.charAt(i) == '0') badS0++;
                else badS1++;
            }
        }

        // 全部执行单独翻转
        long ans = (badS0 + badS1) * flipCost;
        // 如果交换的费用小于翻转的费用, 那我们优先选择交换
        if (swapCost < flipCost * 2) {
            long mn = Math.min(badS0, badS1), rem = badS0 + badS1 - 2L * mn;
            // 然后剩余的元素要么是单独翻转
            ans = Math.min(ans, mn * swapCost + rem * flipCost);
            // 或者是选择一半出来执行与 t 交换再翻转
            ans = Math.min(ans, mn * swapCost + (rem >> 1) * (crossCost + swapCost) + (rem & 1) * flipCost);
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
