package daily.d220409p780reachingpoints;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 780. Reaching Points
 *
 * https://leetcode-cn.com/problems/reaching-points/
 *
 * Given four integers sx, sy, tx, and ty, return true if it is possible to convert the point (sx, sy) to
 * the point (tx, ty) through some operations, or false otherwise.
 *
 * The allowed operation on some point (x, y) is to convert it to either (x, x + y) or (x + y, y).
 */

public class Solution {

    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx > sx && ty > sy && tx != ty) {
            if (tx > ty) tx %= ty;
            else ty %= tx;
        }

        if (tx == sx && ty == sy) return true;
        else if (tx == sx) return ty > sy && (ty - sy) % tx == 0;
        else if (ty == sy) return tx > sx && (tx - sx) % ty == 0;

        return false;
    }

    public static void main(String[] args) {
        assert !new Solution().reachingPoints(35, 13, 455955547, 420098884);

        assert new Solution().reachingPoints(1, 1, 3, 5);
        assert !new Solution().reachingPoints(1, 1, 2, 2);
        assert new Solution().reachingPoints(1, 1, 1, 1);
    }

}
