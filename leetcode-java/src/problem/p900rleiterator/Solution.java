package problem.p900rleiterator;

/**
 * 900. RLE Iterator
 *
 * https://leetcode.cn/problems/rle-iterator/
 *
 * We can use run-length encoding (i.e., RLE) to encode a sequence of integers. In a run-length encoded array of
 * even length encoding (0-indexed), for all even i, encoding[i] tells us the number of times that
 * the non-negative integer value encoding[i + 1] is repeated in the sequence.
 *
 * For example, the sequence arr = [8,8,8,5,5] can be encoded to be encoding = [3,8,2,5].
 * encoding = [3,8,0,9,2,5] and encoding = [2,8,1,8,2,5] are also valid RLE of arr.
 *
 * Given a run-length encoded array, design an iterator that iterates through it.
 *
 * Implement the RLEIterator class:
 *
 * RLEIterator(int[] encoded) Initializes the object with the encoded array encoded.
 * int next(int n) Exhausts the next n elements and returns the last element exhausted in this way.
 * If there is no element left to exhaust, return -1 instead.
 */

public class Solution {

    private static class RLEIterator {
        private int curr = 0;
        private final int[] enc;
        public RLEIterator(int[] encoding) { enc = encoding; }
        public int next(int n) {
            while (curr < enc.length && n != 0) {
                if (enc[curr] > n) {
                    enc[curr] -= n;
                    return enc[curr + 1];
                } else if (enc[curr] == n) {
                    curr += 2;
                    return enc[curr - 1];
                } else {
                    n -= enc[curr];
                    curr += 2;
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        RLEIterator rLEIterator = new RLEIterator(new int[]{3, 8, 0, 9, 2, 5});
        assert rLEIterator.next(2) == 8;
        assert rLEIterator.next(1) == 8;
        assert rLEIterator.next(1) == 5;
        assert rLEIterator.next(2) == -1;
    }

}
