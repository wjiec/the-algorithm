package problem.p1552magneticforcebetweentwoballs;

import java.util.Arrays;

/**
 * 1552. Magnetic Force Between Two Balls
 *
 * https://leetcode.cn/problems/magnetic-force-between-two-balls/
 *
 * In the universe Earth C-137, Rick discovered a special form of magnetic force between two balls
 * if they are put in his new invented basket. Rick has n empty baskets, the ith basket is at
 * position[i], Morty has m balls and needs to distribute the balls into the baskets
 * such that the minimum magnetic force between any two balls is maximum.
 *
 * Rick stated that magnetic force between two different balls at positions x and y is |x - y|.
 *
 * Given the integer array position and the integer m. Return the required force.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int l = 1, r = position[position.length - 1] - position[0], ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (check(position, mid, m)) {
                ans = mid; l = mid + 1;
            } else r = mid - 1;
        }
        return ans;
    }

    private boolean check(int[] pos, int dist, int m) {
        int prev = pos[0], count = 1;
        for (int i = 1; i < pos.length; i++) {
            if (pos[i] - prev >= dist) {
                prev = pos[i];
                count++;
            }
        }
        return count >= m;
    }

    public static void main(String[] args) {
        assert new Solution().maxDistance(new int[]{1,2,3,4,7}, 3) == 3;
        assert new Solution().maxDistance(new int[]{5,4,3,2,1,1000000000}, 2) == 999999999;
    }

}
