package problem.p35searchinsertposition;

/**
 * 35. Search Insert Position
 *
 * https://leetcode-cn.com/problems/search-insert-position/
 *
 * Given a sorted array of distinct integers and a target value, return the index
 * if the target is found. If not, return the index where it would be if it were inserted in order.
 */

public class Solution {

    public int searchInsert(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }

        int s = 0, e = nums.length, i;
        while (s < e - 1) {
            i = (s + e) / 2;
            if (nums[i] == target) {
                return i;
            } else if (nums[i] < target) {
                s = i;
            } else {
                e = i;
            }
        }

        i = (s + e) / 2;
        return nums[i] >= target ? i : i + 1;
    }

    public static void main(String[] args) {
        assert new Solution().searchInsert(new int[]{1,3,5,6}, 5) == 2;
        assert new Solution().searchInsert(new int[]{1,3,5,6}, 2) == 1;
        assert new Solution().searchInsert(new int[]{1,3,5,6}, 7) == 4;
        assert new Solution().searchInsert(new int[]{1,3,5,6}, 0) == 0;
        assert new Solution().searchInsert(new int[]{}, 8) == 0;
        assert new Solution().searchInsert(new int[]{1}, 1) == 0;
        assert new Solution().searchInsert(new int[]{1,3,5,7,9}, 5) == 2;
        assert new Solution().searchInsert(new int[]{1,3,5,7,9}, 4) == 2;
        assert new Solution().searchInsert(new int[]{1,3,5,7,9}, 6) == 3;
        assert new Solution().searchInsert(new int[]{1,3,5,7,9}, 0) == 0;
        assert new Solution().searchInsert(new int[]{1,3,5,7,9}, 10) == 5;
    }

}
