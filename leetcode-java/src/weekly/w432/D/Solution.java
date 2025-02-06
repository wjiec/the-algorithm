package weekly.w432.D;

import java.util.*;

/**
 * 3420. Count Non-Decreasing Subarrays After K Operations
 *
 * https://leetcode.cn/contest/weekly-contest-432/problems/count-non-decreasing-subarrays-after-k-operations/
 *
 * You are given an array nums of n integers and an integer k.
 *
 * For each subarray of nums, you can apply up to k operations on it.
 *
 * In each operation, you increment any element of the subarray by 1.
 *
 * Note that each subarray is considered independently, meaning changes
 * made to one subarray do not persist to another.
 *
 * Return the number of subarrays that you can make non-decreasing after performing at most k operations.
 *
 * An array is said to be non-decreasing if each element is greater than
 * or equal to its previous element, if it exists.
 */

/** @noinspection unchecked */
public class Solution {

    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        // 子数组我们我们使用滑动窗口来解决, 枚举每一个窗口右侧端点 r, 找到第一个
        // 满足条件的左侧位置, 此时在区间内的都是满足条件的子数组, 共有 r - l + 1 个

        // 在右侧新的数字 in 加入窗口之后, 需要变为 max(in, window_max), 如果此时
        // 不满足要求, 则需要移出左侧的数 out, 此时我们需要减去的操作次数则由 out 两侧
        // 大于等于 out 的数来决定

        // 首先通过单调栈找到大于等于每个位置的左右位置
        int[] gteR = new int[n]; Arrays.fill(gteR, n);
        // 对于大于当前数的左侧位置, 我们使用树的方式来存储, 方便后续找到与 out 数同一层级的其他节点
        //  - g 中保存的是 {k: [a, b, c]} 表示大于 a, b, c 的第一个左侧位置是 k
        List<Integer>[] g = new List[n]; Arrays.setAll(g, i -> new ArrayList<>());

        // 使用递增的单调栈(递减)来找到左右两侧大于等于当前数的位置
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!dq.isEmpty() && nums[i] >= nums[dq.peek()]) {
                // 当前数大于等于之前入栈的数, 所以我们找到了一个大于等于之前入栈数的位置
                gteR[dq.pop()] = i;
            }

            // 如果栈非空, 那么此时栈顶的位置就是大于当前位置的第一个左侧位置
            if (!dq.isEmpty()) g[dq.peek()].add(i);
            dq.push(i); // 在找到大于当前数的左侧位置后再将当前数加入到单调栈中
        }

        long ans = 0;
        // 在窗口范围内单调递减
        Deque<Integer> win = new ArrayDeque<>();
        for (int wl = 0, wr = 0, op = 0; wr < n; wr++) {
            // 找到栈内所有大于等于当前数的位置并将其弹出
            while (!win.isEmpty() && nums[wr] >= nums[win.peekLast()]) win.removeLast();
            win.addLast(wr);

            // 右侧有一个数字 in 进入到窗口中, 计算需要增加的操作数
            // 当前窗口内的最大值就是单调栈的首个元素
            op += nums[win.getFirst()] - nums[wr];

            // 如果此时窗口中的子数组使用的操作数超过了限制的值, 则需要缩小窗口
            for (; op > k; wl++) {
                int out = nums[wl]; // 移出窗口的数

                // 降低比移出数小的所有的下级组合
                for (var next : g[wl]) {
                    if (next > wr) break;
                    op -= (out - nums[next]) * (Math.min(gteR[next], wr + 1) - next);
                }

                if (!win.isEmpty() && win.getFirst() <= wl) win.removeFirst();
            }

            ans += wr - wl + 1;
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countNonDecreasingSubarrays(new int[]{6,3,1,2,4,4}, 7) == 17;
        assert new Solution().countNonDecreasingSubarrays(new int[]{6,3,1,3,6}, 4) == 12;
    }

}
