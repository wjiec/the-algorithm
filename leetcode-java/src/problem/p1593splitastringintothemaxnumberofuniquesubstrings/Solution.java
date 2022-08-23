package problem.p1593splitastringintothemaxnumberofuniquesubstrings;

import java.util.HashSet;
import java.util.Set;

/**
 * 1593. Split a String Into the Max Number of Unique Substrings
 *
 * https://leetcode.cn/problems/split-a-string-into-the-max-number-of-unique-substrings/
 *
 * Given a string s, return the maximum number of unique substrings that the given string can be split into.
 *
 * You can split string s into any list of non-empty substrings, where the concatenation of the substrings
 * forms the original string. However, you must split the substrings such that all of them are unique.
 *
 * A substring is a contiguous sequence of characters within a string.
 */

public class Solution {

    private int ans = 0;

    public int maxUniqueSplit(String s) {
        dfs(s, 0, new HashSet<>());
        return ans;
    }

    private void dfs(String s, int l, Set<String> set) {
        if (l == s.length()) {
            ans = Math.max(ans, set.size());
            return;
        }

        for (int r = l; r < s.length(); r++) {
            String sub = s.substring(l, r + 1);
            if (set.add(sub)) {
                dfs(s, r + 1, set);
                set.remove(sub);
            }
        }
    }

    public static void main(String[] args) {
        assert new Solution().maxUniqueSplit("miadceddlamda") == 10;
        assert new Solution().maxUniqueSplit("hmadataa") == 6;
        assert new Solution().maxUniqueSplit("wwwzfvedwfvhsww") == 11;

        assert new Solution().maxUniqueSplit("ababccc") == 5;
        assert new Solution().maxUniqueSplit("aba") == 2;
        assert new Solution().maxUniqueSplit("aa") == 1;
    }

}
