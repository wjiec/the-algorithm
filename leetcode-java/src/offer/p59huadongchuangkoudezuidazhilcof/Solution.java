package offer.p59huadongchuangkoudezuidazhilcof;

import common.Checker;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

/**
 * 剑指 Offer 59 - I. 滑动窗口的最大值
 *
 * https://leetcode.cn/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof
 *
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 */

public class Solution {

    public int[] maxSlidingWindow(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < k - 1; i++) map.merge(nums[i], 1, Solution::sum);

        int[] ans = new int[nums.length - k + 1];
        for (int i = k - 1; i < nums.length; i++) {
            map.merge(nums[i], 1, Solution::sum);
            ans[i - k + 1] = map.lastKey();
            map.merge(nums[i - k + 1], -1, Solution::sum);
        }
        return ans;
    }

    private static class MonoStack {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (k == 1) return nums;
            // 单调递减的索引栈, 最大的在前面
            Deque<Integer> stack = new ArrayDeque<>();
            for (int i = 0; i < k; i++) {
                while (!stack.isEmpty() && nums[stack.peekLast()] <= nums[i]) {
                    stack.removeLast();
                }
                stack.addLast(i);
            }

            int[] ans = new int[nums.length - k + 1];
            assert !stack.isEmpty();
            ans[0] = nums[stack.peekFirst()];
            for (int i = k; i < nums.length; i++) {
                while (!stack.isEmpty() && stack.peekFirst() <= i - k) {
                    stack.removeFirst();
                }

                while (!stack.isEmpty() && nums[stack.peekLast()] <= nums[i]) {
                    stack.removeLast();
                }
                stack.addLast(i);

                assert !stack.isEmpty();
                ans[i - k + 1] = nums[stack.peekFirst()];
            }

            return ans;
        }
    }

    private static Integer sum(Integer a, Integer b) { return a + b == 0 ? null : a + b; }

    public static void main(String[] args) {
        assert Checker.check(new Solution().maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3), new int[]{3,3,5,5,6,7});

        assert Checker.check(new MonoStack().maxSlidingWindow(new int[]{1,-1}, 1), new int[]{1,-1});
        assert Checker.check(new MonoStack().maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3), new int[]{3,3,5,5,6,7});
    }

}
