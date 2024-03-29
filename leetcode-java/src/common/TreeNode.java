package common;

import java.util.ArrayDeque;
import java.util.Queue;

public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode next;

    public TreeNode() {}
    public TreeNode(int val) { this.val = val; }
    public TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; }
    public TreeNode(int val, TreeNode left, TreeNode right, TreeNode next) { this.val = val; this.left = left; this.right = right; this.next = next; }

    public String toString() { return String.format("[val=%d, left=%s, right=%s]", val, left, right); }

    public static TreeNode build(Integer ...values) {
        if (values.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> nodes = new ArrayDeque<>();
        nodes.add(root);

        for (int i = 1; i < values.length; i += 2) {
            TreeNode node = nodes.remove();
            if (values[i] != null) {
                node.left = new TreeNode(values[i]);
                nodes.add(node.left);
            }
            if (i + 1 < values.length && values[i + 1] != null) {
                node.right = new TreeNode(values[i + 1]);
                nodes.add(node.right);
            }
        }

        return root;
    }

    public static TreeNode build(String input) {
        if (input.charAt(0) == '[') {
            input = input.substring(1);
        }
        if (input.charAt(input.length() - 1) == ']') {
            input = input.substring(0, input.length() - 1);
        }

        String[] ss = input.split(",");
        Integer[] ns = new Integer[ss.length];
        for (int i = 0; i < ss.length; i++) {
            if (!ss[i].equals("null")) {
                ns[i] = Integer.valueOf(ss[i].trim(), 10);
            }
        }

        return build(ns);
    }

}
