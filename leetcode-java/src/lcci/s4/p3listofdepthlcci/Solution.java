package lcci.s4.p3listofdepthlcci;

import common.ListNode;
import common.PrettyPrinter;
import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 面试题 04.03. 特定深度节点链表
 *
 * https://leetcode.cn/problems/list-of-depth-lcci/
 *
 * 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。
 * 返回一个包含所有深度的链表的数组。
 */

public class Solution {

    public ListNode[] listOfDepth(TreeNode tree) {
        if (tree == null) return new ListNode[0];

        List<ListNode> ans = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        for (queue.add(tree); !queue.isEmpty(); ) {
            ListNode tail = null;
            for (int i = 0, n = queue.size(); i < n; i++) {
                TreeNode curr = queue.remove();
                ListNode head = new ListNode(curr.val);
                head.next = tail; tail = head;

                if (curr.right != null) queue.add(curr.right);
                if (curr.left != null) queue.add(curr.left);
            }
            ans.add(tail);
        }
        return ans.toArray(new ListNode[0]);
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().listOfDepth(TreeNode.build(1,2,3,4,5,null,7,8)));
    }

}
