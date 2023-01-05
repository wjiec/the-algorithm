package daily.d230105p1803countpairswithxorinarange;

/**
 * 1803. Count Pairs With XOR in a Range
 *
 * https://leetcode.cn/problems/count-pairs-with-xor-in-a-range/
 *
 * Given a (0-indexed) integer array nums and two integers low and
 * high, return the number of nice pairs.
 *
 * A nice pair is a pair (i, j) where 0 <= i < j < nums.length and
 * low <= (nums[i] XOR nums[j]) <= high.
 */

public class Solution {

    public int countPairs(int[] nums, int low, int high) {
        return countPairs(nums, high) - countPairs(nums, low - 1);
    }

    private final static int HIGH_BIT = 14;

    private static class Trie {
        private int sum = 0;
        private final Trie[] next = new Trie[2];

        public void add(int v) {
            Trie curr = this;
            for (int i = HIGH_BIT; i >= 0; i--) {
                int bit = (v >> i) & 1;
                if (curr.next[bit] == null) curr.next[bit] = new Trie();
                curr = curr.next[bit]; curr.sum++;
            }
        }

        public int get(int v, int x) {
            Trie curr = this; int sum = 0;
            for (int i = HIGH_BIT; i >= 0; i--) {
                int bit = (v >> i) & 1;
                if (((x >> i) & 1) != 0) {
                    if (curr.next[bit] != null) sum += curr.next[bit].sum;
                    if (curr.next[bit ^ 1] == null) return sum;
                    curr = curr.next[bit ^ 1];
                } else {
                    if (curr.next[bit] == null) return sum;
                    curr = curr.next[bit];
                }
            }
            sum += curr.sum;
            return sum;
        }
    }

    public int countPairs(int[] nums, int limit) {
        Trie root = new Trie();
        int ans = 0, n = nums.length;
        for (int i = 1; i < n; i++) {
            root.add(nums[i - 1]);
            ans += root.get(nums[i], limit);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countPairs(new int[]{1,4,2,7}, 2, 6) == 6;
        assert new Solution().countPairs(new int[]{9,8,4,2,1}, 5, 14) == 8;
    }

}
