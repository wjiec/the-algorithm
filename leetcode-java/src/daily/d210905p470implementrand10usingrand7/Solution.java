package daily.d210905p470implementrand10usingrand7;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 470. Implement Rand10() Using Rand7()
 *
 * https://leetcode-cn.com/problems/implement-rand10-using-rand7/
 *
 * Given the API rand7() that generates a uniform random integer in the range [1, 7],
 * write a function rand10() that generates a uniform random integer in the range [1, 10].
 *
 * You can only call the API rand7(), and you shouldn't call any other API.
 * Please do not use a language's built-in random API.
 *
 * Each test case will have one internal argument n,
 * the number of times that your implemented function rand10() will be called while testing.
 *
 * Note that this is not an argument passed to rand10().
 *
 * Follow up:
 *
 * What is the expected value for the number of calls to rand7() function?
 * Could you minimize the number of calls to rand7()?
 */

public class Solution {

    public final int rand7() {
        return ThreadLocalRandom.current().nextInt(7) + 1;
    }

    public static class Rand10 extends Solution {
        public int rand10() {
            int row = 0, col = 0, idx = 0;

            do {
                row = rand7();
                col = rand7();
                idx = col + (row - 1) * 7;
            } while (idx > 40);
            return 1 + (idx - 1) % 10;
        }
    }

    public static void main(String[] args) {
        var r10 = new Rand10();

        int[] ans = new int[11];
        for (int i = 0; i < 100000; i++) {
            ans[r10.rand10()] += 1;
        }

        System.out.println(Arrays.toString(ans));
    }

}
