package weekly.w460.C;

import ability.Benchmark;

import java.util.*;

/**
 * Q3. Minimum Jumps to Reach End via Prime Teleportation
 *
 * https://leetcode.cn/contest/weekly-contest-460/problems/minimum-jumps-to-reach-end-via-prime-teleportation/
 *
 * You are given an integer array nums of length n.
 *
 * You start at index 0, and your goal is to reach index n - 1.
 *
 * From any index i, you may perform one of the following operations:
 *
 * Adjacent Step: Jump to index i + 1 or i - 1, if the index is within bounds.
 *
 * Prime Teleportation: If nums[i] is a prime number p, you may instantly jump
 * to any index j != i such that nums[j] % p == 0.
 *
 * Return the minimum number of jumps required to reach index n - 1.
 */

public class Solution {

    private static final int MAX_N = 1_000_001;
    private static final List<Integer>[] factors = new List[MAX_N];
    static {
        Arrays.setAll(factors, i -> new ArrayList<>());
        for (int i = 2; i < MAX_N; i++) {
            if (factors[i].isEmpty()) {
                for (int j = i; j < MAX_N; j += i) {
                    factors[j].add(i);
                }
            }
        }
    }

    public int minJumps(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (var p : factors[nums[i]]) {
                g.computeIfAbsent(p, k -> new ArrayList<>()).add(i);
            }
        }

        boolean[] seen = new boolean[n]; seen[0] = true;
        Queue<int[]> q = new ArrayDeque<>(); q.add(new int[]{0, 0});
        while (!q.isEmpty()) {
            var curr = q.remove(); int i = curr[0], s = curr[1];
            if (i == n - 1) return s;
            if (!seen[i + 1]) { seen[i + 1] = true; q.add(new int[]{i + 1, s + 1}); }
            if (i > 0 && !seen[i - 1]) { seen[i - 1] = true; q.add(new int[]{i - 1, s + 1}); }
            if (g.containsKey(nums[i])) {
                for (var jump : g.get(nums[i])) {
                    if (!seen[jump]) {
                        seen[jump] = true;
                        q.add(new int[]{jump, s + 1});
                    }
                }
                g.remove(nums[i]);
            }
        }
        return n - 1;
    }

    public static void main(String[] args) {
        Benchmark.benchmark("", () -> {
            assert new Solution().minJumps(new int[]{1,2,4,6}) == 2;
            assert new Solution().minJumps(new int[]{2,3,4,7,9}) == 2;
            assert new Solution().minJumps(new int[]{4,6,5,8}) == 3;
        });
    }

}
