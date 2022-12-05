package daily.d221205p1687deliveringboxesfromstoragetoports;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1687. Delivering Boxes from Storage to Ports
 *
 * https://leetcode.cn/problems/delivering-boxes-from-storage-to-ports/
 *
 * You have the task of delivering some boxes from storage to their ports using only one ship.
 * However, this ship has a limit on the number of boxes and the total weight that it can carry.
 *
 * You are given an array boxes, where boxes[i] = [portsi, weighti], and three
 * integers portsCount, maxBoxes, and maxWeight.
 *
 * portsi is the port where you need to deliver the ith box and weightsi is the weight of the ith box.
 * portsCount is the number of ports.
 *
 * maxBoxes and maxWeight are the respective box and weight limits of the ship.
 * The boxes need to be delivered in the order they are given. The ship will follow these steps:
 *
 * The ship will take some number of boxes from the boxes queue, not violating
 * the maxBoxes and maxWeight constraints.
 *
 * For each loaded box in order, the ship will make a trip to the port the box needs
 * to be delivered to and deliver it. If the ship is already at the correct port, no
 * trip is needed, and the box can immediately be delivered.
 *
 * The ship then makes a return trip to storage to take more boxes from the queue.
 *
 * The ship must end at storage after all the boxes have been delivered.
 *
 * Return the minimum number of trips the ship needs to make to deliver all boxes to their respective ports.
 */

public class Solution {

    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.length;
        int[] diffPort = new int[n + 1];
        int[] weightSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            weightSum[i] = weightSum[i - 1] + boxes[i - 1][1];
            if (i > 1) diffPort[i] = diffPort[i - 1] + (boxes[i - 2][0] != boxes[i - 1][0] ? 1 : 0);
        }

        Deque<Integer> window = new ArrayDeque<>();
        window.addLast(0);

        int[] dp = new int[n + 1];
        int[] mi = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            while (!window.isEmpty() && (i - window.peekFirst() > maxBoxes || weightSum[i] - weightSum[window.peekFirst()] > maxWeight)) {
                window.removeFirst();
            }

            if (!window.isEmpty()) dp[i] = mi[window.peekFirst()] + diffPort[i] + 2;

            if (i != n) {
                mi[i] = dp[i] - diffPort[i + 1];
                while (!window.isEmpty() && mi[i] <= mi[window.peekLast()]) {
                    window.removeLast();
                }
                window.addLast(i);
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        assert new Solution().boxDelivering(new int[][]{{1,1},{2,1},{1,1}}, 2, 3, 3) == 4;
        assert new Solution().boxDelivering(new int[][]{{1,2},{3,3},{3,1},{3,1},{2,4}}, 3, 3, 6) == 6;
        assert new Solution().boxDelivering(new int[][]{{1,4},{1,2},{2,1},{2,1},{3,2},{3,4}}, 3, 6, 7) == 6;
        assert new Solution().boxDelivering(new int[][]{{2,4},{2,5},{3,1},{3,2},{3,7},{3,1},{4,4},{1,3},{5,2}}, 5, 5, 7) == 14;
    }

}
