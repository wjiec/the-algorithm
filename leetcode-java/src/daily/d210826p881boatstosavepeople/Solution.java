package daily.d210826p881boatstosavepeople;

import java.util.Arrays;

/**
 * 881. Boats to Save People
 *
 * https://leetcode-cn.com/problems/boats-to-save-people/
 *
 * You are given an array people where people[i] is the weight of the ith person,
 * and an infinite number of boats where each boat can carry a maximum weight of limit.
 *
 * Each boat carries at most two people at the same time,
 * provided the sum of the weight of those people is at most limit.
 *
 * Return the minimum number of boats to carry every given person.
 */

public class Solution {

    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);

        int ans = 0, l = 0, r = people.length - 1;
        while (l <= r) {
            if (people[l] + people[r] <= limit) {
                ++l;
            }
            --r;
            ++ans;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numRescueBoats(new int[]{1,2}, 3) == 1;
        assert new Solution().numRescueBoats(new int[]{3,2,2,1}, 3) == 3;
        assert new Solution().numRescueBoats(new int[]{3,5,3,4}, 5) == 4;
    }

}
