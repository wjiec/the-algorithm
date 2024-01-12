package weekly.w379.C;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.TreeSet;

/**
 * 3002. Maximum Size of a Set After Removals
 *
 * https://leetcode.cn/contest/weekly-contest-379/problems/maximum-size-of-a-set-after-removals/
 *
 * You are given two 0-indexed integer arrays nums1 and nums2 of even length n.
 *
 * You must remove n / 2 elements from nums1 and n / 2 elements from nums2.
 * After the removals, you insert the remaining elements of nums1 and nums2 into a set s.
 *
 * Return the maximum possible size of the set s.
 */

public class Solution {

    public int maximumSetSize(int[] nums1, int[] nums2) {
        int rm1 = 0, rm2 = 0, half = nums1.length / 2;
        TreeSet<Integer> s1 = new TreeSet<>();
        TreeSet<Integer> s2 = new TreeSet<>();
        for (var v : nums1) if (!s1.add(v)) rm1++;
        for (var v : nums2) if (!s2.add(v)) rm2++;

        // 如果已经删的足够多了, 那么直接就得到答案了
        if (rm1 >= half && rm2 >= half) { s1.addAll(s2); return s1.size(); }

        // 求出两个集合重复的元素
        Queue<Integer> dup = new ArrayDeque<>();
        for (var v : s1) if (s2.contains(v)) dup.add(v);

        // 优先删够重复元素
        for (; rm1 < half && !dup.isEmpty(); rm1++) s1.remove(dup.remove());
        for (; rm2 < half && !dup.isEmpty(); rm2++) s2.remove(dup.remove());

        // 如果还不够，就没办法了
        for (; rm1 < half; rm1++) s1.pollFirst();
        for (; rm2 < half; rm2++) s2.pollFirst();

        s1.addAll(s2);
        return s1.size();
    }

    public static void main(String[] args) {
        assert new Solution().maximumSetSize(new int[]{1,3,3,2}, new int[]{2,2,1,3}) == 3;

        assert new Solution().maximumSetSize(new int[]{1,2,1,2}, new int[]{1,1,1,1}) == 2;
        assert new Solution().maximumSetSize(new int[]{1,2,3,4,5,6}, new int[]{2,3,2,3,2,3}) == 5;
        assert new Solution().maximumSetSize(new int[]{1,1,2,2,3,3}, new int[]{4,4,5,5,6,6}) == 6;
    }

}
