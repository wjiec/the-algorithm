package weekly.w390.A;

/**
 * 3090. Maximum Length Substring With Two Occurrences
 *
 * https://leetcode.cn/contest/weekly-contest-390/problems/maximum-length-substring-with-two-occurrences/
 *
 * Given a string s, return the maximum length of a substring
 * such that it contains at most two occurrences of each character.
 */

public class Solution {

    public int maximumLengthSubstring(String s) {
        int ans = 0, n = s.length();
        char[] chars = s.toCharArray();
        int[] count = new int[128];
        for (int l = 0, r = 0; r < n; r++) {
            count[chars[r]]++;
            while (count[chars[r]] > 2) count[chars[l++]]--;
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
