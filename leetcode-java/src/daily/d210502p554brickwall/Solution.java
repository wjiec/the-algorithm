package daily.d210502p554brickwall;

import java.util.*;

/**
 * 554. Brick Wall
 *
 * https://leetcode-cn.com/problems/brick-wall/
 *
 * There is a rectangular brick wall in front of you with n rows of bricks.
 * The ith row has some number of bricks each of the same height (i.e., one unit) but they can be of different widths.
 * The total width of each row is the same.
 *
 * Draw a vertical line from the top to the bottom and cross the least bricks.
 * If your line goes through the edge of a brick, then the brick is not considered as crossed.
 * You cannot draw a line just along one of the two vertical edges of the wall,
 * in which case the line will obviously cross no bricks.
 *
 * Given the 2D array wall that contains the information about the wall,
 * return the minimum number of crossed bricks after drawing such a vertical line.
 */

public class Solution {

    // mark 1
    public int leastBricks(List<List<Integer>> wall) {
        int max = 0;
        Map<Integer, Integer> gaps = new HashMap<>();
        for (var line : wall) {
            for (int v = line.get(0), i = 1; i < line.size(); i++) {
                max = Math.max(max, gaps.merge(v, 1, Integer::sum));
                v += line.get(i);
            }
        }
        return wall.size() - max;
    }

    // mark 0
    public int leastBricks0(List<List<Integer>> wall) {
        Map<Integer, Integer> gaps = new HashMap<>();
        for (var line : wall) {
            for (int v = line.get(0), i = 1; i < line.size(); i++) {
                gaps.put(v, gaps.getOrDefault(v, 0) + 1);
                v += line.get(i);
            }
        }

        int min = wall.size();
        for (var kv : gaps.entrySet()) {
            min = Math.min(min, wall.size() - kv.getValue());
        }
        return min;
    }

    public static void main(String[] args) {
        List<List<Integer>> w0 = new ArrayList<>();
        w0.add(new ArrayList<>() {{ add(1); }});
        w0.add(new ArrayList<>() {{ add(1); }});
        w0.add(new ArrayList<>() {{ add(1); }});
        System.out.println(new Solution().leastBricks(w0));

        List<List<Integer>> w1 = new ArrayList<>();
        w1.add(new ArrayList<>() {{ add(1); add(2); add(2); add(1); }});
        w1.add(new ArrayList<>() {{ add(3); add(1); add(2); }});
        w1.add(new ArrayList<>() {{ add(1); add(3); add(2); }});
        w1.add(new ArrayList<>() {{ add(2); add(4); }});
        w1.add(new ArrayList<>() {{ add(3); add(1); add(2); }});
        w1.add(new ArrayList<>() {{ add(1); add(3); add(1); add(1); }});
        System.out.println(new Solution().leastBricks(w1));

        List<List<Integer>> w2 = new ArrayList<>();
        w2.add(new ArrayList<>() {{ add(100000000); }});
        w2.add(new ArrayList<>() {{ add(100000000); }});
        w2.add(new ArrayList<>() {{ add(100000000); }});
        System.out.println(new Solution().leastBricks(w2));

        // [[1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1]]
        List<List<Integer>> w3 = new ArrayList<>();
        w3.add(new ArrayList<>() {{ add(1); add(1); add(1); add(1); add(1); add(1); add(1); add(1); add(1); add(1); }});
        w3.add(new ArrayList<>() {{ add(1); add(1); add(1); add(1); add(1); add(1); add(1); add(1); add(1); add(1); }});
        w3.add(new ArrayList<>() {{ add(1); add(1); add(1); add(1); add(1); add(1); add(1); add(1); add(1); add(1); }});
        System.out.println(new Solution().leastBricks(w3));
    }

}
