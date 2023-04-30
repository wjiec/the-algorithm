package problem.p418sentencescreenfitting;

/**
 * 418. Sentence Screen Fitting
 *
 * https://leetcode.cn/problems/sentence-screen-fitting/
 *
 * Given a rows x cols screen and a sentence represented as a list of strings, return
 * the number of times the given sentence can be fitted on the screen.
 *
 * The order of words in the sentence must remain unchanged, and a word cannot be split into two lines.
 * A single space must separate two consecutive words in a line.
 */

public class Solution {

    public int wordsTyping(String[] sentence, int rows, int cols) {
        int[] lens = new int[sentence.length];
        for (int i = 0; i < lens.length; i++) {
            lens[i] = sentence[i].length();
        }

        int x = 0, n = sentence.length;
        for (int i = 0; i < rows; i++) {
            for (int r = cols + 1; r > 0; x++) {
                int l = lens[x % n] + 1;
                if (r < l) break; else r -= l;
            }
        }
        return x / n;
    }

    private static class Optimization {
        public int wordsTyping(String[] sentence, int rows, int cols) {
            int[] lens = new int[sentence.length];
            for (int i = 0; i < sentence.length; i++) {
                lens[i] = sentence[i].length();
            }

            // next[i] 表示以 sentence[i] 开头能完成几次且
            // 下一行以哪个单词开头
            int[] next = new int[sentence.length];
            for (int i = 0; i < sentence.length; i++) {
                int padLen = 0, cur = i, rnd = 0;
                while (padLen + lens[cur] <= cols) {
                    padLen += lens[cur++] + 1; // space
                    if (cur == sentence.length) {
                        cur = 0; rnd++;
                    }
                }
                next[i] = (rnd << 16) | cur;
            }

            int curr = 0, ans = 0;
            for (int i = 0; i < rows; i++) {
                ans += next[curr] >> 16;
                curr = next[curr] & 0xffff;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Optimization().wordsTyping(new String[]{"f","p","a"}, 8, 7) == 10;

        assert new Solution().wordsTyping(new String[]{"a", "bcd", "e"}, 3, 6) == 2;
        assert new Optimization().wordsTyping(new String[]{"a", "bcd", "e"}, 3, 6) == 2;
    }

}
