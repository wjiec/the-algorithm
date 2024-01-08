package weekly.w378.C;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 2982. Find Longest Special Substring That Occurs Thrice II
 *
 * https://leetcode.cn/contest/weekly-contest-378/problems/find-longest-special-substring-that-occurs-thrice-ii/
 *
 * You are given a string s that consists of lowercase English letters.
 *
 * A string is called special if it is made up of only a single character.
 * For example, the string "abc" is not special, whereas the strings "ddd", "zz", and "f" are special.
 *
 * Return the length of the longest special substring of s which occurs at
 * least thrice, or -1 if no special substring occurs at least thrice.
 *
 * A substring is a contiguous non-empty sequence of characters within a string.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int maximumLength(String s) {
        Map<Character, TreeMap<Integer, Integer>> map = new HashMap<>();
        char curr = ' '; int len = 0;
        for (var c : s.toCharArray()) {
            if (c != curr) {
                map.computeIfAbsent(curr, v -> new TreeMap<>()).merge(len, 1, Integer::sum);
                curr = c; len = 1;
            } else len++;
        }
        map.computeIfAbsent(curr, v -> new TreeMap<>()).merge(len, 1, Integer::sum);

        int ans = -1;
        for (var cm : map.entrySet()) {
            if (cm.getValue().lastEntry().getKey() <= ans) continue;

            int l = 0, r = 500000, v = -1;
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

    private boolean check(TreeMap<Integer, Integer> map, int len) {
        int curr = 0;
        for (var kv : map.descendingMap().entrySet()) {
            if (kv.getKey() < len) break;
            curr += (kv.getKey() - len + 1) * kv.getValue();
            if (curr >= 3) break;
        }
        return curr >= 3;
    }

    public static void main(String[] args) {
    }

}
