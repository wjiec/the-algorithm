package daily.d220114p373findkpairswithsmallestsums;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 373. Find K Pairs with Smallest Sums
 *
 * https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums/
 *
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 *
 * Define a pair (u, v) which consists of one element from the first array and
 * one element from the second array.
 *
 * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
 */

public class Solution {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(k, (a, b) ->
            nums1[a[0]] + nums2[a[1]] - nums1[b[0]] - nums2[b[1]]);

        for (int i = 0, e = Math.min(nums1.length, k); i < e; i++) {
            queue.add(new int[]{i, 0});
        }

        List<List<Integer>> ans = new ArrayList<>();
        while (k-- > 0 && !queue.isEmpty()) {
            int[] idx = queue.remove();
            ans.add(new ArrayList<>() {{
                add(nums1[idx[0]]);
                add(nums2[idx[1]]);
            }});

            if (idx[1] + 1 < nums2.length) {
                queue.add(new int[]{idx[0], idx[1] + 1});
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().kSmallestPairs(new int[]{1,7,11}, new int[]{2,4,6}, 3));
        System.out.println(new Solution().kSmallestPairs(new int[]{1,1,2}, new int[]{1,2,3}, 2));
        System.out.println(new Solution().kSmallestPairs(new int[]{1,2}, new int[]{3}, 3));
    }

}
