package problem.p164maximumgap;

import java.util.Arrays;

/**
 * 164. Maximum Gap
 *
 * https://leetcode.cn/problems/maximum-gap/
 *
 * Given an integer array nums, return the maximum difference between two
 * successive elements in its sorted form. If the array contains
 * less than two elements, return 0.
 *
 * You must write an algorithm that runs in linear time and uses linear extra space.
 */

public class Solution {

    public int maximumGap(int[] nums) {
        if (nums.length < 2) return 0;

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (var num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int step = Math.max(1, (max - min) / (nums.length - 1));
        int bucketSize = (max - min) / step + 1;

        int[] minBucket = new int[bucketSize];
        int[] maxBucket = new int[bucketSize];
        Arrays.fill(minBucket, -1);
        Arrays.fill(maxBucket, -1);

        for (int num : nums) {
            int bucket = (num - min) / step;
            if (maxBucket[bucket] == -1) {
                minBucket[bucket] = maxBucket[bucket] = num;
            } else {
                minBucket[bucket] = Math.min(minBucket[bucket], num);
                maxBucket[bucket] = Math.max(maxBucket[bucket], num);
            }
        }

        int ans = 0, prev = -1;
        for (int i = 0; i < bucketSize; i++) {
            if (maxBucket[i] == -1) continue;

            if (prev != -1) {
                ans = Math.max(ans, minBucket[i] - maxBucket[prev]);
            }
            prev = i;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maximumGap(new int[]{1,3,100}) == 97;

        assert new Solution().maximumGap(new int[]{3,6,9,1}) == 3;
        assert new Solution().maximumGap(new int[]{10}) == 0;
    }

}
