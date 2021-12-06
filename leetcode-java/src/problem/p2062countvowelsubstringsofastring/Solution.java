package problem.p2062countvowelsubstringsofastring;

/**
 * 2062. Count Vowel Substrings of a String
 *
 * https://leetcode-cn.com/problems/count-vowel-substrings-of-a-string/
 *
 * A substring is a contiguous (non-empty) sequence of characters within a string.
 *
 * A vowel substring is a substring that only consists of vowels ('a', 'e', 'i', 'o', and 'u')
 * and has all five vowels present in it.
 *
 * Given a string word, return the number of vowel substrings in word.
 */

public class Solution {

    public int countVowelSubstrings(String word) {
        int vowel = 1065233, ans = 0;
        char[] chars = word.toCharArray();
        for (int i = 0, l = chars.length; i < l; i++) {
            int current = 0;
            for (int j = i; j < l; j++) {
                current |= 1 << (chars[j] - 'a');
                if (current == vowel) ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countVowelSubstrings("aeiouu") == 2;
        assert new Solution().countVowelSubstrings("unicornarihan") == 0;
        assert new Solution().countVowelSubstrings("cuaieuouac") == 7;
        assert new Solution().countVowelSubstrings("bbaeixoubb") == 0;
    }

}
