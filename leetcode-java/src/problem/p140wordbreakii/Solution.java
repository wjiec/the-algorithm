package problem.p140wordbreakii;

import ability.Ability;
import common.PrettyPrinter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 140. Word Break II
 *
 * https://leetcode.cn/problems/word-break-ii
 *
 * Given a string s and a dictionary of strings wordDict, add spaces
 * in s to construct a sentence where each word is a valid dictionary
 * word.
 *
 * Return all such possible sentences in any order.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 */

public class Solution {

    private final Ability.AlphaTrie root = new Ability.AlphaTrie();

    public List<String> wordBreak(String s, List<String> wordDict) {
        for (var word : wordDict) root.set(word).asLeaf();
        return wordBreak(s.toCharArray(), 0, s.length());
    }

    private final Map<Integer, List<String>> memo = new HashMap<>();

    public List<String> wordBreak(char[] chars, int l, int r) {
        int key = l << 16 | r;
        if (!memo.containsKey(key)) {
            StringBuilder sb = new StringBuilder();
            List<String> ans = new ArrayList<>();
            Ability.AlphaTrie curr = root;
            for (; l < r; l++) {
                sb.append(chars[l]);
                curr = curr.get(chars[l]);
                if (curr == null) break;

                if (curr.isLeaf()) {
                    String prefix = sb.toString();
                    for (var sub : wordBreak(chars, l + 1, r)) {
                        ans.add(prefix + " " + sub);
                    }
                }
            }
            if (l == r && !sb.isEmpty() && curr.isLeaf()) ans.add(sb.toString());

            memo.put(key, ans);
        }
        return memo.get(key);
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().wordBreak("bb", List.of("a","b","bbb","bbbb")));

        PrettyPrinter.println(new Solution().wordBreak("catsanddog", List.of("cat","cats","and","sand","dog")));
        PrettyPrinter.println(new Solution().wordBreak("pineapplepenapple", List.of("apple","pen","applepen","pine","pineapple")));
        PrettyPrinter.println(new Solution().wordBreak("catsandog", List.of("cats","dog","sand","and","cat")));
    }

}
