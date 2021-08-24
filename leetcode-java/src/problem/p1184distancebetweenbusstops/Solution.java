package problem.p1184distancebetweenbusstops;

/**
 * 1184. Distance Between Bus Stops
 *
 * https://leetcode-cn.com/problems/distance-between-bus-stops/
 *
 * A bus has n stops numbered from 0 to n - 1 that form a circle.
 * We know the distance between all pairs of neighboring stops
 * where distance[i] is the distance between the stops number i and (i + 1) % n.
 *
 * The bus goes along both directions i.e. clockwise and counterclockwise.
 *
 * Return the shortest distance between the given start and destination stops.
 */

public class Solution {

    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int all = 0, pre = 0, s = Math.min(start, destination), d = Math.max(start, destination);
        for (int i = 0, l = distance.length; i < l; i++) {
            all += distance[i];
            if (i >= s && i < d) pre += distance[i];
        }
        return Math.min(pre, all - pre);
    }

    public static void main(String[] args) {
        assert new Solution().distanceBetweenBusStops(new int[]{7,10,1,12,11,14,5,0}, 7, 2) == 17;

        assert new Solution().distanceBetweenBusStops(new int[]{1,2,3,4}, 0, 1) == 1;
        assert new Solution().distanceBetweenBusStops(new int[]{1,2,3,4}, 0, 2) == 3;
        assert new Solution().distanceBetweenBusStops(new int[]{1,2,3,4}, 0, 3) == 4;
    }

}
