package problem.p1649createsortedarraythroughinstructions;

/**
 * 1649. Create Sorted Array through Instructions
 *
 * https://leetcode.cn/problems/create-sorted-array-through-instructions
 *
 * Given an integer array instructions, you are asked to create a sorted array from the elements in instructions.
 * You start with an empty container nums. For each element from left to right in instructions, insert it into nums.
 *
 * The cost of each insertion is the minimum of the following:
 *
 * The number of elements currently in nums that are strictly less than instructions[i].
 * The number of elements currently in nums that are strictly greater than instructions[i].
 * For example, if inserting element 3 into nums = [1,2,3,5], the cost of insertion is
 * min(2, 1) (elements 1 and 2 are less than 3, element 5 is greater than 3) and nums
 * will become [1,2,3,3,5].
 *
 * Return the total cost to insert all elements from instructions into nums.
 *
 * Since the answer may be large, return it modulo 109 + 7
 */

public class Solution {

    public int createSortedArray(int[] instructions) {
        long ans = 0, MOD = 1_000_000_007;
        for (int i = 0; i < instructions.length; i++) {
            ans += Math.min(query(instructions[i] - 1), i - query(instructions[i]));
            update(instructions[i]);
        }
        return (int) (ans % MOD);
    }

    private final int[] tree = new int[101000];

    private int query(int v) {
        int ans = 0;
        while (v != 0) {
            ans += tree[v];
            v -= lowbit(v);
        }
        return ans;
    }

    private void update(int v) {
        while (v < tree.length) {
            tree[v] += 1;
            v += lowbit(v);
        }
    }

    private int lowbit(int x) { return x & (-x); }

    public static void main(String[] args) {
        assert new Solution().createSortedArray(new int[]{1,5,6,2}) == 1;
        assert new Solution().createSortedArray(new int[]{1,2,3,6,5,4}) == 3;
        assert new Solution().createSortedArray(new int[]{1,3,3,3,2,4,2,1,2}) == 4;
    }

}
