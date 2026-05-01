package weekly.w487.D;

/**
 * Q4. Longest Alternating Subarray After Removing At Most One Element
 *
 * https://leetcode.cn/contest/weekly-contest-487/problems/longest-alternating-subarray-after-removing-at-most-one-element/
 *
 * You are given an integer array nums.
 *
 * A subarray nums[l..r] is alternating if one of the following holds:
 *
 * nums[l] < nums[l + 1] > nums[l + 2] < nums[l + 3] > ...
 * nums[l] > nums[l + 1] < nums[l + 2] > nums[l + 3] < ...
 * In other words, if we compare adjacent elements in the subarray, then the comparisons alternate
 * between strictly greater and strictly smaller.
 *
 * You can remove at most one element from nums. Then, you select an alternating subarray from nums.
 *
 * Return an integer denoting the maximum length of the alternating subarray you can select.
 *
 * A subarray of length 1 is considered alternating.
 */

public class Solution {

    public int longestAlternating(int[] nums) {
        // 最多只能删除一个, 找到每一段的交替子数组, 然后尝试删掉中间的位置看看能不能连起来
        //  - 如果两段是 xxx [a] yyy, 如果删除 [a] 可以拼起来的前提是
        //      - xxx 的最后一段是 > 并且 yyy 的第一段是 <
        //      - xxx 的最后一段是 < 并且 yyy 的第一段是 >
        int[] pre = alternating(nums);

        reverse(nums);
        int[] suf = alternating(nums);
        reverse(suf); reverse(nums);

        // 枚举删除每个位置, 拼接前后的段
        int ans = 0, n = nums.length;
        for (var v : pre) ans = Math.max(ans, v);
        for (int i = 1; i < n - 1; i++) {
            // 前后相等则无法拼接
            if (nums[i - 1] == nums[i + 1]) continue;

            // 需要考虑 (i - 2, i - 1) (i - 1, i + 1) (i + 1, i + 2) 三组情况
            int c1 = i > 1 ? Integer.compare(nums[i - 2], nums[i - 1]) : 0;
            int c2 = Integer.compare(nums[i - 1], nums[i + 1]);
            int c3 = i + 2 < n ? Integer.compare(nums[i + 1], nums[i + 2]) : 0;

            // 可以拼接的情况是 < > < 或者 > < > 也就是前后相同, 中间与两边不同
            if (c1 == c3 && c1 == -c2) {
                ans = Math.max(ans, pre[i - 1] + suf[i + 1]);
            } else {
                // 否则我们只能考虑是否只拼接中间一段, 也就是 > < < 或者 < > > 的情况
                if (c1 == -c2) ans = Math.max(ans, pre[i - 1] + 1);
                if (c3 == -c2) ans = Math.max(ans, suf[i + 1] + 1);
            }
        }

        return ans;
    }

    private int[] alternating(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] == nums[i - 1]) ans[i] = 1;
            else if (i > 1 && nums[i - 2] != nums[i - 1] && (nums[i - 2] < nums[i - 1] != nums[i - 1] < nums[i])) ans[i] = ans[i - 1] + 1;
            else ans[i] = 2;
        }
        return ans;
    }
    
    private void reverse(int[] nums) {
        for (int l = 0, r = nums.length - 1; l < r; l++, r--) {
            var t = nums[l]; nums[l] = nums[r]; nums[r] = t;
        }
    }

    public static void main(String[] args) {
        assert new Solution().longestAlternating(new int[]{1,1,2,2,1}) == 3;

        assert new Solution().longestAlternating(new int[]{2,1,3,2}) == 4;
        assert new Solution().longestAlternating(new int[]{3,2,1,2,3,2,1}) == 4;
        assert new Solution().longestAlternating(new int[]{100000,100000}) == 1;
    }

}
