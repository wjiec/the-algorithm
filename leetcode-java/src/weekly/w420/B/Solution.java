package weekly.w420.B;

/**
 * 3325. Count Substrings With K-Frequency Characters I
 *
 * https://leetcode.cn/contest/weekly-contest-420/problems/count-substrings-with-k-frequency-characters-i/
 *
 * Given a string s and an integer k, return the total number of substrings of s
 * where at least one character appears at least k times.
 */

public class Solution {

    public int numberOfSubstrings(String s, int k) {
        int n = s.length();
        int[][] diff = new int[n + 1][26];
        for (int i = 1; i <= n; i++) {
            diff[i][s.charAt(i - 1) - 'a']++;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 26; j++) {
                diff[i][j] += diff[i - 1][j];
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (check(diff[i], diff[j], k)) ans++;
            }
        }

        return ans;
    }

    private boolean check(int[] a, int[] b, int k) {
        for (int i = 0; i < a.length; i++) {
            if (b[i] - a[i] >= k) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().numberOfSubstrings("abacb", 2) == 4;
        assert new Solution().numberOfSubstrings("abcde", 1) == 15;

        assert new Solution().numberOfSubstrings("hxccgfp", 1) == 28;
    }

}
