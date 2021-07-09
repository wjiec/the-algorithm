package offer.p35fuzalianbiaodefuzhilcof;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 35. 复杂链表的复制
 *
 * https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof/
 *
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，
 * 还有一个 random 指针指向链表中的任意节点或者 null。
 */

public class Solution {

    private static class Node {
        private final int val;
        private Node next, random;
        public Node(int v) { val = v; }
    }

    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Map<Node, Node> randMap = new HashMap<>();
        Map<Node, Integer> idxMap = new HashMap<>();
        ArrayList<Node> nodeList = new ArrayList<>();

        Node root = new Node(-1); int idx = 0;
        for (Node curr = head, node = root; curr != null; curr = curr.next) {
            node.next = new Node(curr.val);

            node = node.next;
            nodeList.add(node);

            idxMap.put(curr, idx++);
            if (curr.random != null) {
                randMap.put(curr, curr.random);
            }
        }

        for (Node curr = head, node = root.next; curr != null; curr = curr.next, node = node.next) {
            if (curr.random != null) {
                node.random = nodeList.get(idxMap.get(randMap.get(curr)));
            }
        }

        return root.next;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().copyRandomList(null));
    }

}
