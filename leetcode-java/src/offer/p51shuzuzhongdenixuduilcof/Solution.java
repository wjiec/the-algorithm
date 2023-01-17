package offer.p51shuzuzhongdenixuduilcof;

import java.util.Arrays;

/**
 * 剑指 Offer 51. 数组中的逆序对
 *
 * https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/?favorite=xb9nqhhg
 *
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 *
 * 输入一个数组，求出这个数组中的逆序对的总数。
 */

public class Solution {

    private int[] tree = null;

    public int reversePairs(int[] nums) {
        int[] sorted = new int[nums.length];
        System.arraycopy(nums, 0, sorted, 0, nums.length);
        Arrays.sort(sorted);

        for (int i = 0; i < nums.length; i++) {
            nums[i] = Arrays.binarySearch(sorted, nums[i]) + 1;
        }

        int ans = 0; tree = new int[nums.length + 1];
        for (int i = nums.length - 1; i >= 0; i--) {
            ans += query(nums[i] - 1); update(nums[i]);
        }
        return ans;
    }

    private int query(int x) {
        int ans = 0;
        while (x != 0) {
            ans += tree[x];
            x -= lowbit(x);
        }
        return ans;
    }

    private void update(int x) {
        while (x < tree.length) {
            tree[x]++;
            x += lowbit(x);
        }
    }

    private static int lowbit(int x) { return x & (-x); }

    public static void main(String[] args) {
        assert new Solution().reversePairs(new int[]{7,5,6,4}) == 5;
    }

}
