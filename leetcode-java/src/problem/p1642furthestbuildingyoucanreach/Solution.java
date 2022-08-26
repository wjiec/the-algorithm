package problem.p1642furthestbuildingyoucanreach;

import java.util.PriorityQueue;

/**
 * 1642. Furthest Building You Can Reach
 *
 * https://leetcode.cn/problems/furthest-building-you-can-reach/
 *
 * You are given an integer array heights representing the heights of buildings, some bricks, and some ladders.
 *
 * You start your journey from building 0 and move to the next building by possibly using bricks or ladders.
 *
 * While moving from building i to building i+1 (0-indexed),
 *
 * If the current building's height is greater than or equal to the next building's
 * height, you do not need a ladder or bricks.
 * If the current building's height is less than the next building's height, you can
 * either use one ladder or (h[i+1] - h[i]) bricks.
 * Return the furthest building index (0-indexed) you can reach if you use the given
 * ladders and bricks optimally.
 */

public class Solution {

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        // 所有需要梯子或砖块的的高度
        // 我们选择对最大的 ladders 个使用梯子
        // 剩下的需要使用砖块铺起来, 直到砖块不够用
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0, n = heights.length - 1; i < n; i++) {
            if (heights[i] < heights[i + 1]) {
                pq.add(heights[i + 1] - heights[i]);
                if (pq.size() > ladders) bricks -= pq.remove();
                if (bricks < 0) return i;
            }
        }
        return heights.length - 1;
    }

    public static void main(String[] args) {
        assert new Solution().furthestBuilding(new int[]{4,2,7,6,9,14,12}, 5, 1) == 4;
        assert new Solution().furthestBuilding(new int[]{4,12,2,7,3,18,20,3,19}, 10, 2) == 7;
        assert new Solution().furthestBuilding(new int[]{14,3,19,3}, 17, 0) == 3;
    }

}
