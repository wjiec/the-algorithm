package problem.p1065indexpairsofastring;

import common.PrettyPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1065. Index Pairs of a String
 *
 * https://leetcode-cn.com/problems/index-pairs-of-a-string/
 *
 * Given a string text and an array of strings words,
 * return an array of all index pairs [i, j] so that the substring text[i...j] is in words.
 *
 * Return the pairs [i, j] in sorted order (i.e., sort them by their first coordinate,
 * and in case of ties sort them by their second coordinate).
 */

public class Solution {

    public int[][] indexPairs(String text, String[] words) {
        List<int[]> list = new ArrayList<>();
        for (var word : words) {
            for (int n = text.indexOf(word); n != -1; ) {
                list.add(new int[]{n, n + word.length() - 1});
                n = text.indexOf(word, n + word.length() - 1);
            }
        }

        int[][] ans = new int[list.size()][];
        for (int i = 0; i < ans.length; i++) ans[i] = list.get(i);

        Arrays.sort(ans, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b [1];
        });
        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().indexPairs("thestoryofleetcodeandme",
            new String[]{"story","fleet","leetcode"}));

        PrettyPrinter.println(new Solution().indexPairs("ababa",
            new String[]{"aba","ab"}));
    }

}
