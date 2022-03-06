package weekly.w283.p3replacenoncoprimenumbersinarray;

import java.util.LinkedList;
import java.util.List;

/**
 * 6019. Replace Non-Coprime Numbers in Array
 *
 * https://leetcode-cn.com/contest/weekly-contest-283/problems/replace-non-coprime-numbers-in-array/
 *
 * You are given an array of integers nums. Perform the following steps:
 *
 * Find any two adjacent numbers in nums that are non-coprime.
 * If no such numbers are found, stop the process.
 * Otherwise, delete the two numbers and replace them with their LCM (Least Common Multiple).
 * Repeat this process as long as you keep finding two adjacent non-coprime numbers.
 * Return the final modified array. It can be shown that replacing adjacent non-coprime numbers
 * in any arbitrary order will lead to the same result.
 *
 * The test cases are generated such that the values in the final array are less than or equal to 108.
 *
 * Two values x and y are non-coprime if GCD(x, y) > 1 where GCD(x, y) is the Greatest Common Divisor of x and y.
 */

public class Solution {

    public List<Integer> replaceNonCoprimes(int[] nums) {
        LinkedList<Integer> ans = new LinkedList<>();
        ans.addFirst(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];
            while (!ans.isEmpty()) {
                int prev = ans.getLast();
                int val = gcd(curr, prev);
                if (val == 1) break;
                ans.pollLast();
                curr = (int) ((long) curr * prev / val);
            }
            ans.offer(curr);
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return a % b == 0 ? b : gcd(b, a % b);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().replaceNonCoprimes(new int[]{6,4,3,2,7,6,2}));
        System.out.println(new Solution().replaceNonCoprimes(new int[]{2,2,1,1,3,3,3}));
    }

}
