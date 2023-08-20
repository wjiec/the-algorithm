package weekly.bw111.C;

import java.util.List;

/**
 * 6941. Sorting Three Groups
 *
 * https://leetcode.cn/contest/biweekly-contest-111/problems/sorting-three-groups/
 *
 * You are given a 0-indexed integer array nums of length n.
 *
 * The numbers from 0 to n - 1 are divided into three groups numbered from 1 to 3, where
 * number i belongs to group nums[i]. Notice that some groups may be empty.
 *
 * You are allowed to perform this operation any number of times:
 *
 * Pick number x and change its group. More formally, change nums[x] to any number from 1 to 3.
 * A new array res is constructed using the following procedure:
 *
 * Sort the numbers in each group independently.
 * Append the elements of groups 1, 2, and 3 to res in this order.
 * Array nums is called a beautiful array if the constructed array res is sorted in non-decreasing order.
 *
 * Return the minimum number of operations to make nums a beautiful array.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int minimumOperations(List<Integer> nums) {
        // dp[i][j] 表示到第 i - 1 位, 将其作为组 j 的最小操作数
        int pa = 0, pb = 0, pc = 0;
        for (var v : nums) {
            int ca = 0, cb = 0, cc = 0;
            switch (v) {
                case 1 -> {
                    ca = pa;
                    cb = pb + 1; // 变成 2
                    cc = pc + 1; // 变成 3
                }
                case 2 -> {
                    ca = pa + 1; // 变成 1
                    cb = Math.min(pb, pa);
                    cc = pc + 1; // 变成 3
                }
                case 3 -> {
                    ca = pa + 1; // 变成 1
                    cb = pb + 1; // 变成 2
                    cc = Math.min(pa, Math.min(pb, pc));
                }
            }

            pa = ca; pb = cb; pc = cc;
        }

        return Math.min(pa, Math.min(pb, pc));
    }

    public static void main(String[] args) {
        assert new Solution().minimumOperations(List.of(2,1,3,2,1)) == 3;
        assert new Solution().minimumOperations(List.of(1,3,2,1,3,3)) == 2;
        assert new Solution().minimumOperations(List.of(2,2,2,2,3,3)) == 0;
    }

}
