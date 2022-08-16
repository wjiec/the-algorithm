package problem.p1509minimumdifferencebetweenlargestandsmallestvalueinthreemoves;

import java.util.PriorityQueue;

/**
 * 1509. Minimum Difference Between Largest and Smallest Value in Three Moves
 *
 * https://leetcode.cn/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves/
 *
 * You are given an integer array nums. In one move, you can choose one element of nums and change it by any value.
 *
 * Return the minimum difference between the largest and smallest value of nums after performing at most three moves.
 */

public class Solution {

    public int minDifference(int[] nums) {
        if (nums.length <= 4) return 0;

        PriorityQueue<Integer> max = new PriorityQueue<>();
        PriorityQueue<Integer> min = new PriorityQueue<>((a, b) -> b - a);
        for (var n : nums) {
            min.add(n); max.add(n);
            if (max.size() > 4) max.remove();
            if (min.size() > 4) min.remove();
        }

        // mi0, mi1, mi2, mi3, ..., mx0, mx1, mx2, mx3
        int mi3 = min.remove(), mi2 = min.remove(), mi1 = min.remove(), mi0 = min.remove();
        int mx0 = max.remove(), mx1 = max.remove(), mx2 = max.remove(), mx3 = max.remove();

        return Math.min(Math.min(mx3 - mi3, mx2 - mi2), Math.min(mx1 - mi1, mx0 - mi0));
    }

    public static void main(String[] args) {
        assert new Solution().minDifference(new int[]{82,81,95,75,20}) == 1;

        assert new Solution().minDifference(new int[]{5,3,2,4}) == 0;
        assert new Solution().minDifference(new int[]{1,5,0,10,14}) == 1;
    }

}
