package offer2.p70;

/**
 * 剑指 Offer II 070. 排序数组中只出现一次的数字
 *
 * https://leetcode.cn/problems/skFtm2/
 *
 * 给定一个只包含整数的有序数组 nums ，每个元素都会出现两次，唯有一个数只会出现一次，请找出这个唯一的数字。
 *
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int singleNonDuplicate(int[] nums) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int mv = nums[mid];
            int lv = mid - 1 >= 0 ? nums[mid - 1] : -1;
            int rv = mid + 1 < nums.length ? nums[mid + 1] : -1;
            if (lv != mv && rv != mv) return mv;

            if (mv == lv) {
                if ((mid + 1) % 2 == 0) l = mid + 1;
                else r = mid;
            } else {
                if ((mid + 2) % 2 == 0) l = mid + 1;
                else r = mid;
            }
        }
        return nums[l];
    }

    public static void main(String[] args) {
        assert new Solution().singleNonDuplicate(new int[]{1,1,2,3,3,4,4,8,8}) == 2;
        assert new Solution().singleNonDuplicate(new int[]{3,3,7,7,10,11,11}) == 10;

        assert new Solution().singleNonDuplicate(new int[]{1}) == 1;
        assert new Solution().singleNonDuplicate(new int[]{1,2,2}) == 1;
        assert new Solution().singleNonDuplicate(new int[]{1,2,2,3,3}) == 1;
        assert new Solution().singleNonDuplicate(new int[]{2,2,3}) == 3;
        assert new Solution().singleNonDuplicate(new int[]{1,1,2,2,3}) == 3;
    }

}
