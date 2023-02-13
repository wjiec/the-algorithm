package problem.p135candy;

/**
 * 135. Candy
 *
 * https://leetcode.cn/problems/candy/
 *
 * There are n children standing in a line. Each child is assigned a rating value
 * given in the integer array ratings.
 *
 * You are giving candies to these children subjected to the following requirements:
 *
 * Each child must have at least one candy.
 *
 * Children with a higher rating get more candies than their neighbors.
 *
 * Return the minimum number of candies you need to have to distribute
 * the candies to the children.
 */

public class Solution {

    public int candy(int[] ratings) {
        int[] left = new int[ratings.length];
        for (int i = 0; i < ratings.length; i++) {
            if (i != 0 && ratings[i - 1] < ratings[i]) {
                left[i] = left[i - 1] + 1;
            } else left[i] = 1;
        }

        int ans = 0;
        int[] right = new int[ratings.length];
        for (int i = ratings.length - 1; i >= 0; i--) {
            if (i + 1 < ratings.length && ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else right[i] = 1;

            ans += Math.max(left[i], right[i]);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().candy(new int[]{1,0,2}) == 5;
        assert new Solution().candy(new int[]{1,2,2}) == 4;
    }

}
