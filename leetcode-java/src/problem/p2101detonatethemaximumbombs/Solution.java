package problem.p2101detonatethemaximumbombs;

import java.util.HashSet;
import java.util.Set;

/**
 * 2101. Detonate the Maximum Bombs
 *
 * https://leetcode.cn/problems/detonate-the-maximum-bombs/
 *
 * You are given a list of bombs. The range of a bomb is defined as the area where its effect can be felt.
 * This area is in the shape of a circle with the center as the location of the bomb.
 *
 * The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [xi, yi, ri].
 * xi and yi denote the X-coordinate and Y-coordinate of the location of the ith bomb, whereas
 * ri denotes the radius of its range.
 *
 * You may choose to detonate a single bomb. When a bomb is detonated, it will detonate all bombs that
 * lie in its range. These bombs will further detonate the bombs that lie in their ranges.
 *
 * Given the list of bombs, return the maximum number of bombs that can be detonated if you are
 * allowed to detonate only one bomb.
 */

public class Solution {

    private Set<Integer>[] effects = null;

    public int maximumDetonation(int[][] bombs) {
        effects = new Set[bombs.length];
        for (int i = 0; i < bombs.length; i++) {
            effects[i] = new HashSet<>();
            for (int j = 0; j < bombs.length; j++) {
                if (distance(bombs[i][0], bombs[i][1], bombs[j][0], bombs[j][1]) <= bombs[i][2]) {
                    effects[i].add(j);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < bombs.length; i++) {
            Set<Integer> groups = new HashSet<>();
            groups.add(i); dfs(groups, i);
            ans = Math.max(ans, groups.size());
        }
        return ans;
    }

    private void dfs(Set<Integer> bombs, int i) {
        for (var b : effects[i]) {
            if (!bombs.contains(b)) {
                bombs.add(b);
                dfs(bombs, b);
            }
        }
    }

    private double distance(int x1, int y1, int x2, int y2) {
        double dx = x1 - x2, dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static void main(String[] args) {
        assert new Solution().maximumDetonation(new int[][]{
            {855,82,158},{17,719,430},{90,756,164},{376,17,340},
            {691,636,152},{565,776,5},{464,154,271},{53,361,162},
            {278,609,82},{202,927,219},{542,865,377},{330,402,270},
            {720,199,10},{986,697,443},{471,296,69},{393,81,404},
            {127,405,177}
        }) == 9;

        assert new Solution().maximumDetonation(new int[][]{{2,1,3},{6,1,4}}) == 2;
        assert new Solution().maximumDetonation(new int[][]{{1,1,5},{10,10,5}}) == 1;
        assert new Solution().maximumDetonation(new int[][]{{1,2,3},{2,3,1},{3,4,2},{4,5,3},{5,6,4}}) == 5;
    }

}
