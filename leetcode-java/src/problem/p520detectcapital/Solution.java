package problem.p520detectcapital;

/**
 * 520. Detect Capital
 *
 * https://leetcode-cn.com/problems/detect-capital/
 *
 * We define the usage of capitals in a word to be right when one of the following cases holds:
 *
 * All letters in this word are capitals, like "USA".
 * All letters in this word are not capitals, like "leetcode".
 * Only the first letter in this word is capital, like "Google".
 *
 *  Given a string word, return true if the usage of capitals in it is right.
 */

public class Solution {

    public boolean detectCapitalUse(String word) {
        int sz = word.length();
        if (sz == 1) return true;

        boolean upper = word.charAt(1) >= 'A' && word.charAt(1) <= 'Z';
        for (int i = 2; i < sz; i++) {
            if (upper && word.charAt(i) >= 'a') {
                return false;
            }
            if (!upper && word.charAt(i) <= 'Z') {
                return false;
            }
        }

        return !upper || word.charAt(0) <= 'Z';
    }

    public static void main(String[] args) {
        assert new Solution().detectCapitalUse("ue");
        assert new Solution().detectCapitalUse("Ca");
        assert !new Solution().detectCapitalUse("isA");
        assert new Solution().detectCapitalUse("AZ");
        assert new Solution().detectCapitalUse("GG");
        assert new Solution().detectCapitalUse("ABC");
        assert new Solution().detectCapitalUse("leetcode");
        assert new Solution().detectCapitalUse("Google");
        assert !new Solution().detectCapitalUse("nGINX");
        assert !new Solution().detectCapitalUse("FlaG");
    }

}
