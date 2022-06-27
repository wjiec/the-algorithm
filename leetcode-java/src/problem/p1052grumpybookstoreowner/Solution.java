package problem.p1052grumpybookstoreowner;

/**
 * 1052. Grumpy Bookstore Owner
 *
 * https://leetcode.cn/problems/grumpy-bookstore-owner/
 *
 * There is a bookstore owner that has a store open for n minutes. Every minute, some
 * number of customers enter the store. You are given an integer array customers of
 * length n where customers[i] is the number of the customer that enters the store
 * at the start of the ith minute and all those customers leave after
 * the end of that minute.
 *
 * On some minutes, the bookstore owner is grumpy. You are given a binary array
 * grumpy where grumpy[i] is 1 if the bookstore owner is grumpy during the
 * ith minute, and is 0 otherwise.
 *
 * When the bookstore owner is grumpy, the customers of that minute are not
 * satisfied, otherwise, they are satisfied.
 *
 * The bookstore owner knows a secret technique to keep themselves not
 * grumpy for minutes consecutive minutes, but can only use it once.
 *
 * Return the maximum number of customers that can be satisfied throughout the day.
 */

public class Solution {

    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int ans = 0, n = customers.length;
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                ans += customers[i];
            }
        }

        int saves = 0, curr = 0;
        for (int l = 0, r = 0; r < n; r++) {
            if (grumpy[r] == 1) curr += customers[r];
            if (r - l + 1 > minutes) {
                if (grumpy[l] == 1) {
                    curr -= customers[l];
                }
                l++;
            }
            if (curr > saves) saves = curr;
        }
        return ans + saves;
    }

    public static void main(String[] args) {
        assert new Solution().maxSatisfied(new int[]{1,0,1,2,1,1,7,5}, new int[]{0,1,0,1,0,1,0,1}, 3) == 16;
        assert new Solution().maxSatisfied(new int[]{1}, new int[]{0}, 1) == 1;
    }

}
