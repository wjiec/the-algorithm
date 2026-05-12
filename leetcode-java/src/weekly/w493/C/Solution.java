package weekly.w493.C;

/**
 * Q3. Longest Arithmetic Sequence After Changing At Most One Element
 *
 * https://leetcode.cn/contest/weekly-contest-493/problems/longest-arithmetic-sequence-after-changing-at-most-one-element/
 *
 * You are given an integer array nums.
 *
 * A subarray is arithmetic if the difference between consecutive elements in the subarray is constant.
 *
 * You can replace at most one element in nums with any integer. Then, you select an arithmetic subarray from nums.
 *
 * Return an integer denoting the maximum length of the arithmetic subarray you can select.
 */

public class Solution {

    public int longestArithmetic(int[] nums) {
        int ans = find(nums);
        if (ans == nums.length) return ans;

        for (int l = 0, r = nums.length - 1; l < r; l++, r--) {
            var t = nums[l]; nums[l] = nums[r]; nums[r] = t;
        }
        return Math.max(ans, find(nums));
    }
    
    private int find(int[] nums) {
        int n = nums.length;
        // 替换一个元素之后的等差子数组
        //  - 找到所有的等差子数组, 并考虑如何将其进行合并
        record Range(int l, int r, int d, int s, int e) {}
        // 枚举每一个以 i 开头的子数组的公差以及长度
        Range[] ranges = new Range[n - 1];
        for (int l = 0; l < n - 1; l++) {
            // 尝试从前一个区间里转移
            if (l > 0 && ranges[l - 1].r - 1 > l) {
                ranges[l] = new Range(l, ranges[l - 1].r, ranges[l - 1].d, nums[l], ranges[l - 1].e);
                continue;
            }

            int r = l + 1, d = nums[r] - nums[l];
            while (r + 1 < n && nums[r + 1] - nums[r] == d) r++;
            // 此时我们找到了一个新区间
            ranges[l] = new Range(l, r, d, nums[l], nums[r]);
        }

        int ans = 0;
        // 尝试为每一个区间进行拼接
        for (int i = 0; i < ranges.length; i++) {
            var curr = ranges[i]; int cl = curr.r - curr.l + 1; i = curr.r - 1;
            // 当前区间的长度就是一个等差数列, 如果后面还有内容, 那么我们必然可以修改后面一个区间的第一个元素使得长度+1
            ans = Math.max(ans, cl + (curr.r + 1 < n ? 1 : 0));
            // 如果当前区间后面没有区间的话, 那就可以终止了
            if (curr.r + 1 >= ranges.length) continue;

            var next = ranges[curr.r + 1];
            // 如果我们考虑修改 next.l, 此时有两种可能
            //  - 变成 [..curr..] [next.l] [next.l+1] 的形式: [next.l+1] == curr.e + curr.d + curr.d
            if (nums[next.l + 1] == curr.e + 2 * curr.d) {
                ans = Math.max(ans, cl + 2);
                //  - 变成 [..curr..] [next.l] [..next2..] 的形式: next2.e == curr.e + curr.d + n2l * curr.d
                //      - 同时需要满足 next2.s == curr.e + curr.d + curr.d
                if (next.l + 1 < ranges.length) {
                    var next2 = ranges[next.l + 1]; int n2l = next2.r - next2.l + 1;
                    if (next2.e == curr.e + (n2l + 1) * curr.d) ans = Math.max(ans, cl + n2l + 1);
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().longestArithmetic(new int[]{90033,1535,13037,24539,842}) == 4;
        assert new Solution().longestArithmetic(new int[]{19334,19334,24488,58213,19334,19334,19334,19334,19334,19334}) == 7;

        assert new Solution().longestArithmetic(new int[]{45165,45154,42337,45132,45121,45110}) == 6;
        assert new Solution().longestArithmetic(new int[]{1,1,2,3}) == 4;
        assert new Solution().longestArithmetic(new int[]{1,1,2,1}) == 4;
        assert new Solution().longestArithmetic(new int[]{1,1,1,1}) == 4;
        assert new Solution().longestArithmetic(new int[]{9,7,5,10,1}) == 5;
        assert new Solution().longestArithmetic(new int[]{1,2,6,7}) == 3;
    }

}
