package problem.p1103distributecandiestopeople;

import javax.print.attribute.standard.NumberUp;
import java.util.Arrays;
import java.util.Map;

/**
 * 1103. Distribute Candies to People
 *
 * https://leetcode-cn.com/problems/distribute-candies-to-people/
 *
 * We distribute some number of candies, to a row of n = num_people people in the following way:
 *
 * We then give 1 candy to the first person, 2 candies to the second person,
 * and so on until we give n candies to the last person.
 *
 * Then, we go back to the start of the row, giving n + 1 candies to the first person,
 * n + 2 candies to the second person, and so on until we give 2 * n candies to the last person.
 *
 * This process repeats (with us giving one more candy each time, and moving to the start of
 * the row after we reach the end) until we run out of candies.
 *
 * The last person will receive all of our remaining candies (not necessarily one more than the previous gift).
 *
 * Return an array (of length num_people and sum candies) that represents the final distribution of candies.
 */

public class Solution {

    public int[] distributeCandies(int candies, int num_people) {
        int[] ans = new int[num_people];
        for (int i = 0; candies > 0; i++) {
            ans[i % num_people] += Math.min(i + 1, candies);
            candies -= Math.min(i + 1, candies);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().distributeCandies(7, 4)));
        System.out.println(Arrays.toString(new Solution().distributeCandies(10, 3)));
    }

}
