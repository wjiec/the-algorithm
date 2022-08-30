package problem.p1689partitioningintominimumnumberofdecibinarynumbers;

/**
 * 1689. Partitioning Into Minimum Number Of Deci-Binary Numbers
 *
 * https://leetcode.cn/problems/partitioning-into-minimum-number-of-deci-binary-numbers/
 *
 * A decimal number is called deci-binary if each of its digits is either 0 or 1 without any leading zeros.
 * For example, 101 and 1100 are deci-binary, while 112 and 3001 are not.
 *
 * Given a string n that represents a positive decimal integer, return the minimum number of positive
 * deci-binary numbers needed so that they sum up to n.
 */

public class Solution {

    public int minPartitions(String n) {
        int ans = 0;
        for (var c : n.toCharArray()) {
            ans = Math.max(ans, c - '0');
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minPartitions("32") == 3;
        assert new Solution().minPartitions("82734") == 8;
        assert new Solution().minPartitions("27346209830709182346") == 9;
    }

}
