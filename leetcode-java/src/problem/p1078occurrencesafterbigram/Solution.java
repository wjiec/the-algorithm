package problem.p1078occurrencesafterbigram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1078. Occurrences After Bigram
 *
 * https://leetcode-cn.com/problems/occurrences-after-bigram/
 *
 * Given two strings first and second, consider occurrences in some text of the form "first second third",
 * where second comes immediately after first, and third comes immediately after second.
 *
 * Return an array of all the words third for each occurrence of "first second third".
 */

public class Solution {

    public String[] findOcurrences(String text, String first, String second) {
        List<String> list = new ArrayList<>();
        String[] words = text.split(" ");
        for (int i = words.length - 1; i >= 2; i--) {
            if (words[i - 1].equals(second) && words[i - 2].equals(first)) {
                list.add(words[i]);
            }
        }

        String[] ans = new String[list.size()];
        for (int i = 0, l = list.size() - 1; i <= l; i++) {
            ans[i] = list.get(l - i);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Arrays.deepEquals(new Solution().findOcurrences(
            "alice is a good girl she is a good student", "a", "good"
        ), new String[]{"girl","student"});
        assert Arrays.deepEquals(new Solution().findOcurrences(
            "we will we will rock you", "we", "will"
        ), new String[]{"we","rock"});
    }

}
