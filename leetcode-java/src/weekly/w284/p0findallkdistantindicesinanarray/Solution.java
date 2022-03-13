package weekly.w284.p0findallkdistantindicesinanarray;

import java.util.*;

/**
 * 6031. Find All K-Distant Indices in an Array
 *
 * https://leetcode-cn.com/contest/weekly-contest-284/problems/find-all-k-distant-indices-in-an-array/
 *
 * You are given a 0-indexed integer array nums and two integers key and k. A k-distant index is an
 * index i of nums for which there exists at least one index j such that |i - j| <= k and nums[j] == key.
 *
 * Return a list of all k-distant indices sorted in increasing order.
 */

public class Solution {

    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == key) queue.add(i);
        }

        Set<Integer> set = new TreeSet<>();
        while (!queue.isEmpty()) {
            int index = queue.remove();
            for (int i = 0; i <= k; i++) {
                if (index + i < nums.length) set.add(index + i);
                if (index - i >= 0) set.add(index - i);
            }
        }

        return new ArrayList<>(set);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findKDistantIndices(new int[]{3,4,9,1,3,9,5}, 9, 1));
        System.out.println(new Solution().findKDistantIndices(new int[]{2,2,2,2,2}, 2, 2));
    }

}
