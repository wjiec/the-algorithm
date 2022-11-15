package offer2.p61;

import common.Checker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 剑指 Offer II 061. 和最小的 k 个数对
 *
 * https://leetcode.cn/problems/qn8gGX/
 *
 * 给定两个以升序排列的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 *
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 *
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 */

public class Solution {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(v -> nums1[v[0]] + nums2[v[1]]));
        for (int i = 0; i < Math.min(nums1.length, k); i++) pq.add(new int[]{i, 0});

        List<List<Integer>> ans = new ArrayList<>();
        while (ans.size() < k && !pq.isEmpty()) {
            int[] curr = pq.remove();
            ans.add(List.of(nums1[curr[0]], nums2[curr[1]]));

            if (++curr[1] < nums2.length) pq.add(curr);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().kSmallestPairs(new int[]{1,7,11}, new int[]{2,4,6}, 3), List.of(
            List.of(1,2), List.of(1,4), List.of(1,6)
        ));

        assert Checker.check(new Solution().kSmallestPairs(new int[]{1,1,2}, new int[]{1,2,3}, 2), List.of(
            List.of(1,1), List.of(1,1)
        ));

        assert Checker.check(new Solution().kSmallestPairs(new int[]{1,2}, new int[]{3}, 3), List.of(
            List.of(1,3), List.of(2,3)
        ));
    }

}
