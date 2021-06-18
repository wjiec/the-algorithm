package problem.p953verifyinganaliendictionary;

/**
 * 953. Verifying an Alien Dictionary
 *
 * https://leetcode-cn.com/problems/verifying-an-alien-dictionary/
 *
 * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order.
 * The order of the alphabet is some permutation of lowercase letters.
 *
 * Given a sequence of words written in the alien language, and the order of the alphabet,
 * return true if and only if the given words are sorted lexicographicaly in this alien language.
 */

public class Solution {

    private final int[] chars = new int[255];

    public boolean isAlienSorted(String[] words, String order) {
        for (int i = 0, l = order.length(); i < l; i++) {
            chars[order.charAt(i)] = i;
        }

        for (int i = 1, l = words.length; i < l; i++) {
            if (!gt(words[i], words[i - 1])) return false;
        }
        return true;
    }

    public boolean gt(String self, String other) {
        int m = self.length(), n = other.length();
        for (int i = 0; i < m && i < n; i++) {
            if (chars[self.charAt(i)] != chars[other.charAt(i)]) {
                return chars[self.charAt(i)] > chars[other.charAt(i)];
            }
        }
        return m >= n;
    }

    public static void main(String[] args) {
        assert new Solution().isAlienSorted(new String[]{"hello","leetcode"}, "hlabcdefgijkmnopqrstuvwxyz");
        assert !new Solution().isAlienSorted(new String[]{"word","world","row"}, "worldabcefghijkmnpqstuvxyz");
        assert !new Solution().isAlienSorted(new String[]{"apple","app"}, "abcdefghijklmnopqrstuvwxyz");
    }

}
