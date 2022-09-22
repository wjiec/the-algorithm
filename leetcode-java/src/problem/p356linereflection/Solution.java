package problem.p356linereflection;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 356. Line Reflection
 *
 * https://leetcode.cn/problems/line-reflection/
 *
 * Given n points on a 2D plane, find if there is such a line parallel to the y-axis that
 * reflects the given points symmetrically.
 *
 * In other words, answer whether or not if there exists a line that after reflecting all
 * points over the given line, the original points' set is the same as the reflected ones.
 *
 * Note that there can be repeated points.
 */

@SuppressWarnings("ConstantConditions")
public class Solution {

    public boolean isReflected(int[][] points) {
        // y -> x
        Map<Integer, TreeSet<Integer>> map = new HashMap<>();
        for (var point : points) {
            map.computeIfAbsent(point[1], v -> new TreeSet<>())
                .add(point[0]);
        }

        double r = Double.MIN_VALUE;
        for (var v : map.values()) {
            if (r == Double.MIN_VALUE) {
                r = reflected(v);
            } else if (r != reflected(v)) {
                return false;
            }
        }
        return r != Double.MIN_VALUE;
    }

    private double reflected(TreeSet<Integer> set) {
        if (set.size() == 1) return set.first();

        double r = mid(set.pollFirst(), set.pollLast());
        while (!set.isEmpty()) {
            if (set.size() == 1) {
                if (set.pollFirst() != r) {
                    return Double.MIN_VALUE;
                }
            } else {
                double dr = mid(set.pollFirst(), set.pollLast());
                if (dr != r) return Double.MIN_VALUE;
            }
        }
        return r;
    }

    private double mid(int l, int r) { return l + ((double) r - l) / 2; }

    public static void main(String[] args) {
        assert !new Solution().isReflected(new int[][]{{0,0},{1,0},{3,0}});

        assert new Solution().isReflected(new int[][]{{1,1},{-1,1}});
        assert !new Solution().isReflected(new int[][]{{1,1},{-1,-1}});
    }

}
