package problem.p1408stringmatchinginanarray;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1408. String Matching in an Array
 *
 * https://leetcode-cn.com/problems/string-matching-in-an-array/
 *
 * Given an array of string words. Return all strings in words which is substring of another word in any order. 
 *
 * String words[i] is substring of words[j], if can be obtained removing some characters
 * to left and/or right side of words[j].
 */

public class Solution {

    public List<String> stringMatching(String[] words) {
        Set<String> ans = new HashSet<>();
        for (var l : words) {
            for (var r : words) {
                if (l.contains(r) && !l.equals(r)) {
                    ans.add(r);
                }
            }
        }
        return new ArrayList<>(ans);
    }

    public static void main(String[] args) {
        assert new Solution().stringMatching(new String[]{"leetcoder","leetcode","od","hamlet","am"}).equals(List.of("leetcode","od","am"));

        assert new Solution().stringMatching(new String[]{"mass","as","hero","superhero"}).equals(List.of("as","hero"));
        assert new Solution().stringMatching(new String[]{"leetcode","et","code"}).equals(List.of("et","code"));
        assert new Solution().stringMatching(new String[]{"blue","green","bu"}).equals(List.of());
    }

}
