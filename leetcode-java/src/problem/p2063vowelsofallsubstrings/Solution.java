package problem.p2063vowelsofallsubstrings;

/**
 * 2063. Vowels of All Substrings
 *
 * https://leetcode.cn/problems/vowels-of-all-substrings/
 *
 * Given a string word, return the sum of the number of
 * vowels ('a', 'e', 'i', 'o', and 'u') in every substring of word.
 *
 * A substring is a contiguous (non-empty) sequence of characters within a string.
 *
 * Note: Due to the large constraints, the answer may not fit in a signed 32-bit integer.
 * Please be careful during the calculations.
 */

public class Solution {

    public long countVowels(String word) {
        long ans = 0, n = word.length();
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                // 从 i 往前可以选择 0, 1, ..., i 一共 i + 1 个
                // 从 i 往后可以选择 i, i + 1, i + 2, ..., n - 1 一共 n - i 个
                ans += (i + 1) * (n - i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countVowels("aba") == 6;
        assert new Solution().countVowels("abc") == 3;
        assert new Solution().countVowels("ltcd") == 0;
        assert new Solution().countVowels("noosabasboosa") == 237;
        assert new Solution().countVowels("aaaaaaaaaaa") == 286;
    }

}
