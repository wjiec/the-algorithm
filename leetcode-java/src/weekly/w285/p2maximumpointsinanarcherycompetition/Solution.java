package weekly.w285.p2maximumpointsinanarcherycompetition;

import common.PrettyPrinter;

/**
 * 6029. Maximum Points in an Archery Competition
 *
 * https://leetcode-cn.com/contest/weekly-contest-285/problems/maximum-points-in-an-archery-competition/
 * Alice and Bob are opponents in an archery competition. The competition has set the following rules:
 * Alice first shoots numArrows arrows and then Bob shoots numArrows arrows.
 *
 * The points are then calculated as follows:
 *
 * The target has integer scoring sections ranging from 0 to 11 inclusive.
 *
 * For each section of the target with score k (in between 0 to 11), say Alice and Bob have shot ak and bk arrows
 * on that section respectively. If ak >= bk, then Alice takes k points. If ak < bk, then Bob takes k points.
 *
 * However, if ak == bk == 0, then nobody takes k points.
 * For example, if Alice and Bob both shot 2 arrows on the section with score 11, then Alice takes 11 points.
 * On the other hand, if Alice shot 0 arrows on the section with score 11 and Bob shot 2 arrows on that same section,
 * then Bob takes 11 points.
 *
 * You are given the integer numArrows and an integer array aliceArrows of size 12, which represents
 * the number of arrows Alice shot on each scoring section from 0 to 11.
 * Now, Bob wants to maximize the total number of points he can obtain.
 *
 * Return the array bobArrows which represents the number of arrows Bob shot on each scoring section
 * from 0 to 11. The sum of the values in bobArrows should equal numArrows.
 *
 * If there are multiple ways for Bob to earn the maximum total points, return any one of them.
 */

public class Solution {

    private int max = 0;
    private int[] ans = new int[12];

    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        dfs(aliceArrows, new int[12], numArrows, 0, 0);
        return ans;
    }

    private void dfs(int[] alice, int[] bob, int remain, int sum, int i) {
        if (i == alice.length) {
            if (sum > max) {
                max = sum;
                System.arraycopy(bob, 0, ans, 0, ans.length);
                ans[0] += remain;
            }
            return;
        }

        if (remain > alice[i]) {
            bob[i] = alice[i] + 1;
            dfs(alice, bob, remain - alice[i] - 1, sum + i, i + 1);
            bob[i] = 0;
        }
        dfs(alice, bob, remain, sum, i + 1);
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().maximumBobPoints(9, new int[]{1,1,0,1,0,0,2,1,0,1,2,0}));
        PrettyPrinter.println(new Solution().maximumBobPoints(3, new int[]{0,0,1,0,0,0,0,0,0,0,0,2}));
        PrettyPrinter.println(new Solution().maximumBobPoints(89, new int[]{3,2,28,1,7,1,16,7,3,13,3,5}));
    }

}
