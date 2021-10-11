package problem.p1496pathcrossing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 1496. Path Crossing
 *
 * https://leetcode-cn.com/problems/path-crossing/
 *
 * Given a string path, where path[i] = 'N', 'S', 'E' or 'W',
 * each representing moving one unit north, south, east, or west, respectively.
 *
 * You start at the origin (0, 0) on a 2D plane and walk on the path specified by path.
 *
 * Return true if the path crosses itself at any point, that is,
 * if at any time you are on a location you have previously visited. Return false otherwise.
 */

public class Solution {

    public boolean isPathCrossing(String path) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        map.put(0, new HashSet<>() {{ add(0); }});

        int x = 0, y = 0;
        for (var c : path.toCharArray()) {
            switch (c) {
                case 'N': y++; break;
                case 'S': y--; break;
                case 'E': x++; break;
                case 'W': x--; break;
            }

            if (!map.containsKey(x)) {
                map.put(x, new HashSet<>());
            }

            if (map.get(x).contains(y)) return true;
            map.get(x).add(y);
        }
        return false;
    }

    public static void main(String[] args) {
        assert !new Solution().isPathCrossing("NES");
        assert new Solution().isPathCrossing("NESWW");
        assert new Solution().isPathCrossing("NESWW");
    }

}
