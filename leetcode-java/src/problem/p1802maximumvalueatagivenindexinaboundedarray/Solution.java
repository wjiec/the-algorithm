package problem.p1802maximumvalueatagivenindexinaboundedarray;

/**
 * 1802. Maximum Value at a Given Index in a Bounded Array
 *
 * https://leetcode.cn/problems/maximum-value-at-a-given-index-in-a-bounded-array/
 *
 * You are given three positive integers: n, index, and maxSum. You want to construct
 * an array nums (0-indexed) that satisfies the following conditions:
 *
 * nums.length == n
 * nums[i] is a positive integer where 0 <= i < n.
 * abs(nums[i] - nums[i+1]) <= 1 where 0 <= i < n-1.
 * The sum of all the elements of nums does not exceed maxSum.
 * nums[index] is maximized.
 * Return nums[index] of the constructed array.
 *
 * Note that abs(x) equals x if x >= 0, and -x otherwise.
 */

public class Solution {

    // sum(nums) <= maxSum
    // abs(nums[i] - nums[i+1]) <= 1
    public int maxValue(int n, int index, int maxSum) {
        // 首先需要保证所有数都是正整数
        maxSum -= n; // 每个位置都填1

        int ans = 1;
        // 从 index 开始往两边递增, 生长成一个三角形
        for (int l = index, r = index; l > 0 || r < n - 1;) {
            int len = r - l + 1;
            if (maxSum >= len) { // 开始填充三角形
                maxSum -= len; ans++;
                if (l - 1 >= 0) l--;
                if (r + 1 < n) r++;
            } else break; // 不够填充了
        }
        // 如果整个区域内都填充完了, 那就所有格子一起向上叠加
        // 如果是因为不够填充而退出的, 那这里肯定会得到 0
        ans += maxSum / n;

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxValue(2, 1, 865959216) == 432979608;

        assert new Solution().maxValue(4, 2, 6) == 2;
        assert new Solution().maxValue(6, 1, 10) == 3;
    }

}
