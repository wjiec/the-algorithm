package problem.p819mostcommonword;

import java.util.*;

/**
 * 819. Most Common Word
 *
 * https://leetcode-cn.com/problems/most-common-word/
 *
 * Given a string paragraph and a string array of the banned words banned,
 * return the most frequent word that is not banned.
 * It is guaranteed there is at least one word that is not banned, and that the answer is unique.
 *
 * The words in paragraph are case-insensitive and the answer should be returned in lowercase.
 */

public class Solution {

    public String mostCommonWord(String paragraph, String[] banned) {
        paragraph += ".";
        Set<String> rejections = new HashSet<>(Arrays.asList(banned));

        String ans = "";
        int max = 0, n = paragraph.length();
        Map<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = paragraph.charAt(i);
            if (c >= 'A' && c <= 'Z') c += 32;

            if (c >= 'a' && c <= 'z') {
                sb.append(c);
            } else if (sb.length() != 0) {
                var s = sb.toString();
                if (!rejections.contains(s)) {
                    map.put(s, map.getOrDefault(s, 1) + 1);
                    if (map.get(s) > max) {
                        ans = s;
                        max = map.get(s);
                    }
                }

                sb = new StringBuilder();
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().mostCommonWord("Bob", new String[]{"hit"}).equals("bob");
        assert new Solution().mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.",
            new String[]{"hit"}).equals("ball");
    }

}
