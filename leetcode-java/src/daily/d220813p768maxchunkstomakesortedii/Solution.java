package daily.d220813p768maxchunkstomakesortedii;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 768. Max Chunks To Make Sorted II
 *
 * https://leetcode.cn/problems/max-chunks-to-make-sorted-ii/
 *
 * You are given an integer array arr.
 *
 * We split arr into some number of chunks (i.e., partitions), and individually sort
 * each chunk. After concatenating them, the result should equal the sorted array.
 *
 * Return the largest number of chunks we can make to sort the array.
 */

@SuppressWarnings("ConstantConditions")
public class Solution {

    public int maxChunksToSorted(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);

        for (var val : arr) {
            int max = Math.max(val, stack.peek());
            while (stack.peek() > val) stack.pop();
            stack.push(max);
        }
        return stack.size() - 1;
    }

    public static void main(String[] args) {
        assert new Solution().maxChunksToSorted(new int[]{5,1,1,8,1,6,5,9,7,8}) == 1;

        assert new Solution().maxChunksToSorted(new int[]{5,4,3,2,1}) == 1;
        assert new Solution().maxChunksToSorted(new int[]{2,1,3,4,4}) == 4;
        assert new Solution().maxChunksToSorted(new int[]{1,1,0,0,1}) == 2;
    }

}
