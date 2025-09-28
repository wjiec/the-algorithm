package weekly.w464.B;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Q2. Partition Array Into K-Distinct Groups
 *
 * https://leetcode.cn/contest/weekly-contest-464/problems/partition-array-into-k-distinct-groups/
 *
 * You are given an integer array nums and an integer k.
 *
 * Your task is to determine whether it is possible to partition
 * all elements of nums into one or more groups such that:
 *
 * Each group contains exactly k elements.
 * All elements in each group are distinct.
 * Each element in nums must be assigned to exactly one group.
 *
 * Return true if such a partition is possible, otherwise return false.
 */

public class Solution {

    public boolean partitionArray(int[] nums, int k) {
        // 每个组恰好包含 k 个元素且每个元素必须被分配到恰好一个组中
        if (nums.length % k != 0) return false;

        // 每组中的元素互不相同
        int gs = nums.length / k;
        Map<Integer, Integer> g = new HashMap<>();
        for (var v : nums) if (g.merge(v, 1, Integer::sum) > gs) return false;
        return true;
    }

    private static class Optimization {
        public boolean partitionArray(int[] nums, int k) {
            int n = nums.length;
            if (n % k != 0) return false;

            Arrays.sort(nums); int gs = n / k;
            for (int r = 1, l = 0; r <= n; r++) {
                if (r == n || nums[r] != nums[l]) {
                    if (r - l > gs) return false;
                    l = r;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
    }

}
