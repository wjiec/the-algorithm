package problem.p1664waystomakeafairarray;

/**
 * 1664. Ways to Make a Fair Array
 *
 * https://leetcode.cn/problems/ways-to-make-a-fair-array/
 *
 * You are given an integer array nums. You can choose exactly one index (0-indexed) and remove the element.
 * Notice that the index of the elements may change after the removal.
 *
 * For example, if nums = [6,1,7,4,1]:
 *
 * Choosing to remove index 1 results in nums = [6,7,4,1].
 * Choosing to remove index 2 results in nums = [6,1,4,1].
 * Choosing to remove index 4 results in nums = [6,1,7,4].
 * An array is fair if the sum of the odd-indexed values equals the sum of the even-indexed values.
 *
 * Return the number of indices that you could choose such that after the removal, nums is fair.
 */

public class Solution {

    public int waysToMakeFair(int[] nums) {
        int allOdd = 0, allEven = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) allEven += nums[i];
            else allOdd += nums[i];
        }

        int ans = 0, currOdd = 0, currEven = 0;
        for (int i = 0; i < nums.length; i++) {
            // 把当前元素删除, 会导致之后的下标偶数变
            // 奇数, 奇数变偶数
            if (i % 2 == 0) {
                allEven -= nums[i];
                if (currOdd + allEven == currEven + allOdd) ans++;
                currEven += nums[i];
            } else {
                allOdd -= nums[i];
                if (currOdd + allEven == currEven + allOdd) ans++;
                currOdd += nums[i];
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().waysToMakeFair(new int[]{2,1,6,4}) == 1;
        assert new Solution().waysToMakeFair(new int[]{1,1,1}) == 3;
        assert new Solution().waysToMakeFair(new int[]{1,2,3}) == 0;
    }

}
