package problem.p374guessnumberhigherorlower;

/**
 * 374. Guess Number Higher or Lower
 *
 * https://leetcode-cn.com/problems/guess-number-higher-or-lower/
 *
 * We are playing the Guess Game. The game is as follows:
 *
 * I pick a number from 1 to n. You have to guess which number I picked.
 *
 * Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.
 *
 * You call a pre-defined API int guess(int num), which returns 3 possible results:
 *
 * -1: The number I picked is lower than your guess (i.e. pick < num).
 * 1: The number I picked is higher than your guess (i.e. pick > num).
 * 0: The number I picked is equal to your guess (i.e. pick == num).
 * Return the number that I picked.
 */

public class Solution extends GuessGame {

    public int guessNumber(int n) {
        int l = 1, r = n;
        while (l < r) {
            int mid = l + (r - l) / 2;
            switch (guess(mid)) {
                case 0:
                    return mid;
                case -1:
                    r = mid - 1;
                    break;
                case 1:
                    l = mid + 1;
                    break;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        assert new Solution().guessNumber(10) == 6;
        assert new Solution().guessNumber(100) == 6;
        assert new Solution().guessNumber(1000) == 6;
        assert new Solution().guessNumber(10000) == 6;
    }

}
