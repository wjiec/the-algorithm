package weekly.w374.C;

/**
 * 2953. Count Complete Substrings
 *
 * https://leetcode.cn/contest/weekly-contest-374/problems/count-complete-substrings/
 *
 * You are given a string word and an integer k.
 *
 * A substring s of word is complete if:
 *
 * Each character in s occurs exactly k times.
 * The difference between two adjacent characters is at most 2. That is, for any
 * two adjacent characters c1 and c2 in s, the absolute difference in their positions
 * in the alphabet is at most 2.
 *
 * Return the number of complete substrings of word.
 *
 * A substring is a non-empty contiguous sequence of characters in a string.
 */

public class Solution {

    public int countCompleteSubstrings(String word, int k) {
        int ans = 0;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; ) {
            int end = i + 1;
            while (end < chars.length && Math.abs(chars[end] - chars[end - 1]) <= 2) end++;
            ans += countCompleteSubstrings(chars, i, end, k);
            i = end;
        }
        return ans;
    }

    private int countCompleteSubstrings(char[] chars, int s, int e, int k) {
        int ans = 0, n = e - s;
        for (int x = 1; x <= 26 && x * k <= n; x++) {
            int[] cnt = new int[128];
            for (int l = s, r = s; r < e; r++) {
                cnt[chars[r]]++;
                while (r + 1 - l > x * k) cnt[chars[l++]]--;
                if (r + 1 - l == x * k) {
                    boolean ok = true;
                    for (var c : cnt) {
                        if (c > 0 && c != k) {
                            ok = false;
                            break;
                        }
                    }
                    if (ok) ans++;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
