package weekly.w330.C;

import java.util.PriorityQueue;

/**
 * 2551. Put Marbles in Bags
 *
 * https://leetcode.cn/problems/put-marbles-in-bags/
 *
 * You have k bags. You are given a 0-indexed integer array weights where
 * weights[i] is the weight of the ith marble.
 *
 * You are also given the integer k.
 *
 * Divide the marbles into the k bags according to the following rules:
 *
 * No bag is empty.
 * If the ith marble and jth marble are in a bag, then all marbles with an index
 * between the ith and jth indices should also be in that same bag.
 *
 * If a bag consists of all the marbles with an index from i to j inclusively, then
 * the cost of the bag is weights[i] + weights[j].
 *
 * The score after distributing the marbles is the sum of the costs of all the k bags.
 *
 * Return the difference between the maximum and minimum scores among marble distributions.
 */

public class Solution {

    public long putMarbles(int[] weights, int k) {
        if (k == weights.length) return 0;
        // max: [a~~op~~~qs~~z]
        // min: [a~~~lm~np~~~z]
        // max - min = (a + o + p + q + s + z) - (a + l + m + n + p + z)
        //           = (o + p) + (q + s) - (l + m) - (n + p)
        // k - 1 个 max-pair 最大, k - 1 个 min-pair 最小
        k--;
        PriorityQueue<Integer> max = new PriorityQueue<>((a, b) -> a - b);
        PriorityQueue<Integer> min = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 1; i < weights.length; i++) {
            min.add(weights[i] + weights[i - 1]);
            max.add(weights[i] + weights[i - 1]);
            if (min.size() > k) min.remove();
            if (max.size() > k) max.remove();
        }

        long ans = 0;
        while (!max.isEmpty()) ans += max.remove();
        while (!min.isEmpty()) ans -= min.remove();
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().putMarbles(new int[]{1,4,2,5,2}, 3) == 3;

        assert new Solution().putMarbles(new int[]{1,3,5,1}, 2) == 4;
        assert new Solution().putMarbles(new int[]{1,3}, 2) == 0;
    }

}
