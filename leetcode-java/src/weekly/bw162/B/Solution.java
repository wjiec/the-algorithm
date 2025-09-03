package weekly.bw162.B;

import java.util.Arrays;

/**
 * Q2. Minimum Removals to Balance Array
 *
 * https://leetcode.cn/contest/biweekly-contest-162/problems/minimum-removals-to-balance-array/
 *
 * You are given an integer array nums and an integer k.
 *
 * An array is considered balanced if the value of its maximum element is at most k times the minimum element.
 *
 * You may remove any number of elements from nums without making it empty.
 *
 * Return the minimum number of elements to remove so that the remaining array is balanced.
 *
 * Note: An array of size 1 is considered balanced as its maximum and
 * minimum are equal, and the condition always holds true.
 */

public class Solution {

    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums);

        int n = nums.length, ans = n;
        // 枚举我们需要保留的最小元素是 nums[i]
        for (int i = 0; i < nums.length; i++) {
            // 那数组内至多可以到 mx = nums[i] * k
            //  - 找到第一个大于 mx 的下标位置 j , 则从 [j, n) 都是需要去掉的, 总长度为 n - j
            ans = Math.min(ans, n - binarySearch(nums, (long) nums[i] * k) + i);
        }

        return ans;
    }

    // 找到大于 target 的第一个位置
    private int binarySearch(int[] nums, long target) {
        int l = -1, r = nums.length;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] <= target) l = mid;
            else r = mid;
        }
        return r;
    }

    public static void main(String[] args) {
        assert new Solution().minRemoval(new int[]{2,1,5}, 2) == 1;
        assert new Solution().minRemoval(new int[]{1,6,2,9}, 3) == 2;
        assert new Solution().minRemoval(new int[]{4,6}, 2) == 0;
    }

}
