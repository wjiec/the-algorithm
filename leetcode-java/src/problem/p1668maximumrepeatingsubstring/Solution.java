package problem.p1668maximumrepeatingsubstring;

/**
 * 1668. Maximum Repeating Substring
 *
 * https://leetcode-cn.com/problems/maximum-repeating-substring/
 *
 * For a string sequence, a string word is k-repeating if word concatenated k times is a substring of sequence.
 *
 * The word's maximum k-repeating value is the highest value k where word is k-repeating in sequence.
 *
 * If word is not a substring of sequence, word's maximum k-repeating value is 0.
 *
 * Given strings sequence and word, return the maximum k-repeating value of word in sequence.
 */

public class Solution {

    public int maxRepeating(String sequence, String word) {
        int ans = 0, l = word.length();
        for (int i = 0; i + l - 1 < sequence.length(); i++) {
            int cnt = 0;
            for (int k = i; k < sequence.length(); k++) {
                if (sequence.charAt(k) == word.charAt((k - i) % l)) {
                    cnt++;
                } else break;
            }
            ans = Math.max(ans, cnt / l);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxRepeating("aaabaaaabaaabaaaabaaaabaaaabaaaaba", "aaaba") == 5;
        //                                          0123456789abcdef

        assert new Solution().maxRepeating("ababc", "ab") == 2;
        assert new Solution().maxRepeating("ababc", "ba") == 1;
        assert new Solution().maxRepeating("ababc", "ac") == 0;
    }

}
