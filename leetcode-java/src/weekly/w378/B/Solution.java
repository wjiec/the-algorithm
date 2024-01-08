package weekly.w378.B;

import java.util.HashMap;
import java.util.Map;

/**
 * 2981. Find Longest Special Substring That Occurs Thrice I
 *
 * https://leetcode.cn/contest/weekly-contest-378/problems/find-longest-special-substring-that-occurs-thrice-i/
 *
 * You are given a string s that consists of lowercase English letters.
 *
 * A string is called special if it is made up of only a single character.
 * For example, the string "abc" is not special, whereas the strings "ddd", "zz", and "f" are special.
 *
 * Return the length of the longest special substring of s which occurs at least
 * thrice, or -1 if no special substring occurs at least thrice.
 *
 * A substring is a contiguous non-empty sequence of characters within a string.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int maximumLength(String s) {
        Map<Character, Map<Integer, Integer>> map = new HashMap<>();
        char curr = ' '; int len = 0;
        for (var c : s.toCharArray()) {
            if (c != curr) {
                map.computeIfAbsent(curr, v -> new HashMap<>()).merge(len, 1, Integer::sum);
                curr = c; len = 1;
            } else len++;
        }
        map.computeIfAbsent(curr, v -> new HashMap<>()).merge(len, 1, Integer::sum);

        int ans = -1;
        for (var cm : map.entrySet()) {
            int l = 0, r = 1000, v = -1;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (check(cm.getValue(), mid)) {
                    v = mid; l = mid + 1;
                } else r = mid;
            }
            if (v != 0) ans = Math.max(ans, v);
        }
        return ans;
    }

    private boolean check(Map<Integer, Integer> map, int len) {
        int curr = 0;
        for (var kv : map.entrySet()) {
            if (kv.getKey() < len) continue;
            curr += (kv.getKey() - len + 1) * kv.getValue();
            if (curr >= 3) break;
        }
        return curr >= 3;
    }

    public static void main(String[] args) {
        assert new Solution().maximumLength("acc") == -1;
        assert new Solution().maximumLength("lkwwdddddnnnnnynnnnl") == 4;
        assert new Solution().maximumLength("eccdnmcnkl") == 1;
        assert new Solution().maximumLength("aaaaabaaaaaa") == 5;
        assert new Solution().maximumLength("aaaa") == 2;
        assert new Solution().maximumLength("abcdef") == -1;
        assert new Solution().maximumLength("abcaba") == 1;
    }

}
