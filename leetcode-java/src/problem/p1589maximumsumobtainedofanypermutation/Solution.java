package problem.p1589maximumsumobtainedofanypermutation;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1589. Maximum Sum Obtained of Any Permutation
 *
 * https://leetcode.cn/problems/maximum-sum-obtained-of-any-permutation/
 *
 * We have an array of integers, nums, and an array of requests where requests[i] = [starti, endi].
 * The ith request asks for the sum of nums[starti] + nums[starti + 1] + ... + nums[endi - 1] + nums[endi].
 * Both starti and endi are 0-indexed.
 *
 * Return the maximum total sum of all requests among all permutations of nums.
 *
 * Since the answer may be too large, return it modulo 109 + 7.
 */

public class Solution {

    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        Arrays.sort(requests, Comparator.comparingInt(a -> a[0]));

        int[] freq = new int[nums.length];
        int nl = nums.length, rl = requests.length;
        PriorityQueue<Integer> last = new PriorityQueue<>();
        for (int i = 0, r = 0; i < nl; i++) {
            while (r < rl && requests[r][0] <= i) last.add(requests[r++][1]);
            while (!last.isEmpty() && last.peek() < i) last.remove();
            freq[i] = last.size();
        }

        Arrays.sort(freq);
        Arrays.sort(nums);

        long ans = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            ans = (ans + (long) freq[i] * nums[i]) % 1_000_000_007;
        }

        return (int) ans;
    }

    private static class DiffArray {
        public int maxSumRangeQuery(int[] nums, int[][] requests) {
            int n = nums.length;
            int[] freq = new int[n];
            for (var request : requests) {
                freq[request[0]]++;
                if (request[1] + 1 < n)
                    freq[request[1] + 1]--;
            }
            for (int i = 1; i < freq.length; i++) {
                freq[i] += freq[i - 1];
            }

            Arrays.sort(freq);
            Arrays.sort(nums);

            long ans = 0;
            for (int i = nums.length - 1; i >= 0; i--) {
                ans += (long) nums[i] * freq[i];
            }
            return (int) (ans % 1_000_000_007);
        }
    }

    public static void main(String[] args) {
        assert new Solution().maxSumRangeQuery(new int[]{1,2,3,4,5}, new int[][]{{1,3},{0,1}}) == 19;
        assert new Solution().maxSumRangeQuery(new int[]{1,2,3,4,5,6}, new int[][]{{0,1}}) == 11;
        assert new Solution().maxSumRangeQuery(new int[]{1,2,3,4,5,10}, new int[][]{{0,2},{1,3},{1,1}}) == 47;

        assert new DiffArray().maxSumRangeQuery(new int[]{1,2,3,4,5}, new int[][]{{1,3},{0,1}}) == 19;
        assert new DiffArray().maxSumRangeQuery(new int[]{1,2,3,4,5,6}, new int[][]{{0,1}}) == 11;
        assert new DiffArray().maxSumRangeQuery(new int[]{1,2,3,4,5,10}, new int[][]{{0,2},{1,3},{1,1}}) == 47;
    }

}
