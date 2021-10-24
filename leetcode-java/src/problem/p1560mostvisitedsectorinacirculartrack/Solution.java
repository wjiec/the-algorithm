package problem.p1560mostvisitedsectorinacirculartrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 1560. Most Visited Sector in  a Circular Track
 *
 * https://leetcode-cn.com/problems/most-visited-sector-in-a-circular-track/
 *
 * Given an integer n and an integer array rounds.
 *
 * We have a circular track which consists of n sectors labeled from 1 to n.
 * A marathon will be held on this track, the marathon consists of m rounds.
 * The ith round starts at sector rounds[i - 1] and ends at sector rounds[i].
 *
 * For example, round 1 starts at sector rounds[0] and ends at sector rounds[1]
 *
 * Return an array of the most visited sectors sorted in ascending order.
 *
 * Notice that you circulate the track in ascending order of sector numbers
 * in the counter-clockwise direction (See the first example).
 */

public class Solution {

    public List<Integer> mostVisited(int n, int[] rounds) {
        List<Integer> ans = new ArrayList<>();
        int start = rounds[0], end = rounds[rounds.length - 1];
        if (start <= end) {
            for (int i = start; i <= end; i++) ans.add(i);
        } else {
            for (int i = 1; i <= end; i++) ans.add(i);
            for (int i = start; i <= n; i++) ans.add(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().mostVisited(4, new int[]{1,3,1,2}).equals(List.of(1,2));
        assert new Solution().mostVisited(2, new int[]{2,1,2,1,2,1,2,1,2}).equals(List.of(2));
        assert new Solution().mostVisited(7, new int[]{1,3,5,7}).equals(List.of(1,2,3,4,5,6,7));
    }

}
