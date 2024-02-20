package weekly.bw124.A;

/**
 * 3038. Maximum Number of Operations With the Same Score I
 *
 * https://leetcode.cn/contest/biweekly-contest-124/problems/maximum-number-of-operations-with-the-same-score-i/
 *
 * Given an array of integers called nums, you can perform the following
 * operation while nums contains at least 2 elements:
 *
 * Choose the first two elements of nums and delete them.
 *
 * The score of the operation is the sum of the deleted elements.
 *
 * Your task is to find the maximum number of operations that can be
 * performed, such that all operations have the same score.
 *
 * Return the maximum number of operations possible that satisfy the condition mentioned above.
 */

public class Solution {

    public int maxOperations(int[] nums) {
        int ans = 1, target = nums[0] + nums[1];
        for (int i = 2; i + 1 < nums.length; i += 2) {
            if (nums[i] + nums[i + 1] == target) ans++;
            else break;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
