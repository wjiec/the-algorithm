package problem.p975oddevenjump;

import java.util.TreeMap;

/**
 * 975. Odd Even Jump
 *
 * https://leetcode.cn/problems/odd-even-jump
 *
 * You are given an integer array arr. From some starting index, you can make a series of jumps.
 * The (1st, 3rd, 5th, ...) jumps in the series are called odd-numbered jumps, and the (2nd, 4th, 6th, ...)
 * jumps in the series are called even-numbered jumps. Note that the jumps are numbered, not the indices.
 *
 * You may jump forward from index i to index j (with i < j) in the following way:
 *
 * During odd-numbered jumps (i.e., jumps 1, 3, 5, ...), you jump to the index j such
 * that arr[i] <= arr[j] and arr[j] is the smallest possible value. If there are multiple
 * such indices j, you can only jump to the smallest such index j.
 *
 * During even-numbered jumps (i.e., jumps 2, 4, 6, ...), you jump to the index j such
 * that arr[i] >= arr[j] and arr[j] is the largest possible value. If there are multiple
 * such indices j, you can only jump to the smallest such index j.
 *
 * It may be the case that for some index i, there are no legal jumps.
 *
 * A starting index is good if, starting from that index, you can reach the end of the
 * array (index arr.length - 1) by jumping some number of times (possibly 0 or more than once).
 *
 * Return the number of good starting indices.
 */

public class Solution {

    public int oddEvenJumps(int[] arr) {
        boolean[] odd = new boolean[arr.length];
        boolean[] even = new boolean[arr.length];
        TreeMap<Integer, Integer> last = new TreeMap<>();

        last.put(arr[arr.length - 1], arr.length - 1);
        odd[arr.length - 1] = even[arr.length - 1] = true;

        for (int i = arr.length - 2; i >= 0; i--) {
            // 从当前位置进行奇数次跳跃是否能到末尾, 需要找到大于等于自身的最小值
            var next1 = last.ceilingEntry(arr[i]);
            odd[i] = next1 != null && even[next1.getValue()];

            // 从当前位置进行偶数次跳跃是否能到达末尾, 需要找到小于等于自身的最大值
            var next2 = last.floorEntry(arr[i]);
            even[i] = next2 != null && odd[next2.getValue()];

            last.put(arr[i], i);
        }

        int ans = 0;
        for (var v : odd) ans += v ? 1 : 0;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().oddEvenJumps(new int[]{10,13,12,14,15}) == 2;
        assert new Solution().oddEvenJumps(new int[]{2,3,1,1,4}) == 3;
        assert new Solution().oddEvenJumps(new int[]{5,1,3,4,2}) == 3;
    }

}
