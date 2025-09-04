package weekly.w461.B;

/**
 * Q2. Maximum Balanced Shipments
 *
 * https://leetcode.cn/contest/weekly-contest-461/problems/maximum-balanced-shipments/
 *
 * You are given an integer array weight of length n, representing the
 * weights of n parcels arranged in a straight line. A shipment is defined
 * as a contiguous subarray of parcels. A shipment is considered balanced
 * if the weight of the last parcel is strictly less than the maximum weight
 * among all parcels in that shipment.
 *
 * Select a set of non-overlapping, contiguous, balanced shipments such that each
 * parcel appears in at most one shipment (parcels may remain unshipped).
 *
 * Return the maximum possible number of balanced shipments that can be formed.
 */

public class Solution {

    // 找到最多的不重叠连续子数组, 每个子数组要求最后一个数严格小于子数组的最大值
    public int maxBalancedShipments(int[] weight) {
        int n = weight.length, ans = 0;
        // f[i] 预处理从位置 i 开始选, 第一个满足要求的位置是哪里
        //  - 如果是递减的, 那么就是 i + 1
        //  - 如果出现递增, 那么就是 f[i + 1]
        // 从末尾开始处理
        int[] f = new int[n]; f[n - 1] = n;
        for (int i = n - 2; i >= 0; i--) {
            if (weight[i] > weight[i + 1]) f[i] = i + 1;
            else f[i] = f[i + 1];
        }

        // 贪心找到最短的下一个小值
        for (int i = 0; i < n; ) {
            if (f[i] == n) break;
            i = f[i] + 1; ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxBalancedShipments(new int[]{1,2,3}) == 0;
        assert new Solution().maxBalancedShipments(new int[]{1000,999,998}) == 1;

        assert new Solution().maxBalancedShipments(new int[]{2,5,1,4,3}) == 2;
        assert new Solution().maxBalancedShipments(new int[]{4,4}) == 0;
    }

}
