package weekly.w382.A;

/**
 * 3019. Number of Changing Keys
 *
 * https://leetcode.cn/contest/weekly-contest-382/problems/number-of-changing-keys/
 *
 * You are given a 0-indexed string s typed by a user. Changing a key is defined as
 * using a key different from the last used key. For example, s = "ab" has a change
 * of a key while s = "bBBb" does not have any.
 *
 * Return the number of times the user had to change the key.
 *
 * Note: Modifiers like shift or caps lock won't be counted in changing the key
 * that is if a user typed the letter 'a' and then the letter 'A' then it will
 * not be considered as a changing of key.
 */

public class Solution {

    public int countKeyChanges(String s) {
        s = s.toLowerCase();
        int ans = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i - 1)) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
