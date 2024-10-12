package problem.p1359countallvalidpickupanddeliveryoptions;

/**
 * 1359. Count All Valid Pickup and Delivery Options
 *
 * https://leetcode.cn/problems/count-all-valid-pickup-and-delivery-options/
 *
 * Given n orders, each order consists of a pickup and a delivery service.
 *
 * Count all valid pickup/delivery possible sequences such that delivery(i) is always after of pickup(i).
 *
 * Since the answer may be too large, return it modulo 10^9 + 7.
 */

public class Solution {

    public int countOrders(int n) {
        if (n == 1) return 1;

        // 对于 n - 1 笔订单, 总共有 2 + 2 * (n - 1) - 1 个空位可以插入当前的 Dn Pn
        //  - 每一笔订单都有 D 和 P, 所以一共有 2 * (n - 1) 个 D 和 P
        //
        // 题目要求 P 一定要在 D 之前, 那么在 2 + 2 * (n - 1) - 1 个空位中, 我们先排 P
        // 然后在 P 之后的每个空位(包括P当前的位置)写入 D 都是符合条件的方案
        //
        // 另 e = 2 + 2 * (n - 1) - 1, 答案可以表示为
        //  ans = e + (e - 1) + (e - 2) + ... + 1
        long e = 2 + 2L * (n - 1) - 1;
        return (int) ((countOrders(n - 1) * e * (1 + e) / 2) % 1_000_000_007);
    }

    public static void main(String[] args) {
    }

}
