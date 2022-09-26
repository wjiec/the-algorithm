package problem.p1915numberofwonderfulsubstrings;

import common.Tag;

/**
 * 1915. Number of Wonderful Substrings
 *
 * https://leetcode.cn/problems/number-of-wonderful-substrings/
 *
 * A wonderful string is a string where at most one letter appears an odd number of times.
 *
 * For example, "ccjjc" and "abab" are wonderful, but "ab" is not.
 * Given a string word that consists of the first ten lowercase English letters ('a' through 'j'), return
 * the number of wonderful non-empty substrings in word.
 *
 * If the same substring appears multiple times in word, then count each occurrence separately.
 *
 * A substring is a contiguous sequence of characters in a string.
 */

public class Solution {

    @Tag({"子字符串", "至多一个字母出现奇数次"})
    public long wonderfulSubstrings(String word) {
        long[] freq = new long[1 << 10];
        freq[0] = 1;

        int mask = 0; long ans = 0;
        for (var c : word.toCharArray()) {
            mask ^= (1 << (c - 'a'));
            ans += freq[mask];

            for (int i = 0; i < 10; i++) {
                ans += freq[mask ^ (1 << i)];
            }
            freq[mask]++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().wonderfulSubstrings("aba") == 4;
        assert new Solution().wonderfulSubstrings("aabb") == 9;
        assert new Solution().wonderfulSubstrings("he") == 2;
    }

}
