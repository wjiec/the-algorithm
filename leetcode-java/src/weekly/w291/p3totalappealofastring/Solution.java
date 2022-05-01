package weekly.w291.p3totalappealofastring;

import java.util.Arrays;

/**
 * 6050. Total Appeal of A String
 *
 * https://leetcode-cn.com/contest/weekly-contest-291/problems/total-appeal-of-a-string/
 *
 * The appeal of a string is the number of distinct characters found in the string.
 *
 * For example, the appeal of "abbca" is 3 because it has 3 distinct characters: 'a', 'b', and 'c'.
 * Given a string s, return the total appeal of all of its substrings.
 *
 * A substring is a contiguous sequence of characters within a string.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public long appealSum(String s) {
        int[] map = new int[128];
        Arrays.fill(map, -1);

        long ans = 0;
        for (int i = 0, n = s.length(); i < n; i++) {
            long cur = n - i;
            if (map[s.charAt(i)] != -1) {
                cur += (long) (i - map[s.charAt(i)] - 1) * (n - i);
            } else cur += (long) i * (n - i);
            ans += cur;
            map[s.charAt(i)] = i;
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().appealSum("abbca") == 28;
        assert new Solution().appealSum("code") == 20;
    }

}
