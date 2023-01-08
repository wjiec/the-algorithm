package weekly.w327.D;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 6306. Time to Cross a Bridge
 *
 * https://leetcode.cn/contest/weekly-contest-327/problems/time-to-cross-a-bridge/
 *
 * There are k workers who want to move n boxes from an old warehouse to a new one.
 * You are given the two integers n and k, and a 2D integer array time of size k x 4
 * where time[i] = [leftToRighti, pickOldi, rightToLefti, putNewi].
 *
 * The warehouses are separated by a river and connected by a bridge.
 * The old warehouse is on the right bank of the river, and the new warehouse is on
 * the left bank of the river. Initially, all k workers are waiting on the left
 * side of the bridge.
 *
 * To move the boxes, the ith worker (0-indexed) can :
 *
 * Cross the bridge from the left bank (new warehouse) to the right
 * bank (old warehouse) in leftToRighti minutes.
 *
 * Pick a box from the old warehouse and return to the bridge in pickOldi minutes.
 * Different workers can pick up their boxes simultaneously.
 *
 * Cross the bridge from the right bank (old warehouse) to the left
 * bank (new warehouse) in rightToLefti minutes.
 *
 * Put the box in the new warehouse and return to the bridge in putNewi minutes.
 * Different workers can put their boxes simultaneously.
 *
 * A worker i is less efficient than a worker j if either condition is met:
 *
 * leftToRighti + rightToLefti > leftToRightj + rightToLeftj
 * leftToRighti + rightToLefti == leftToRightj + rightToLeftj and i > j
 * The following rules regulate the movement of the workers through the bridge :
 *
 * If a worker x reaches the bridge while another worker y is crossing the
 * bridge, x waits at their side of the bridge.
 *
 * If the bridge is free, the worker waiting on the right side of the bridge
 * gets to cross the bridge. If more than one worker is waiting on the right side, the
 * one with the lowest efficiency crosses first.
 *
 * If the bridge is free and no worker is waiting on the right side, and at least one
 * box remains at the old warehouse, the worker on the left side of the river gets to
 * cross the bridge. If more than one worker is waiting on the left side, the one with
 * the lowest efficiency crosses first.
 *
 * Return the instance of time at which the last worker reaches the left bank of the
 * river after all n boxes have been put in the new warehouse.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private static class Worker {
        private int available;
        private final int i, l2r, po, r2l, pn;

        private Worker(int i, int l2r, int po, int r2l, int pn) {
            this.i = i;
            this.l2r = l2r;
            this.po = po;
            this.r2l = r2l;
            this.pn = pn;
        }

        @Override
        public String toString() {
            return "Worker{" +
                "available=" + available +
                ", i=" + i +
                ", l2r=" + l2r +
                ", po=" + po +
                ", r2l=" + r2l +
                ", pn=" + pn +
                '}';
        }
    }

    public int findCrossingTime(int n, int k, int[][] time) {
        PriorityQueue<Worker> left = new PriorityQueue<>((a, b) -> {
            int aw = a.l2r + a.r2l, bw = b.l2r + b.r2l;
            if (aw != bw) return bw - aw;
            return b.i - a.i;
        });
        PriorityQueue<Worker> right = new PriorityQueue<>((a, b) -> {
            int aw = a.l2r + a.r2l, bw = b.l2r + b.r2l;
            if (aw != bw) return bw - aw;
            return b.i - a.i;
        });

        PriorityQueue<Worker> lAvailable = new PriorityQueue<>(Comparator.comparingInt(v -> v.available));
        PriorityQueue<Worker> rAvailable = new PriorityQueue<>(Comparator.comparingInt(v -> v.available));
        for (int i = 0; i < k; i++) {
            lAvailable.add(new Worker(i, time[i][0], time[i][1], time[i][2], time[i][3]));
        }

        int ans = 0;

        while (n > 0) {
            while (!lAvailable.isEmpty() && lAvailable.peek().available <= ans) left.add(lAvailable.remove());
            while (!rAvailable.isEmpty() && rAvailable.peek().available <= ans) right.add(rAvailable.remove());

            // 右边的人先走
            if (!right.isEmpty()) {
                Worker curr = right.remove();
                ans += curr.r2l;

                curr.available = ans + curr.pn;
                lAvailable.add(curr);

                if (--n == 0) break;
                continue;
            }

            // 左边走, 且只有在右边人手不足的时候才可以走
            if (!left.isEmpty() && rAvailable.size() < n) {
                Worker curr = left.remove();
                ans += curr.l2r;

                curr.available = ans + curr.po;
                rAvailable.add(curr);

                continue;
            }

            // 看看哪边先好
            assert !lAvailable.isEmpty() || !rAvailable.isEmpty();
            if (lAvailable.isEmpty()) ans = rAvailable.peek().available;
            else if (rAvailable.isEmpty()) ans = lAvailable.peek().available;
            else ans = Math.min(lAvailable.peek().available, rAvailable.peek().available);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findCrossingTime(1, 3, new int[][]{{1,1,2,1}, {1,1,3,1}, {1,1,4,1}}) == 6;
        assert new Solution().findCrossingTime(3, 2, new int[][]{{1,9,1,8}, {10,10,10,10}}) == 50;
    }

}
