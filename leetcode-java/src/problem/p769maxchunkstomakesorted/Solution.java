package problem.p769maxchunkstomakesorted;

/**
 * 769. Max Chunks To Make Sorted
 *
 * https://leetcode.cn/problems/max-chunks-to-make-sorted/
 *
 * You are given an integer array arr of length n that represents
 * a permutation of the integers in the range [0, n - 1].
 *
 * We split arr into some number of chunks (i.e., partitions), and individually sort each chunk.
 * After concatenating them, the result should equal the sorted array.
 *
 * Return the largest number of chunks we can make to sort the array.
 */

public class Solution {

    public int maxChunksToSorted(int[] arr) {
        int ans = 0, sorted = 0;
        for (int i = 0; i < arr.length; i++, ans++) {
            if (arr[i] != sorted) {
                int idx = i + 1, max = arr[i], n = 1;
                for (; idx < arr.length; idx++) {
                    if (arr[idx] > max) max = arr[idx];
                    if (++n == max - sorted + 1) break;
                }

                i = idx;
                sorted = max + 1;
            } else sorted++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxChunksToSorted(new int[]{4,3,2,1,0}) == 1;
        assert new Solution().maxChunksToSorted(new int[]{1,0,2,3,4}) == 4;
        assert new Solution().maxChunksToSorted(new int[]{0,1,2,3,4}) == 5;
        assert new Solution().maxChunksToSorted(new int[]{4,0,1,2,3}) == 1;
    }

}
