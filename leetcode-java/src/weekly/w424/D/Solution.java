package weekly.w424.D;

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
        int[] guards = new int[nums.length + 2];
        System.arraycopy(nums, 0, guards, 1, nums.length);
        for (int i = 1; i < guards.length - 1; i++) {
            if (guards[i] != -1) {
                guards[0] = guards[i];
                break;
            }
        }
        for (int i = guards.length - 2; i > 0; i--) {
            if (guards[i] != -1) {
                guards[guards.length - 1] = guards[i];
                break;
            }
        }

        int l = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] != -1 && nums[i] != -1) {
                l = Math.max(l, Math.abs(nums[i - 1] - nums[i]));
            }
        }

        int r = 1_000_000_007, ans = l;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (check(guards, mid)) {
                ans = mid; r = mid;
            } else l = mid + 1;
        }

        System.out.println(ans);
        return ans;
    }

    // 判断是否可以通过使用数对 (x, y) 填充 nums 中的所有 -1, 使得最大值为 max
    private boolean check(int[] nums, int max) {
        // 首先判断所有共用 -1 的情况下是否满足
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == -1 && nums[i - 1] != -1 && nums[i + 1] != -1) {
                int mid = (nums[i - 1] + nums[i + 1]) / 2;
                if (Math.abs(mid - nums[i - 1]) > max) return false;
                if (Math.abs(nums[i + 1] - mid) > max) return false;
            }
        }

        // 求每个 -1 旁边非 -1 的数在 max 下的要求范围
        // 如果所有的要求范围都可以使用两个子范围满足, 则说明该 max 是可以满足要求的
        boolean first = true;
        int l = Integer.MIN_VALUE, r = Integer.MAX_VALUE;
        for (int i = 1, p = 0; i < nums.length - 1; i++) {
            if (nums[i] != -1 && (nums[i - 1] == -1 || nums[i + 1] == -1)) {
                l = Math.max(l, nums[i] - max);
                r = Math.min(r, nums[i] + max);
                if (l > r) {
                    if (!first) return false;
                    first = false;

                    l = nums[i] - max;
                    r = nums[i] + max;
                    if (Math.abs(l - nums[p]) > max) return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        assert new Solution().minDifference(new int[]{14, -1, -1, 46}) == 11;
        assert new Solution().minDifference(new int[]{2, -1, 4, -1, -1, 6}) == 1;

        assert new Solution().minDifference(new int[]{1,12}) == 11;
        assert new Solution().minDifference(new int[]{1,2,-1,10,8}) == 4;
        assert new Solution().minDifference(new int[]{-1, -1, -1}) == 0;
        assert new Solution().minDifference(new int[]{-1,10,-1,8}) == 1;
    }

}
