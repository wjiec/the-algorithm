package weekly.w440.D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 3480. Maximize Subarrays After Removing One Conflicting Pair
 *
 * https://leetcode.cn/contest/weekly-contest-440/problems/maximize-subarrays-after-removing-one-conflicting-pair/
 *
 * You are given an integer n which represents an array nums containing the numbers from 1 to n in order. Additionally, you are given a 2D array conflictingPairs, where conflictingPairs[i] = [a, b] indicates that a and b form a conflicting pair.
 *
 * Remove exactly one element from conflictingPairs. Afterward, count the number of non-empty subarrays of nums which do not contain both a and b for any remaining conflicting pair [a, b].
 *
 * Return the maximum number of subarrays possible after removing exactly one conflicting pair.
 */

public class Solution {

    /** @noinspection unchecked*/
    public long maxSubarrays(int n, int[][] conflictingPairs) {
        // 在不删除 conflictingPairs 的情况下, 枚举子数组的起始位置 i
        // 子数组的右边最长可以扩展到所有所有 b0 = min(pair.b) where pair.a >= i 位置
        // 也就是右端点在范围 [i, b0) 内, 满足条件的子数组数量为 b0 - i
        //
        // 删除其中一个 pair 时, 枚举子数组的起始位置 i, 如果删除某个 pair
        // 我们可以从原本的最远 b0 位置扩展到 b1 位置, 额外增加的长度为 b1 - b0
        //  - 实际上也就是我们从最小的位置 b0 变成了次小的位置 b1

        // 为了方便找到所有相同冲突位置的最小位置 b0, 首先对 pairs 进行分组
        List<Integer>[] groups = new List[n + 1];
        Arrays.setAll(groups, i -> new ArrayList<>());
        for (var p : conflictingPairs) groups[Math.min(p[0], p[1])].add(Math.max(p[0], p[1]));

        long ans = 0; int b0 = n + 1, b1 = n + 1;
        // extra[b0] 表示当删除 pair.b == b0 时可以扩展多少次
        long[] extra = new long[n + 2];
        // 所以我们从后往前枚举位置 i, 计算可以有多少个子数组
        for (int i = n; i > 0; i--) {
            // 找到最小的 b0 和次小的 b1
            if (!groups[i].isEmpty()) {
                groups[i].add(b0); groups[i].add(b1);
                groups[i].sort(Integer::compare);
                b0 = groups[i].get(0);
                b1 = groups[i].get(1);
            }

            // 不删除情况下的子数组数量
            ans += b0 - i;
            // 如果删除 b0 的情况下可以额外增加的数量
            extra[b0] += b1 - b0;
        }

        long maxExtra = 0;
        for (var v : extra) maxExtra = Math.max(maxExtra, v);
        return ans + maxExtra;
    }

    public static void main(String[] args) {
    }

}
