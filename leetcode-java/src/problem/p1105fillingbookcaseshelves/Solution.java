package problem.p1105fillingbookcaseshelves;

import java.util.Arrays;

/**
 * 1105. Filling Bookcase Shelves
 *
 * https://leetcode.cn/problems/filling-bookcase-shelves/
 *
 * You are given an array books where books[i] = [thicknessi, heighti] indicates the thickness
 * and height of the ith book. You are also given an integer shelfWidth.
 *
 * We want to place these books in order onto bookcase shelves that have a total width shelfWidth.
 *
 * We choose some of the books to place on this shelf such that the sum of their thickness is less than
 * or equal to shelfWidth, then build another level of the shelf of the bookcase so that the total height
 * of the bookcase has increased by the maximum height of the books we just put down.
 * We repeat this process until there are no more books to place.
 *
 * Note that at each step of the above process, the order of the books we place is the
 * same order as the given sequence of books.
 *
 * For example, if we have an ordered list of 5 books, we might place the first and second book
 * onto the first shelf, the third book on the second shelf, and the fourth and fifth book on the last shelf.
 * Return the minimum possible height that the total bookshelf can be after placing shelves in this manner.
 */

public class Solution {

    public int minHeightShelves(int[][] books, int shelfWidth) {
        int[] dp = new int[books.length + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        for (int i = 0; i < books.length; i++) {
            int width = 0, height = 0;
            for (int j = i; j >= 0; j--) {
                width += books[j][0];
                if (width > shelfWidth) break;
                height = Math.max(height, books[j][1]);
                dp[i + 1] = Math.min(dp[i + 1], dp[j] + height);
            }
        }
        return dp[books.length];
    }

    public static void main(String[] args) {
        assert new Solution().minHeightShelves(new int[][]{
            {1,1},{2,3},{2,3},{1,1},{1,1},{1,1},{1,2}
        }, 4) == 6;

        assert new Solution().minHeightShelves(new int[][]{
            {1,3},{2,4},{3,2}
        }, 6) == 4;
    }

}
