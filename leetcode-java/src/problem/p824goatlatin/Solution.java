package problem.p824goatlatin;

/**
 * 824. Goat Latin
 *
 * https://leetcode-cn.com/problems/goat-latin/
 *
 * A sentence sentence is given, composed of words separated by spaces.
 * Each word consists of lowercase and uppercase letters only.
 *
 * We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)
 *
 * The rules of Goat Latin are as follows:
 *
 * If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
 * For example, the word 'apple' becomes 'applema'.
 *
 * If a word begins with a consonant (i.e. not a vowel),
 * remove the first letter and append it to the end, then add "ma".
 *
 * For example, the word "goat" becomes "oatgma".
 *
 * Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
 * For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.
 * Return the final sentence representing the conversion from sentence to Goat Latin.
 */

public class Solution {

    public String toGoatLatin(String sentence) {
        StringBuilder ans = new StringBuilder(), tail = new StringBuilder("maa");
        for (var word : sentence.split(" ")) {
            if (isVowel(word.charAt(0))) {
                ans.append(word);
            } else {
                ans.append(word, 1, word.length()).append(word.charAt(0));
            }

            ans.append(tail).append(" ");
            tail.append('a');
        }
        return ans.deleteCharAt(ans.length() - 1).toString();
    }

    private boolean isVowel(char c) {
        return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' ||
            c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public static void main(String[] args) {
        assert new Solution().toGoatLatin("I speak Goat Latin").equals("Imaa peaksmaaa oatGmaaaa atinLmaaaaa");
        assert new Solution().toGoatLatin("The quick brown fox jumped over the lazy dog")
            .equals("heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa");
    }

}
