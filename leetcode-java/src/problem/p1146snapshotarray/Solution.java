package problem.p1146snapshotarray;

import java.util.TreeMap;

/**
 * 1146. Snapshot Array
 *
 * https://leetcode.cn/problems/snapshot-array/
 *
 * Implement a SnapshotArray that supports the following interface:
 *
 * SnapshotArray(int length) initializes an array-like data structure with the given length.
 * Initially, each element equals 0.
 *
 * void set(index, val) sets the element at the given index to be equal to val.
 * int snap() takes a snapshot of the array and returns the snap_id: the total
 * number of times we called snap() minus 1.
 *
 * int get(index, snap_id) returns the value at the given index, at the time
 * we took the snapshot with the given snap_id
 */

public class Solution {

    @SuppressWarnings("unchecked")
    private static class SnapshotArray {
        private int snapshotId = 0;
        private final TreeMap<Integer, Integer>[] data;
        public SnapshotArray(int length) { data = new TreeMap[length]; }
        public int snap() { return snapshotId++; }

        public void set(int index, int val) {
            if (data[index] == null) data[index] = new TreeMap<>();
            data[index].put(snapshotId, val);
        }

        public int get(int index, int snap_id) {
            if (data[index] == null) return 0;
            Integer floorKey = data[index].floorKey(snap_id);
            if (floorKey == null) return 0;
            return data[index].get(floorKey);
        }
    }

    public static void main(String[] args) {
        SnapshotArray snapshotArr = new SnapshotArray(3);
        snapshotArr.set(0,5);
        assert snapshotArr.snap() == 0;
        snapshotArr.set(0,6);
        assert snapshotArr.get(0,0) == 5;

        SnapshotArray sa = new SnapshotArray(1);
        sa.set(0, 15);
        assert sa.snap() == 0;
        assert sa.snap() == 1;
        assert sa.snap() == 2;
        assert sa.get(0, 2) == 15;
        assert sa.snap() == 3;
        assert sa.snap() == 4;
        assert sa.get(0, 0) == 15;
    }


}
