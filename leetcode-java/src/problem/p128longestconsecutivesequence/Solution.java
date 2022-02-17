package problem.p128longestconsecutivesequence;

import java.util.HashSet;
import java.util.Set;

/**
 * 128. Longest Consecutive Sequence
 *
 * https://leetcode-cn.com/problems/longest-consecutive-sequence/
 *
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 *
 * You must write an algorithm that runs in O(n) time.
 */

public class Solution {

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (var n : nums) set.add(n);

        int ans = 0;
        for (int n : set) {
            if (!set.contains(n + 1)) {
                int count = 1;
                for (; set.contains(n - 1); n--) count++;
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().longestConsecutive(new int[]{100,4,200,1,3,2}) == 4;
        assert new Solution().longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1}) == 9;
    }

}
