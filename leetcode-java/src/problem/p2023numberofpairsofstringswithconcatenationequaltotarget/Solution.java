package problem.p2023numberofpairsofstringswithconcatenationequaltotarget;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 2023. Number of Pairs of Strings With Concatenation Equal to Target
 *
 * https://leetcode.cn/problems/number-of-pairs-of-strings-with-concatenation-equal-to-target/
 *
 * Given an array of digit strings nums and a digit string target, return the number of
 * pairs of indices (i, j) (where i != j) such that the concatenation of nums[i] + nums[j]
 * equals target.
 */

public class Solution {

    public int numOfPairs(String[] nums, String target) {
        Arrays.sort(nums, Comparator.comparingInt(String::length));
        int ans = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            int left = nums[i].length();
            int right = target.length() - left;
            for (int j = 0; j < n; j++) {
                if (nums[j].length() > right) break;
                if (i == j || nums[j].length() < right) continue;

                if (target.equals(nums[i] + nums[j])) ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numOfPairs(new String[]{"777","7","77","77"}, "7777") == 4;
        assert new Solution().numOfPairs(new String[]{"123","4","12","34"}, "1234") == 2;
        assert new Solution().numOfPairs(new String[]{"1","1","1"}, "11") == 6;
    }

}
