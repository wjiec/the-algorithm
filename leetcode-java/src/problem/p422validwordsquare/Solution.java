package problem.p422validwordsquare;

import java.util.List;

/**
 * 422. Valid Word Square
 *
 * https://leetcode-cn.com/problems/valid-word-square/
 *
 * Given an array of strings words, return true if it forms a valid word square.
 *
 * A sequence of strings forms a valid word square if the kth row and column read the same string,
 * where 0 <= k < max(numRows, numColumns).
 */

public class Solution {

    public boolean validWordSquare(List<String> words) {
        for (int i = 0, n = words.size(); i < n; i++) {
            String word = words.get(i);
            for (int j = 0, l = word.length(); j < l; j++) {
                if (j >= n) return false;
                if (i >= words.get(j).length()) return false;
                if (word.charAt(j) != words.get(j).charAt(i)) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert !new Solution().validWordSquare(List.of("abc", "b"));

        assert new Solution().validWordSquare(List.of("abcd", "bnrt", "crmy", "dtye"));
        assert new Solution().validWordSquare(List.of("abcd", "bnrt", "crm", "dt"));
        assert !new Solution().validWordSquare(List.of("ball", "area", "read", "lady"));
    }

}
