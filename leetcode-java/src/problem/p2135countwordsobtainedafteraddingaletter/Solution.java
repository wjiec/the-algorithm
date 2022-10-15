package problem.p2135countwordsobtainedafteraddingaletter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 2135. Count Words Obtained After Adding a Letter
 *
 * https://leetcode.cn/problems/count-words-obtained-after-adding-a-letter/
 *
 * You are given two 0-indexed arrays of strings startWords and targetWords.
 * Each string consists of lowercase English letters only.
 *
 * For each string in targetWords, check if it is possible to choose a string
 * from startWords and perform a conversion operation on it to be equal to that
 * from targetWords.
 *
 * The conversion operation is described in the following two steps:
 *
 * Append any lowercase letter that is not present in the string to its end.
 * For example, if the string is "abc", the letters 'd', 'e', or 'y' can be
 * added to it, but not 'a'. If 'd' is added, the resulting string will be "abcd".
 *
 * Rearrange the letters of the new string in any arbitrary order.
 * For example, "abcd" can be rearranged to "acbd", "bacd", "cbda", and so on.
 * Note that it can also be rearranged to "abcd" itself.
 * Return the number of strings in targetWords that can be obtained by performing
 * the operations on any string of startWords.
 *
 * Note that you will only be verifying if the string in targetWords can be obtained
 * from a string in startWords by performing the operations.
 *
 * The strings in startWords do not actually change during this process.
 */

public class Solution {

    public int wordCount(String[] startWords, String[] targetWords) {
        Set<Integer> possible = new HashSet<>();
        for (var word : startWords) {
            int id = hash(word.toCharArray());
            for (int i = 0; i < 26; i++) {
                if ((id & (1 << i)) == 0) {
                    possible.add(id | (1 << i));
                }
            }
        }

        int ans = 0;
        for (var word : targetWords) {
            if (possible.contains(hash(word.toCharArray()))) ans++;
        }
        return ans;
    }

    private int hash(char[] chars) {
        int id = 0;
        for (var c : chars) {
            id |= 1 << (c - 'a');
        }
        return id;
    }

    public static void main(String[] args) {
        assert new Solution().wordCount(new String[]{"ant","act","tack"}, new String[]{"tack","act","acti"}) == 2;
        assert new Solution().wordCount(new String[]{"ab","a"}, new String[]{"abc","abcd"}) == 1;
    }

}
