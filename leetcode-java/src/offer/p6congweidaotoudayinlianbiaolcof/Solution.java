package offer.p6congweidaotoudayinlianbiaolcof;

import common.ListNode;

import java.util.Arrays;
import java.util.Stack;

/**
 * 剑指 Offer 06. 从尾到头打印链表
 *
 * https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
 *
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 */

public class Solution {

    public int[] reversePrintByStack(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        for (var curr = head; curr != null; curr = curr.next) stack.add(curr);

        int[] ans = new int[stack.size()];
        for (int i = 0, l = stack.size(); i < l; i++) {
            ans[i] = stack.pop().val;
        }
        return ans;
    }

    public int[] reversePrint(ListNode head) {
        int size = 0;
        for (var curr = head; curr != null; curr = curr.next) size++;

        int[] ans = new int[size];
        for (var curr = head; curr != null; curr = curr.next) ans[--size] = curr.val;
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().reversePrint(ListNode.build(1, 3, 2))));
        System.out.println(Arrays.toString(new Solution().reversePrintByStack(ListNode.build(1, 3, 2))));
    }

}
