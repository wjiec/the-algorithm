package problem1.java;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(Arrays.toString(solution.twoSum(new int[] {2, 7, 11, 15}, 9))); // 0, 1
        System.out.println(Arrays.toString(solution.twoSum(new int[] {2, 7, 2, 15}, 4))); // 0, 2
        System.out.println(Arrays.toString(solution.twoSum(new int[] {1, 2, 2, 15}, 4))); // 0, 2
    }

    private static class Solution {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> values = new HashMap<>();

            values.put(nums[0], 0);
            for (int i = 1; i < nums.length; i++) {
                if (values.containsKey(target - nums[i])) {
                    return new int[]{values.get(target - nums[i]), i};
                }
                values.put(nums[i], i);
            }

            return new int[]{0, 0};
        }
    }

}
