package weekly.w444.D;

import common.Tag;

import java.util.TreeSet;

/**
 * 3510. Minimum Pair Removal to Sort Array II
 *
 * https://leetcode.cn/contest/weekly-contest-444/problems/minimum-pair-removal-to-sort-array-ii/description/
 *
 * Given an array nums, you can perform the following operation any number of times:
 *
 * Select the adjacent pair with the minimum sum in nums. If multiple such pairs exist, choose the leftmost one.
 * Replace the pair with their sum.
 *
 * Return the minimum number of operations needed to make the array non-decreasing.
 *
 * An array is said to be non-decreasing if each element is greater than or equal to its previous element (if it exists).
 */

@Tag("Interesting")
public class Solution {

    private record Pair(long s, int i) {}

    /** @noinspection DataFlowIssue*/
    public int minimumPairRemoval(int[] nums) {
        // 需要维护相邻元素和 s, 以及相邻元素左边的下标 i, 组成一个元组 (s, i)
        //  - 需要增加, 删除, 维护最小值
        //  - 维护删除之后的左侧下标以及右侧下标
        TreeSet<Pair> pairs = new TreeSet<>((a, b) -> a.s != b.s ? Long.compare(a.s, b.s) : Integer.compare(a.i, b.i));

        // 首先计算所有递减的相邻对的数量, 我们要达成的是非递减, 也就是让递减的相邻对数量变成 0
        int desc = 0, n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) desc++;
            pairs.add(new Pair(nums[i] + nums[i - 1], i - 1));
        }

        // 保存所有还可以使用的下标, 用于查找噫
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < n; i++) available.add(i);

        // 保存累加之后的数据
        long[] acc = new long[n];
        for (int i = 0; i < n; i++) acc[i] = nums[i];

        // 开始模拟整个流程
        int ans = 0;
        while (desc > 0) {
            ans++;

            // 找到相邻元素和最小的那一对
            var curr = pairs.pollFirst();

            // 当前的需要操作的 pair 是 (curr.i, next) 这个相邻对
            int next = available.higher(curr.i);
            // 在删除之前如果是非递减的, 那么我们的操作就会导致非递减的相邻对少 1
            if (acc[curr.i] > acc[next]) desc--;

            // 此时我们删除了 next, 并将数据转移到 curr.i 中, 那么会影响 curr.i 前面的数
            Integer pre = available.lower(curr.i);
            if (pre != null) {
                // 在删除之前是非递减状态, 我们的操作使得这个相邻对没了
                if (acc[pre] > acc[curr.i]) desc--;
                // 新出现的相邻对是 (pre, curr), 检查这个相邻对是否是非递减的
                if (acc[pre] > curr.s) desc++;

                pairs.remove(new Pair(acc[pre] + acc[curr.i], pre));
                pairs.add(new Pair(acc[pre] + curr.s, pre));
            }

            // 删除 (curr.i, next) 之后, 还会影响 next 后面的数
            Integer indirectNext = available.higher(next);
            if (indirectNext != null) {
                if (acc[next] > acc[indirectNext]) desc--;
                if (curr.s > acc[indirectNext]) desc++;

                pairs.remove(new Pair(acc[next] + acc[indirectNext], next));
                pairs.add(new Pair(curr.s + acc[indirectNext], curr.i));
            }

            // 此时 next 已经删除, 同时将值加到 curr.i 里
            available.remove(next);
            acc[curr.i] = curr.s;
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
