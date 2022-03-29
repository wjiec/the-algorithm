package daily.d220329p2024maximizetheconfusionofanexam;

/**
 * 2024. Maximize the Confusion of an Exam
 *
 * https://leetcode-cn.com/problems/maximize-the-confusion-of-an-exam/
 *
 * A teacher is writing a test with n true/false questions, with 'T' denoting true and 'F' denoting false.
 * He wants to confuse the students by maximizing the number of consecutive questions with
 * the same answer (multiple trues or multiple falses in a row).
 *
 * You are given a string answerKey, where answerKey[i] is the original answer to the ith question.
 * In addition, you are given an integer k, the maximum number of times you may perform the following operation:
 *
 * Change the answer key for any question to 'T' or 'F' (i.e., set answerKey[i] to 'T' or 'F').
 * Return the maximum number of consecutive 'T's or 'F's in the answer key
 * after performing the operation at most k times.
 */

public class Solution {

    public int maxConsecutiveAnswers(String answerKey, int k) {
        char[] chars = answerKey.toCharArray();
        return Math.max(window(chars, 'T', k), window(chars, 'F', k));
    }

    private int window(char[] chars, char c, int k) {
        int ans = 0, n = chars.length;
        for (int l = 0, r = 0, x = 0; r < n; r++) {
            if (chars[r] != c) x++;
            for (; x > k; l++) if (chars[l] != c) x--;
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxConsecutiveAnswers("TTFF", 2) == 4;
        assert new Solution().maxConsecutiveAnswers("TFFT", 1) == 3;
        assert new Solution().maxConsecutiveAnswers("TTFTTFTT", 1) == 5;
    }

}
