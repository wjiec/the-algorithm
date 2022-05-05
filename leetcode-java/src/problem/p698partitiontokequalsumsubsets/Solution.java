package problem.p698partitiontokequalsumsubsets;

import common.TODO;

import java.util.Arrays;

/**
 * 698. Partition to K Equal Sum Subsets
 *
 * https://leetcode-cn.com/problems/partition-to-k-equal-sum-subsets/
 *
 * Given an integer array nums and an integer k, return true if it is possible to divide
 * this array into k non-empty subsets whose sums are all equal.
 */

public class Solution {

    @TODO
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (k == 1) return true;
        if (nums.length < k) return false;

        int sum = 0;
        Arrays.sort(nums);
        for (var n : nums) sum += n;
        if (sum % k != 0) return false;

        int avg = sum / k;
        if (avg < nums[nums.length - 1]) return false;

        int size = 1 << nums.length;
        int[] memo = new int[size];
        boolean[] state = new boolean[size];
        state[0] = true;

        for (int i = 0; i < size; i++) {
            // 如果无法推导到当前状态，则跳过这个状态
            if (!state[i]) continue;

            // 尝试数组中的每一个数字
            for (int j = 0; j < nums.length; j++) {
                // 如果当前数字已经选择过了，则直接跳过
                if ((i & (1 << j)) != 0) continue;

                // 获取下一个状态的整数表示
                int next = i | (1 << j);
                // 已经推导过这个状态了就直接跳过
                if (state[next]) continue;

                // memo[i] 表示当前凑到的X组数和一个未完成的分组
                // memo[i] % avg 获取到当前这个未完成的分组
                // 如果我们选择的这个数可以凑成，那就使用这个数
                // 如果当前数无法凑成，说明之后更大的数肯定也无法凑成，直接跳过
                if ((memo[i] % avg) + nums[j] <= avg) {
                    memo[next] = memo[i] + nums[j];
                    state[next] = true;
                } else break;
            }
        }

        // 当所有的位都被选中时，是否可以推导得到 true
        return state[size - 1];
    }

    public static void main(String[] args) {
        assert !new Solution().canPartitionKSubsets(new int[]{3,3,10,2,6,5,10,6,8,3,2,1,6,10,7,2}, 6);

        assert new Solution().canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4);
        assert !new Solution().canPartitionKSubsets(new int[]{1,2,3,4}, 3);
    }

}
