package weekly.w469.A;

import common.PrettyPrinter;

import java.util.ArrayList;
import java.util.List;

/**
 * Q1. Compute Decimal Representation
 *
 * https://leetcode.cn/contest/weekly-contest-469/problems/compute-decimal-representation/
 *
 * You are given a positive integer n.
 *
 * A positive integer is a base-10 component if it is the product of a single digit
 * from 1 to 9 and a non-negative power of 10. For example, 500, 30, and 7 are base-10 components,
 * while 537, 102, and 11 are not.
 *
 * Express n as a sum of only base-10 components, using the fewest base-10 components possible.
 *
 * Return an array containing these base-10 components in descending order.
 */

public class Solution {

    public int[] decimalRepresentation(int n) {
        List<Integer> list = new ArrayList<>();
        for (int base = 1; n != 0; n /= 10, base *= 10) {
            if (n % 10 != 0) list.add((n % 10) * base);
        }

        int[] ans = new int[list.size()];
        for (int i = 0, j = list.size() - 1; j >= 0; i++, j--) ans[i] = list.get(j);
        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().decimalRepresentation(537));
        PrettyPrinter.println(new Solution().decimalRepresentation(102));
    }

}
