package problem.p2114maximumnumberofwordsfoundinsentences;

/**
 * 2114. Maximum Number of Words Found in Sentences
 *
 * https://leetcode-cn.com/problems/maximum-number-of-words-found-in-sentences/
 *
 * A sentence is a list of words that are separated by a single space with no leading or trailing spaces.
 *
 * You are given an array of strings sentences, where each sentences[i] represents a single sentence.
 *
 * Return the maximum number of words that appear in a single sentence.
 */

public class Solution {

    public int mostWordsFound(String[] sentences) {
        int ans = 0, curr = 0;
        for (var sentence : sentences) {
            for (var c : sentence.toCharArray()) {
                if (c == ' ') curr++;
            }
            ans = Math.max(ans, curr + 1);
            curr = 0;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().mostWordsFound(new String[]{"alice and bob love leetcode", "i think so too",
            "this is great thanks very much"}) == 6;
        assert new Solution().mostWordsFound(new String[]{"please wait", "continue to fight", "continue to win"}) == 3;
    }

}
