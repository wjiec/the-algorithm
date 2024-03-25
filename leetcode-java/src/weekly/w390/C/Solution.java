package weekly.w390.C;

import java.util.*;

/**
 * 3092. Most frequent IDs
 *
 * https://leetcode.cn/contest/weekly-contest-390/problems/most-frequent-ids/
 *
 * The problem involves tracking the frequency of IDs in a collection that changes over time.
 * You have two integer arrays, nums and freq, of equal length n. Each element in nums
 * represents an ID, and the corresponding element in freq indicates how many times that ID
 * should be added to or removed from the collection at each step.
 *
 * Addition of IDs: If freq[i] is positive, it means freq[i] IDs with the value nums[i] are added
 * to the collection at step i.
 *
 * Removal of IDs: If freq[i] is negative, it means -freq[i] IDs with the value nums[i] are removed
 * from the collection at step i.
 *
 * Return an array ans of length n, where ans[i] represents the count of the most frequent ID in
 * the collection after the ith step. If the collection is empty at any step, ans[i] should be 0 for that step.
 */

public class Solution {

    public long[] mostFrequentIDs(int[] nums, int[] freq) {
        Map<Integer, Long> m1 = new HashMap<>();
        TreeMap<Long, Set<Integer>> m2 = new TreeMap<>();

        long[] ans = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int k = nums[i], f = freq[i];
            long v = m1.merge(k, (long) f, Long::sum);
            m2.computeIfAbsent(v - f, x -> new HashSet<>()).remove(k);
            if (m2.get(v - f).isEmpty()) m2.remove(v - f);

            m2.computeIfAbsent(v, x -> new HashSet<>()).add(k);
            ans[i] = m2.lastKey();
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
