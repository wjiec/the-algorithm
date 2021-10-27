package problem.p1592rearrangespacesbetweenwords;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * 1592. Rearrange Spaces Between Words
 *
 * https://leetcode-cn.com/problems/rearrange-spaces-between-words/
 *
 * You are given a string text of words that are placed among some number of spaces.
 *
 * Each word consists of one or more lowercase English letters and are separated by at least one space.
 *
 * It's guaranteed that text contains at least one word.
 *
 * Rearrange the spaces so that there is an equal number of spaces between
 * every pair of adjacent words and that number is maximized.
 *
 * If you cannot redistribute all the spaces equally, place the extra spaces at the end,
 * meaning the returned string should be the same length as text.
 *
 * Return the string after rearranging the spaces.
 */

public class Solution {

    public String reorderSpaces(String text) {
        int space = 0;
        StringBuilder sb = new StringBuilder();
        List<String> words = new ArrayList<>();
        for (var c : text.toCharArray()) {
            if (c == ' ') {
                space++;
                if (sb.length() != 0) {
                    words.add(sb.toString());
                    sb = new StringBuilder();
                }
            } else sb.append(c);
        }
        if (sb.length() != 0) words.add(sb.toString());
        if (space == 0) return text;
        if (words.size() == 1) return words.get(0) + " ".repeat(space);

        int avg = space / (words.size() - 1), rest = space % (words.size() - 1);
        StringJoiner sj = new StringJoiner(" ".repeat(avg));
        for (var word : words) sj.add(word);

        return sj.toString() + " ".repeat(rest);
    }

    public static void main(String[] args) {
        assert new Solution().reorderSpaces("  this   is  a sentence ").equals("this   is   a   sentence");
        assert new Solution().reorderSpaces(" practice   makes   perfect").equals("practice   makes   perfect ");
        assert new Solution().reorderSpaces("hello   world").equals("hello   world");
        assert new Solution().reorderSpaces("  walks  udp package   into  bar a").equals("walks  udp  package  into  bar  a ");
        assert new Solution().reorderSpaces("a").equals("a");
    }

}
