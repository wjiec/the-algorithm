package offer2.p60;

import common.Checker;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 剑指 Offer II 060. 出现频率最高的 k 个数字
 *
 * https://leetcode.cn/problems/g5c51o/
 *
 * 给定一个整数数组 nums 和一个整数 k ，请返回其中出现频率前 k 高的元素。可以按 任意顺序 返回答案。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int[] topKFrequent(int[] nums, int k) {
        Arrays.sort(nums);

        // [value, count]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v[1]));
        for (int i = 0, prev = 0; i <= nums.length; i++) {
            if (i == nums.length || nums[i] != nums[prev]) {
                pq.add(new int[]{nums[prev], i - prev});
                if (pq.size() > k) pq.remove();
                prev = i;
            }
        }

        int[] ans = new int[pq.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = pq.remove()[0];
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().topKFrequent(new int[]{1,2}, 2), new int[]{1,2});

        assert Checker.anyOrder(new Solution().topKFrequent(new int[]{1,1,1,2,2,3}, 2), new int[]{1,2});
        assert Checker.anyOrder(new Solution().topKFrequent(new int[]{1}, 1), new int[]{1});
    }

}
