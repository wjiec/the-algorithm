package daily.d211227p825friendsofappropriateages;

import java.util.Arrays;

/**
 * 825. Friends Of Appropriate Ages
 *
 * https://leetcode-cn.com/problems/friends-of-appropriate-ages/
 *
 * There are n persons on a social media website. You are given an integer array ages
 * where ages[i] is the age of the ith person.
 *
 * A Person x will not send a friend request to a person y (x != y) if any of the following conditions is true:
 *
 * age[y] <= 0.5 * age[x] + 7
 * age[y] > age[x]
 * age[y] > 100 && age[x] < 100
 *
 * Otherwise, x will send a friend request to y.
 *
 * Note that if x sends a request to y, y will not necessarily send a request to x.
 * Also, a person will not send a friend request to themself.
 *
 * Return the total number of friend requests made.
 */

public class Solution {

    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int l = 0, r = 0, ans = 0;
        for (var age : ages) {
            if (age < 15) continue;
            while (ages[l] <= 0.5 * age + 7) l++;
            while (r + 1 < ages.length && ages[r + 1] <= age) r++;
            ans += r - l;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numFriendRequests(new int[]{16,16}) == 2;
        assert new Solution().numFriendRequests(new int[]{16,17,18}) == 2;
        assert new Solution().numFriendRequests(new int[]{20,30,100,110,120}) == 3;
    }

}
