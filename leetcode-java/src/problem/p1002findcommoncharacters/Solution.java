package problem.p1002findcommoncharacters;

import java.util.ArrayList;
import java.util.List;

/**
 * 1002. Find Common Characters
 *
 * https://leetcode-cn.com/problems/find-common-characters/
 *
 * Given an array words of strings made only from lowercase letters,
 * return a list of all characters that show up in all strings within the list (including duplicates).
 *
 * For example, if a character occurs 3 times in all strings but not 4 times,
 * you need to include that character three times in the final answer.
 *
 * You may return the answer in any order.
 */

public class Solution {

    public List<String> commonChars(String[] words) {
        int[][] chars = new int[26][2];
        for (int i = 0; i < 26; i++) {
            chars[i][1] = Integer.MAX_VALUE;
        }

        for (var word : words) {
            for (var c : word.toCharArray()) {
                chars[c - 'a'][0]++;
            }

            for (int i = 0; i < 26; i++) {
                chars[i][1] = Math.min(chars[i][0], chars[i][1]);
                chars[i][0] = 0;
            }
        }

        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < chars[i][1]; j++) {
                ans.add(String.valueOf((char) ('a' + i)));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().commonChars(new String[]{"bella","label","roller"}).equals(List.of("e","l","l"));
        assert new Solution().commonChars(new String[]{"cool","lock","cook"}).equals(List.of("c","o"));
    }

}
