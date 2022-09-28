package problem.p1962removestonestominimizethetotal;

import java.util.PriorityQueue;

/**
 * 1962. Remove Stones to Minimize the Total
 *
 * https://leetcode.cn/problems/remove-stones-to-minimize-the-total/
 *
 * You are given a 0-indexed integer array piles, where piles[i] represents the number of stones
 * in the ith pile, and an integer k. You should apply the following operation exactly k times:
 *
 * Choose any piles[i] and remove floor(piles[i] / 2) stones from it.
 * Notice that you can apply the operation on the same pile more than once.
 *
 * Return the minimum possible total number of stones remaining after applying the k operations.
 *
 * floor(x) is the greatest integer that is smaller than or equal to x (i.e., rounds x down).
 */

public class Solution {

    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (var pile : piles) pq.add(pile);

        while (--k >= 0) pq.add((pq.remove() + 1) / 2);

        int ans = 0;
        while (!pq.isEmpty()) ans += pq.remove();
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minStoneSum(new int[]{5,4,9}, 2) == 12;
        assert new Solution().minStoneSum(new int[]{4,3,6,7}, 3) == 12;
    }

}
