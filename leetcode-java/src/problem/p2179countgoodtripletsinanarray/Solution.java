package problem.p2179countgoodtripletsinanarray;

import common.TODO;

/**
 * 2179. Count Good Triplets in an Array
 *
 * https://leetcode.cn/problems/count-good-triplets-in-an-array/
 *
 * You are given two 0-indexed arrays nums1 and nums2 of length n, both of
 * which are permutations of [0, 1, ..., n - 1].
 *
 * A good triplet is a set of 3 distinct values which are present in increasing
 * order by position both in nums1 and nums2. In other words, if we consider
 * pos1v as the index of the value v in nums1 and pos2v as the index of the
 * value v in nums2, then a good triplet will be a set (x, y, z)
 * where 0 <= x, y, z <= n - 1, such that pos1x < pos1y < pos1z and pos2x < pos2y < pos2z.
 *
 * Return the total number of good triplets.
 */

@TODO
public class Solution {

    public long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;
        // 对 nums1 进行置换, 将其变成 [0, 1, 2, ..., n - 1]
        int[] displace = new int[n];
        for (int i = 0; i < n; i++) displace[nums1[i]] = i;

        long ans = 0;
        int[] tree = new int[n + 1];
        for (int i = 1; i < n - 1; i++) {
            for (var v = displace[nums2[i - 1]] + 1; v < tree.length; v += v & -v) tree[v]++;

            int y = displace[nums2[i]], less = 0;
            for (var v = y; v > 0; v &= v - 1) less += tree[v];

            ans += (long) less * (n - 1 - y - (i - less));
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
