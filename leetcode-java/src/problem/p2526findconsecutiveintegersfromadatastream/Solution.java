package problem.p2526findconsecutiveintegersfromadatastream;

/**
 * 2526. Find Consecutive Integers from a Data Stream
 *
 * https://leetcode.cn/problems/find-consecutive-integers-from-a-data-stream/
 *
 * For a stream of integers, implement a data structure that checks
 * if the last k integers parsed in the stream are equal to value.
 *
 * Implement the DataStream class:
 *
 * DataStream(int value, int k) Initializes the object with an empty
 * integer stream and the two integers value and k.
 *
 * boolean consec(int num) Adds num to the stream of integers. Returns true
 * if the last k integers are equal to value, and false otherwise.
 *
 * If there are less than k integers, the condition does not hold true, so returns false.
 */

public class Solution {

    private static class DataStream {
        private int cnt = 0;
        private final int value, k;
        public DataStream(int value, int k) { this.value = value; this.k = k; }
        public boolean consec(int num) { return (cnt = num == value ? (cnt + 1) : 0) >= k; }
    }

    public static void main(String[] args) {
        DataStream dataStream = new DataStream(4, 3); // value = 4, k = 3
        assert !dataStream.consec(4);
        assert !dataStream.consec(4);
        assert dataStream.consec(4);
        assert !dataStream.consec(3);
    }

}
