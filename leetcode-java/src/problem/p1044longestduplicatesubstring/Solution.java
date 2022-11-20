package problem.p1044longestduplicatesubstring;

import java.util.HashSet;
import java.util.Set;

/**
 * 1044. Longest Duplicate Substring
 *
 * https://leetcode.cn/problems/longest-duplicate-substring/
 *
 * Given a string s, consider all duplicated substrings: (contiguous) substrings of s
 * that occur 2 or more times. The occurrences may overlap.
 *
 * Return any duplicated substring that has the longest possible length.
 * If s does not have a duplicated substring, the answer is "".
 */

public class Solution {

    public String longestDupSubstring(String s) {
        char[] chars = s.toCharArray();

        int l = 0, r = s.length(), len = 0, idx = 0;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int curr = search(chars, mid);
            if (curr != -1) {
                len = mid;
                idx = curr;
                l = mid + 1;
            } else r = mid;
        }

        if (len == 0) return "";
        return s.substring(idx, idx + len);
    }

    private final int PRIME = 31;

    private int search(char[] chars, int len) {
        long hash = 0, power = 1;
        for (int i = 0; i < len; i++) {
            hash = hash * PRIME + (chars[i] - 'a');
            power *= PRIME;
        }

        Set<Long> seen = new HashSet<>();
        seen.add(hash);

        for (int i = len; i < chars.length; i++) {
            hash = hash * PRIME + (chars[i] - 'a') - power * (chars[i - len] - 'a');
            if (seen.contains(hash)) return i - len + 1;
            seen.add(hash);
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().longestDupSubstring("banana").equals("ana");
        assert new Solution().longestDupSubstring("abcd").equals("");
    }

}
