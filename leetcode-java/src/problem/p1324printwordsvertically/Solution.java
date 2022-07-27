package problem.p1324printwordsvertically;

import common.Checker;

import java.util.ArrayList;
import java.util.List;

/**
 * 1324. Print Words Vertically
 *
 * https://leetcode.cn/problems/print-words-vertically/
 *
 * Given a string s. Return all the words vertically in the same order in which they appear in s.
 * Words are returned as a list of strings, complete with spaces when is necessary. (Trailing spaces are not allowed).
 * Each word would be put on only one column and that in one column there will be only one word.
 */

public class Solution {

    public List<String> printVertically(String s) {
        int maxLen = 0;
        String[] words = s.split(" ");
        for (String word : words) {
            maxLen = Math.max(maxLen, word.length());
        }

        List<String> ans = new ArrayList<>();
        for (int i = 0; i < maxLen; i++) {
            int blank = 0;
            StringBuilder sb = new StringBuilder();
            for (var word : words) {
                if (i < word.length()) {
                    if (blank != 0) {
                        sb.append(" ".repeat(blank));
                        blank = 0;
                    }

                    sb.append(word.charAt(i));
                } else blank++;
            }
            ans.add(sb.toString());
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().printVertically("HOW ARE YOU"), List.of(
            "HAY",
            "ORO",
            "WEU"
        ));

        assert Checker.check(new Solution().printVertically("TO BE OR NOT TO BE"), List.of(
            "TBONTB",
            "OEROOE",
            "   T"
        ));

        assert Checker.check(new Solution().printVertically("CONTEST IS COMING"), List.of(
            "CIC",
            "OSO",
            "N M",
            "T I",
            "E N",
            "S G",
            "T"
        ));
    }

}
