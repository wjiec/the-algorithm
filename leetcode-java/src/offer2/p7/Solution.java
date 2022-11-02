package offer2.p7;

import common.Checker;
import common.TODO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer II 007. 数组中和为 0 的三个数
 *
 * https://leetcode.cn/problems/1fGaJU/
 *
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。
 *
 * 请你返回所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    @TODO
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        // 计算 a + b + c = 0, 这里枚举每一个 a
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break; // 如果 a > 0 则 a + b + c 必大于 0
            if (i != 0 && nums[i] == nums[i - 1]) continue;

            int target = -nums[i];
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                if (nums[l] + nums[r] == target) {
                    ans.add(List.of(nums[i], nums[l], nums[r]));

                    l++; r--;
                    while (l < r && nums[l] == nums[l - 1]) l++;
                    while (l < r && nums[r] == nums[r + 1]) r--;
                } else if (nums[l] + nums[r] < target) {
                    l++;
                } else {
                    r--;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().threeSum(new int[]{-1,0,1,2,-1,-4}),
            List.of(List.of(-1,-1,2), List.of(-1,0,1)));
    }

}
