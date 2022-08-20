package problem.p1562findlatestgroupofsizem;

/**
 * 1562. Find Latest Group of Size M
 *
 * https://leetcode.cn/problems/find-latest-group-of-size-m/
 *
 * Given an array arr that represents a permutation of numbers from 1 to n.
 *
 * You have a binary string of size n that initially has all its bits set to zero.
 * At each step i (assuming both the binary string and arr are 1-indexed) from 1 to n, the
 * bit at position arr[i] is set to 1.
 *
 * You are also given an integer m. Find the latest step at which there exists a group of
 * ones of length m. A group of ones is a contiguous substring of 1's such that it cannot be
 * extended in either direction.
 *
 * Return the latest step at which there exists a group of ones of length exactly m.
 * If no such group exists, return -1.
 */

public class Solution {

    public int findLatestStep(int[] arr, int m) {
        int[] link = new int[arr.length + 2];

        int ans = -1, cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            int v = arr[i];
            int l = link[v - 1] != 0 ? link[v - 1] : v;
            int r = link[v + 1] != 0 ? link[v + 1] : v;

            if (v - l == m) cnt--;
            if (r - v == m) cnt--;
            if (r - l + 1 == m) cnt++;
            if (cnt > 0) ans = i + 1;

            link[l] = r; link[r] = l;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findLatestStep(new int[]{3,5,1,2,4}, 1) == 4;
        assert new Solution().findLatestStep(new int[]{3,1,5,4,2}, 2) == -1;
        assert new Solution().findLatestStep(new int[]{1}, 1) == 1;
        assert new Solution().findLatestStep(new int[]{2,1}, 2) == 2;
    }

}
