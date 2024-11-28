package weekly.w424.D;

import java.util.ArrayList;
import java.util.List;

/**
 * 3357. Minimize the Maximum Adjacent Element Difference
 *
 * https://leetcode.cn/contest/weekly-contest-424/problems/minimize-the-maximum-adjacent-element-difference/
 *
 * You are given an array of integers nums. Some values in nums are missing and are denoted by -1.
 *
 * You can choose a pair of positive integers (x, y) exactly once and replace each missing element with either x or y.
 *
 * You need to minimize the maximum absolute difference between adjacent elements of nums after replacements.
 *
 * Return the minimum possible difference.
 */

public class Solution {

    public int minDifference(int[] nums) {
        int l = -1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0 && nums[i - 1] > 0) {
                l = Math.max(l, Math.abs(nums[i] - nums[i - 1]) - 1);
            }
        }

        int r = 1_000_000_001;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (check(nums, mid)) r = mid;
            else l = mid;
        }
        return r;
    }

    // 判断是否可以通过使用数对 (x, y) 填充 nums 中的所有 -1, 使得最大值为 max
    private boolean check(int[] nums, int max) {
        int n = nums.length;
        // 对于数组中的每一个非负数 x 左右侧如果存在 -1, 则这个 -1 的范围要求是 [x - max, x + max]
        List<int[]> ranges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] != -1) continue;

            // 如果一个 -1 左右两侧都是正数, 则这个 -1 需要同时满足两边的要求 [a, -1, b]
            //  a: [a - max, a + max]; b: [b - max, a + max]
            if (i > 0 && i + 1 < n && nums[i - 1] > 0 && nums[i + 1] > 0) {
                int a = nums[i - 1], b = nums[i + 1];
                ranges.add(new int[]{Math.max(a - max, b - max), Math.min(a + max, b + max)});
            } else if (i > 0 && nums[i - 1] > 0) {
                ranges.add(new int[]{nums[i - 1] - max, nums[i - 1] + max});
            } else if (i + 1 < n && nums[i + 1] > 0) {
                ranges.add(new int[]{nums[i + 1] - max, nums[i + 1] + max});
            }
        }
        if (ranges.isEmpty()) return true;

        // 此时问题变成了: 找到至多两个数，使得所找到的数在所有的区间里(区间找点问题)
        //  - 首先按照区间的右端点进行排序, 为了尽可能多的使用一个数占据更多的区间
        //      - 所以我们第一个数 x 的选择是最小的区间右端点
        //  - 第二个数考虑需要满足 [a -1 -1 b] 的要求, 所以得尽可能靠近所选择的第一个数
        //      - 所以我们的第二个数 y 选择使用最大的区间左端点
        int x = Integer.MAX_VALUE, y = Integer.MIN_VALUE;
        for (var range : ranges) {
            x = Math.min(x, range[1]);
            y = Math.max(y, range[0]);
        }

        // 检查选择出来的 x 和 y 是否能覆盖所有的区间
        for (var range : ranges) {
            if (range[0] <= x && x <= range[1]) continue;
            if (range[0] <= y && y <= range[1]) continue;
            return false;
        }

        // 检查数组中的每一段 -1 是否可以用 (x, y) 填充满足最大值为 max
        for (int l = -1, r = 0; r < n; r++) {
            if (nums[r] < 0) continue;
            // 在数组起始位置或者是中间没有负数的
            if (l == -1 || l + 1 == r) { l = r; continue; }

            // 在 (l, r) 区间中是否可以使用 x 来填充
            boolean onlyUseX = Math.abs(nums[l] - x) <= max && Math.abs(nums[r] - x) <= max;
            // 在 (l, r) 区间中是否可以使用 y 来填充
            boolean onlyUseY = Math.abs(nums[l] - y) <= max && Math.abs(nums[r] - y) <= max;
            // 否则只能两边各填 x 或者 y 了
            boolean useBoth = Math.abs(x - y) <= max;

            // 如果以上任何一种条件都不满足, 则当前选择的 max 无法满足
            if (!(onlyUseX || onlyUseY || useBoth)) return false;

            // 修改当前查找的区间
            l = r;
        }

        return true;
    }

    public static void main(String[] args) {
        assert new Solution().minDifference(new int[]{-1,1,1000000000,-1,-1,1,-1,1000000000,-1}) == 999999999;
        assert new Solution().minDifference(new int[]{-1,1,1000000000,-1,-1,1,-1,1000000000,-1}) == 999999999;

        assert new Solution().minDifference(new int[]{14, -1, -1, 46}) == 11;
        assert new Solution().minDifference(new int[]{2, -1, 4, -1, -1, 6}) == 1;
        assert new Solution().minDifference(new int[]{-1, 1, 100, 200, 300, 400, 500, -1}) == 100;

        assert new Solution().minDifference(new int[]{1,12}) == 11;
        assert new Solution().minDifference(new int[]{1,2,-1,10,8}) == 4;
        assert new Solution().minDifference(new int[]{-1, -1, -1}) == 0;
        assert new Solution().minDifference(new int[]{-1,10,-1,8}) == 1;
    }

}
