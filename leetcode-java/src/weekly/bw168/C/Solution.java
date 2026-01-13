package weekly.bw168.C;

/**
 * Q3. Minimum Operations to Transform Array
 *
 * https://leetcode.cn/contest/biweekly-contest-168/problems/minimum-operations-to-transform-array/
 *
 * You are given two integer arrays nums1 of length n and nums2 of length n + 1.
 *
 * You want to transform nums1 into nums2 using the minimum number of operations.
 *
 * You may perform the following operations any number of times, each time choosing an index i:
 *
 * Increase nums1[i] by 1.
 * Decrease nums1[i] by 1.
 * Append nums1[i] to the end of the array.
 *
 * Return the minimum number of operations required to transform nums1 into nums2.
 */

public class Solution {

    public long minOperations(int[] nums1, int[] nums2) {
        long ans = 0; int n = nums1.length, tail = nums2[n];
        // 我们需要找到一个数并将其追加到末尾使其成为 tail = nums2[-1]
        //  - 如果存在任意的 i 在完成 nums1[i] -> nums2[i] 时包含 tail, 则只需要附带一个追加操作即可
        //  - 否则, 我们需要先完成追加再执行其他操作
        //      - 我们在 nums2[0..n-1] 中找到与 tail 最接近的数字的位置 j, 在 nums1[j] 中的数字变成 nums2[j] 时, 执行一次追加并再进行数字操作
        //          - 也就是 |nums2[j] - tail| + 1
        //      - 再 nums1[0..n-1] 中找到与 tail 最接近的数字 k, 首先执行追加 nums1[k] 然后在进行数字操作
        //          - 也就是 |nums1[k] - tail| + 1
        //  - 取较小值
        boolean ok = false; int diff1 = Integer.MAX_VALUE, diff2 = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            ok = ok || (tail == nums1[i]) || (tail == nums2[i]);
            diff1 = Math.min(diff1, Math.abs(tail - nums1[i]));
            diff2 = Math.min(diff2, Math.abs(tail - nums2[i]));
            // 检查我们是否可以在数字操作过程中顺带完成
            if (nums1[i] < nums2[i]) {
                ans += nums2[i] - nums1[i];
                ok = ok || (nums1[i] <= tail && tail <= nums2[i]);
            } else if (nums1[i] > nums2[i]) {
                ans += nums1[i] - nums2[i];
                ok = ok || (nums2[i] <= tail && tail <= nums1[i]);
            }
        }
        return ok ? (ans + 1) : (ans + Math.min(diff1, diff2) + 1);
    }

    public static void main(String[] args) {
    }

}
