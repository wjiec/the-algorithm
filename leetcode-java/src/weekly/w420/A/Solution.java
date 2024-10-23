package weekly.w420.A;

import common.PrettyPrinter;

import java.util.ArrayList;
import java.util.List;

/**
 * 3324. Find the Sequence of Strings Appeared on the Screen
 *
 * https://leetcode.cn/contest/weekly-contest-420/problems/find-the-sequence-of-strings-appeared-on-the-screen/
 *
 * You are given a string target.
 *
 * Alice is going to type target on her computer using a special keyboard that has only two keys:
 *
 * Key 1 appends the character "a" to the string on the screen.
 * Key 2 changes the last character of the string on the screen to its next character in the English alphabet.
 *
 * For example, "c" changes to "d" and "z" changes to "a".
 *
 * Note that initially there is an empty string "" on the screen, so she can only press key 1.
 *
 * Return a list of all strings that appear on the screen as Alice types target,
 * in the order they appear, using the minimum key presses.
 */

public class Solution {

    public List<String> stringSequence(String target) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);

            if (ans.isEmpty()) ans.add("a");
            else ans.add(ans.get(ans.size() - 1) + "a");

            while (ans.get(ans.size() - 1).charAt(i) != c) {
                char[] chars = ans.get(ans.size() - 1).toCharArray();
                chars[i] = (char) (chars[i] + 1);
                ans.add(new String(chars));
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().stringSequence("abc"));
    }

}
