package problem.p1255maximumscorewordsformedbyletters;

/**
 * 1255. Maximum Score Words Formed by Letters
 *
 * https://leetcode.cn/problems/maximum-score-words-formed-by-letters/
 *
 * Given a list of words, list of  single letters (might be repeating) and score of every character.
 *
 * Return the maximum score of any valid set of words formed by using the given letters
 * (words[i] cannot be used two or more times).
 *
 * It is not necessary to use all characters in letters and each letter can only be used once.
 * Score of letters 'a', 'b', 'c', ... ,'z' is given by score[0], score[1], ... , score[25] respectively.
 */

public class Solution {

    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] total = new int[26];
        for (var c : letters) total[c - 'a']++;

        int n = words.length;
        int[] wordScores = new int[n];
        int[][] requires = new int[n][26];
        for (int i = 0; i < n; i++) {
            for (var c : words[i].toCharArray()) {
                if (++requires[i][c - 'a'] <= total[c - 'a']) {
                    wordScores[i] += score[c - 'a'];
                } else { wordScores[i] = -1; break; }
            }
        }

        int dn = 1 << words.length, ans = 0;
        for (int i = 1; i < dn; i++) {
            int[] remain = new int[total.length];
            System.arraycopy(total, 0, remain, 0, total.length);

            int curr = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    if (wordScores[j] < 0) break;
                    if (wordScores[j] == 0) continue;

                    boolean ok = true;
                    for (int k = 0; k < remain.length; k++) {
                        if ((remain[k] -= requires[j][k]) < 0) {
                            ok = false; break;
                        }
                    }

                    if (ok) curr += wordScores[j];
                    else break;
                }
            }
            ans = Math.max(ans, curr);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxScoreWords(new String[]{
            "dog","cat","dad","good"
        }, new char[]{
            'a','a','c','d','d','d','g','o','o'
        }, new int[]{
            1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0
        }) == 23;

        assert new Solution().maxScoreWords(new String[]{
            "xxxz","ax","bx","cx"
        }, new char[]{
            'z','a','b','c','x','x','x'
        }, new int[]{
            4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10
        }) == 27;

        assert new Solution().maxScoreWords(new String[]{
            "leetcode"
        }, new char[]{
            'l','e','t','c','o','d'
        }, new int[]{
            0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0
        }) == 0;
    }

}
