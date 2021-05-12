package problem.p202happynumber;

import java.util.HashSet;
import java.util.Set;

/**
 * 202. Happy Number
 *
 * https://leetcode-cn.com/problems/happy-number/
 *
 * Write an algorithm to determine if a number n is happy.
 *
 * A happy number is a number defined by the following process:
 *
 * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay),
 * or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 */

public class Solution {

    public boolean isHappy(int n) {
        Set<Integer> ns = new HashSet<>();
        for (int sum = 0; n != 1; sum = 0) {
            while (n != 0) {
                sum += (n % 10) * (n % 10);
                n /= 10;
            }
            n = sum;
            if (ns.contains(n)) {
                return false;
            } else {
                ns.add(n);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().isHappy(19);
        assert !new Solution().isHappy(2);
    }

}
