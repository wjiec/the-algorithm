package offer.p36erchasousuoshuyushuangxianglianbiaolcof;

/**
 * 剑指 Offer 36. 二叉搜索树与双向链表
 *
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
 *
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 */

public class Solution {

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node(int val) { this.val = val; }
    }

    private Node head, prev;

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;

        dfs(root);
        head.left = prev;
        prev.right = head;

        return head;
    }

    void dfs(Node curr) {
        if (curr != null) {
            dfs(curr.left);

            if (head == null) head = curr;
            else prev.right = curr;

            curr.left = prev;
            prev = curr;

            dfs(curr.right);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().treeToDoublyList(null));

        Node root = new Node(4);
        root.left = new Node(2); root.right = new Node(5);
        root.left.left = new Node(1); root.left.right = new Node(3);
        System.out.println(new Solution().treeToDoublyList(root));
    }

}
