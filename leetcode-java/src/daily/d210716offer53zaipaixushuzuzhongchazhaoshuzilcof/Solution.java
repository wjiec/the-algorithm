package daily.d210716offer53zaipaixushuzuzhongchazhaoshuzilcof;

/**
 * 剑指 Offer 53 - I. 在排序数组中查找数字 I
 *
 * https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/
 *
 * 统计一个数字在排序数组中出现的次数。
 */

public class Solution {

    public int search(int[] nums, int target) {
        int l = 0, r = nums.length, idx = -1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid;
            } else {
                idx = mid;
                break;
            }
        }

        if (idx != -1) {
            for (l = idx; l >= 0 && nums[l] == target; l--);
            for (r = idx; r < nums.length && nums[r] == target; r++);
            return r - l - 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        assert new Solution().search(new int[]{5,7,7,8,8,10}, 8) == 2;
        assert new Solution().search(new int[]{5,7,7,8,8,10}, 6) == 0;
    }

}
