package problem.p134gasstation;

/**
 * 134. Gas Station
 *
 * https://leetcode-cn.com/problems/gas-station/
 *
 * There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
 *
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from
 * the ith station to its next (i + 1)th station.
 *
 * You begin the journey with an empty tank at one of the gas stations.
 *
 * Given two integer arrays gas and cost, return the starting gas station's index
 * if you can travel around the circuit once in the clockwise direction,
 * otherwise return -1. If there exists a solution, it is guaranteed to be unique
 */

public class Solution {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        for (int i = 0, n = gas.length; i < n; ) {
            int remains = 0, count = 0;
            for (; count < n; count++) {
                int x = (i + count) % n;
                remains += gas[x] - cost[x];
                if (remains < 0) break;
            }

            if (count == n) return i;
            i += count + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}) == 3;
        assert new Solution().canCompleteCircuit(new int[]{2,3,4}, new int[]{3,4,3}) == -1;
    }

}
