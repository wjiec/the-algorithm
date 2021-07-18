package weekly.w250.p0p5161maximumnumberofwordsyoucantype;

/**
 * 5161. Maximum Number of Words You Can Type
 *
 * https://leetcode-cn.com/contest/weekly-contest-250/problems/maximum-number-of-words-you-can-type/
 *
 * There is a malfunctioning keyboard where some letter keys do not work.
 *
 * All other keys on the keyboard work properly.
 *
 * Given a string text of words separated by a single space (no leading or trailing spaces)
 * and a string brokenLetters of all distinct letter keys that are broken,
 * return the number of words in text you can fully type using this keyboard.
 */

public class Solution {

    public int canBeTypedWords(String text, String brokenLetters) {
        boolean[] broken = new boolean[255];
        for (var c : brokenLetters.toCharArray()) broken[c] = true;

        int ans = 0;
        for (var word : text.split(" ")) {
            boolean ok = true;
            for (var c : word.toCharArray()) {
                if (broken[c]) {
                    ok = false;
                    break;
                }
            }

            if (ok) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().canBeTypedWords("hello world", "ad") == 1;
        assert new Solution().canBeTypedWords("leet code", "lt") == 1;
        assert new Solution().canBeTypedWords("leet code", "e") == 0;
    }

}
