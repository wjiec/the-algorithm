package weekly.bw135.B;

/**
 * 3223. Minimum Length of String After Operations
 *
 * https://leetcode.cn/contest/biweekly-contest-135/problems/minimum-length-of-string-after-operations/
 *
 * You are given a string s.
 *
 * You can perform the following process on s any number of times:
 *
 * Choose an index i in the string such that there is at least one character to the
 * left of index i that is equal to s[i], and at least one character to the right
 * that is also equal to s[i].
 *
 * Delete the closest character to the left of index i that is equal to s[i].
 *
 * Delete the closest character to the right of index i that is equal to s[i].
 *
 * Return the minimum length of the final string s that you can achieve.
 */

public class Solution {

    public int minimumLength(String s) {
        int[] count = new int[128];
        for (var c : s.toCharArray()) count[c]++;

        int ans = 0;
        for (int c = 'a'; c <= 'z'; c++) {
            while (count[c] >= 3) count[c] -= 2;
            ans += count[c];
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumLength("ucvbutgkohgbcobqeyqwppbxqoynxeuuzouyvmydfhrprdbuzwqebwuiejoxsxdhbmuaiscalnteocghnlisxxawxgcjloevrdcj") == 38;

        assert new Solution().minimumLength("abaacbcbb") == 5;
        assert new Solution().minimumLength("aa") == 2;
    }

}
