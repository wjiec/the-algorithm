package weekly.bw123.B;

/**
 * 3025. Find the Number of Ways to Place People I
 *
 * https://leetcode.cn/contest/biweekly-contest-123/problems/find-the-number-of-ways-to-place-people-i/
 *
 * You are given a 2D array points of size n x 2 representing integer coordinates of some
 * points on a 2D-plane, where points[i] = [xi, yi].
 *
 * We define the right direction as positive x-axis (increasing x-coordinate) and the left
 * direction as negative x-axis (decreasing x-coordinate). Similarly, we define the up
 * direction as positive y-axis (increasing y-coordinate) and the down direction as
 * negative y-axis (decreasing y-coordinate)
 *
 * You have to place n people, including Chisato and Takina, at these points such that
 * there is exactly one person at every point. Chisato wants to be alone with Takina, so
 * Chisato will build a rectangular fence with Chisato's position as the upper left
 * corner and Takina's position as the lower right corner of the fence (Note that the
 * fence might not enclose any area, i.e. it can be a line). If any person other than
 * Chisato and Takina is either inside the fence or on the fence, Chisato will be sad.
 *
 * Return the number of pairs of points where you can place Chisato and Takina, such that
 * Chisato does not become sad on building the fence.
 *
 * Note that Chisato can only build a fence with Chisato's position as the upper left
 * corner, and Takina's position as the lower right corner. For example, Chisato
 * cannot build either of the fences in the picture below with four
 * corners (1, 1), (1, 3), (3, 1), and (3, 3), because:
 *
 * With Chisato at (3, 3) and Takina at (1, 1), Chisato's position is not the upper
 * left corner and Takina's position is not the lower right corner of the fence.
 *
 * With Chisato at (1, 3) and Takina at (1, 1), Takina's position is not the
 * lower right corner of the fence.
 */

public class Solution {

    public int numberOfPairs(int[][] points) {
        int ans = 0, n = points.length;
        for (int i = 0; i < n; i++) {
            int ax = points[i][0], ay = points[i][1];
            for (int j = 0; j < n; j++) {
                int bx = points[j][0], by = points[j][1];
                if (i != j && ax <= bx && ay >= by) {

                    boolean happy = true;
                    for (int k = 0; k < n; k++) {
                        if (k == i || k == j) continue;
                        int cx = points[k][0], cy = points[k][1];
                        if (cx >= ax && cx <= bx && cy >= by && cy <= ay) {
                            happy = false;
                            break;
                        }
                    }
                    if (happy) ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
