package problem.p1658minimumoperationstoreducextozero;

/**
 * 1658. Minimum Operations to Reduce X to Zero
 *
 * https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero/
 *
 * You are given an integer array nums and an integer x. In one operation, you can
 * either remove the leftmost or the rightmost element from the array nums and
 * subtract its value from x. Note that this modifies the array for future operations.
 *
 * Return the minimum number of operations to reduce x to exactly 0 if it is possible, otherwise, return -1.
 */

public class Solution {

    public int minOperations(int[] nums, int x) {
        int n = nums.length, ans = nums.length + 1;
        int[] prefix = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }
        if (prefix[n] < x) return -1;
        if (prefix[n] == x) return n;

        int[] suffix = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            suffix[i] = suffix[i - 1] + nums[n - i];
        }

        ans = Math.min(ans, find(prefix, suffix, x));
        ans = Math.min(ans, find(suffix, prefix, x));
        return ans > n ? -1 : ans;
    }

    private int find(int[] a, int[] b, int x) {
        int l = 0, r = upper(b, x), ans = a.length + 1;
        while (l <= r) {
            int sum = a[l] + b[r];
            if (sum == x) { ans = Math.min(ans, l + r); l++; r++; }
            else if (sum < x) l++; else r--;
        }
        return ans;
    }

    private int upper(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    public static void main(String[] args) {
        assert new Solution().minOperations(new int[]{5,2,3,1,1}, 5) == 1;
        assert new Solution().minOperations(new int[]{8828,9581,49,9818,9974,9869,9991,10000,10000,10000,9999,9993,9904,8819,1231,6309}, 134365) == 16;
        assert new Solution().minOperations(new int[]{1,1,4,2,3}, 5) == 2;
        assert new Solution().minOperations(new int[]{5,6,7,8,9}, 4) == -1;
        assert new Solution().minOperations(new int[]{3,2,20,1,1,3}, 10) == 5;
    }

}
