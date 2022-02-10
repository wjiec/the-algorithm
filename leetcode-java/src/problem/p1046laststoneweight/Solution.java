package problem.p1046laststoneweight;

import java.util.PriorityQueue;

/**
 * 1046. Last Stone Weight
 *
 * https://leetcode-cn.com/problems/last-stone-weight/
 *
 * We have a collection of stones, each stone has a positive integer weight.
 *
 * Each turn, we choose the two heaviest stones and smash them together.
 * Suppose the stones have weights x and y with x <= y.
 *
 * The result of this smash is:
 *
 * If x == y, both stones are totally destroyed;
 * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
 *
 * At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)
 */

public class Solution {

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (var n : stones) queue.add(n);

        while (queue.size() != 1) {
            queue.add(Math.abs(queue.remove() - queue.remove()));
        }
        return queue.remove();
    }

    public static void main(String[] args) {
        assert new Solution().lastStoneWeight(new int[]{2,7,4,1,8,1}) == 1;
    }

}
