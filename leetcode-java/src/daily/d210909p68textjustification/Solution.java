package daily.d210909p68textjustification;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * 68. Text Justification
 *
 * https://leetcode-cn.com/problems/text-justification/
 *
 * Given an array of strings words and a width maxWidth, format the text such that
 * each line has exactly maxWidth characters and is fully (left and right) justified.
 *
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
 * Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 *
 * Extra spaces between words should be distributed as evenly as possible.
 * If the number of spaces on a line does not divide evenly between words,
 * the empty slots on the left will be assigned more spaces than the slots on the right.
 *
 * For the last line of text, it should be left-justified and no extra space is inserted between words.
 *
 * Note:
 *
 * A word is defined as a character sequence consisting of non-space characters only.
 * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * The input array words contains at least one word.
 */

public class Solution {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        for (int l = words.length, right = 0; true; ) {
            int left = right, len = 0;
            while (right < l && len + words[right].length() + right - left <= maxWidth) {
                len += words[right++].length();
            }

            if (right == l) {
                StringBuilder sb = new StringBuilder(join(words, left, l, " "));
                sb.append(" ".repeat(maxWidth - sb.length()));
                ans.add(sb.toString());
                return ans;
            }

            if (right - left == 1) {
                ans.add(words[left] + " ".repeat(maxWidth - len));
                continue;
            }

            int blankAvg = (maxWidth - len) / (right - left - 1);
            int blankExt = (maxWidth - len) % (right - left - 1);

            String sb = join(words, left, left + blankExt + 1, " ".repeat(blankAvg + 1)) +
                " ".repeat(blankAvg) +
                join(words, left + blankExt + 1, right, " ".repeat(blankAvg));
            ans.add(sb);
        }
    }

    private String join(String[] words, int left, int right, String separate) {
        StringJoiner sj = new StringJoiner(separate);
        for (; left < right; left++) {
            sj.add(words[left]);
        }
        return sj.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().fullJustify(new String[]{
            "This", "is", "an", "example", "of", "text", "justification."
        }, 16));

        System.out.println(new Solution().fullJustify(new String[]{
            "What","must","be","acknowledgment","shall","be"
        }, 16));

        System.out.println(new Solution().fullJustify(new String[]{
            "Science","is","what","we","understand","well","enough","to","explain",
            "to","a","computer.","Art","is","everything","else","we","do"
        }, 20));
    }

}
