package weekly.w419.D;

import common.Checker;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 3321. Find X-Sum of All K-Long Subarrays II
 *
 * https://leetcode.cn/contest/weekly-contest-419/problems/find-x-sum-of-all-k-long-subarrays-ii/
 *
 * You are given an array nums of n integers and two integers k and x.
 *
 * The x-sum of an array is calculated by the following procedure:
 *
 * Count the occurrences of all elements in the array.
 * Keep only the occurrences of the top x most frequent elements. If two elements have the same
 * number of occurrences, the element with the bigger value is considered more frequent.
 * Calculate the sum of the resulting array.
 * Note that if an array has less than x distinct elements, its x-sum is the sum of the array.
 *
 * Return an integer array answer of length n - k + 1 where answer[i] is the x-sum of the
 * subarray nums[i..i + k - 1].
 */

public class Solution {

    private static class Countable {
        private long sum = 0;
        private final Map<Integer, Integer> grp = new HashMap<>();
        private final TreeMap<Integer, TreeSet<Integer>> rev = new TreeMap<>();

        public record Pair(int value, int count) {
            public boolean gt(Pair p) { return count > p.count || (count == p.count && value > p.value); }
        }

        public int size() { return grp.size(); }
        public boolean contains(int v) { return grp.containsKey(v); }
        public void add(int v) {
            int sz = grp.merge(v, 1, Integer::sum);

            rev.computeIfAbsent(sz - 1, k -> new TreeSet<>()).remove(v);
            if (rev.get(sz - 1).isEmpty()) rev.remove(sz - 1);

            rev.computeIfAbsent(sz, k -> new TreeSet<>()).add(v);
            sum += v;
        }
        public boolean remove(int v) {
            if (!grp.containsKey(v)) return false;

            int sz = grp.merge(v, -1, Integer::sum);
            if (sz == 0) grp.remove(v);

            rev.computeIfAbsent(sz + 1, k ->  new TreeSet<>()).remove(v);
            if (rev.get(sz + 1).isEmpty()) rev.remove(sz + 1);

            if (sz > 0) rev.computeIfAbsent(sz, k -> new TreeSet<>()).add(v);
            sum -= v;
            return true;
        }
        public Pair first() {
            if (grp.isEmpty()) return new Pair(-1, -1);
            int value = rev.firstEntry().getValue().first();
            return new Pair(value, grp.get(value));
        }
        public Pair removeFirst() {
            int value = rev.firstEntry().getValue().first();

            int cnt = grp.remove(value);
            rev.get(cnt).remove(value);
            if (rev.get(cnt).isEmpty()) rev.remove(cnt);

            sum -= (long) value * cnt;
            return new Pair(value, cnt);
        }
        public Pair last() {
            if (grp.isEmpty()) return new Pair(-1, -1);
            int value = rev.lastEntry().getValue().last();
            return new Pair(value, grp.get(value));
        }
        public Pair removeLast() {
            int value = rev.lastEntry().getValue().last();

            int cnt = grp.remove(value);
            rev.get(cnt).remove(value);
            if (rev.get(cnt).isEmpty()) rev.remove(cnt);

            sum -= (long) value * cnt;
            return new Pair(value, cnt);
        }
        public void add(Pair p) {
            Integer before = grp.get(p.value);
            int after = grp.merge(p.value, p.count, Integer::sum);

            if (before != null) {
                rev.get(before).remove(p.value);
                if (rev.get(before).isEmpty()) rev.remove(before);
            }

            sum += (long) p.value * (after - (before == null ? 0 : before));
            rev.computeIfAbsent(after, k -> new TreeSet<>()).add(p.value);
        }

        @Override public String toString() { return rev.toString(); }
    }

    public long[] findXSum(int[] nums, int k, int x) {
        Countable left = new Countable(), right = new Countable();

        int n = nums.length;
        long[] ans = new long[n - k + 1];
        for (int i = 0; i < n; i++) {
            if (right.contains(nums[i])) right.add(nums[i]);
            else left.add(nums[i]);
            while (left.size() > x) right.add(left.removeFirst());
            while (left.size() < x && right.size() > 0) left.add(right.removeLast());
            while (right.last().gt(left.first())) {
                right.add(left.removeFirst());
                left.add(right.removeLast());
            }

            if (i - k + 1 >= 0) {
                ans[i - k + 1] = left.sum;
                if (!left.remove(nums[i - k + 1])) right.remove(nums[i - k + 1]);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().findXSum(new int[]{1,6,5,5,5}, 2, 1), new long[]{6,6,10,10});

        assert Checker.check(new Solution().findXSum(new int[]{1,1,2,2,3,4,2,3}, 6, 2), new long[]{6,10,12});
        assert Checker.check(new Solution().findXSum(new int[]{3,8,7,8,7,5}, 2, 2), new long[]{11,15,15,15,12});
    }

}
