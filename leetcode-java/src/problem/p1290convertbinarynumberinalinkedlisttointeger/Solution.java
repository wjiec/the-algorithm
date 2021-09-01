package problem.p1290convertbinarynumberinalinkedlisttointeger;

import common.ListNode;

/**
 * 1290. Convert Binary Number in a Linked List to Integer
 *
 * https://leetcode-cn.com/problems/convert-binary-number-in-a-linked-list-to-integer/
 *
 * Given head which is a reference node to a singly-linked list.
 *
 * The value of each node in the linked list is either 0 or 1.
 *
 * The linked list holds the binary representation of a number.
 *
 * Return the decimal value of the number in the linked list.
 */

public class Solution {

    public int getDecimalValue(ListNode head) {
        int ans = 0;
        for (; head != null; head = head.next) {
            ans = (ans << 1) | head.val;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().getDecimalValue(ListNode.build(1,0,1)) == 5;
        assert new Solution().getDecimalValue(ListNode.build(0)) == 0;
        assert new Solution().getDecimalValue(ListNode.build(1)) == 1;
        assert new Solution().getDecimalValue(ListNode.build(1,0,0,1,0,0,1,1,1,0,0,0,0,0,0)) == 18880;
        assert new Solution().getDecimalValue(ListNode.build(0,0)) == 0;
    }

}
