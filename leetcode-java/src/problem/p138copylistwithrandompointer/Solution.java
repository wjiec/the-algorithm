package problem.p138copylistwithrandompointer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 138. Copy List with Random Pointer
 *
 * https://leetcode-cn.com/problems/copy-list-with-random-pointer/
 *
 * A linked list of length n is given such that each node contains an additional random pointer,
 * which could point to any node in the list, or null.
 *
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes,
 * where each new node has its value set to the value of its corresponding original node.
 *
 * Both the next and random pointer of the new nodes should point to new nodes in the
 * copied list such that the pointers in the original list and copied list represent the same list state.
 * None of the pointers in the new list should point to nodes in the original list.
 *
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y,
 * then for the corresponding two nodes x and y in the copied list, x.random --> y.
 *
 * Return the head of the copied linked list.
 *
 * The linked list is represented in the input/output as a list of n nodes.
 * Each node is represented as a pair of [val, random_index] where:
 *
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) that the random pointer points to,
 * or null if it does not point to any node.
 * Your code will only be given the head of the original linked list.
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
