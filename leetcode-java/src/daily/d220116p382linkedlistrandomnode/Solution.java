package daily.d220116p382linkedlistrandomnode;

import common.ListNode;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 382. Linked List Random Node
 *
 * https://leetcode-cn.com/problems/linked-list-random-node/
 *
 * Given a singly linked list, return a random node's value from the linked list.
 * Each node must have the same probability of being chosen.
 *
 * Implement the Solution class:
 *
 * Solution(ListNode head) Initializes the object with the integer array nums.
 * int getRandom() Chooses a node randomly from the list and returns its value.
 * All the nodes of the list should be equally likely to be choosen.
 */

public class Solution {

    private final ListNode list;

    public Solution(ListNode head) {
        list = head;
    }

    public int getRandom() {
        int i = 1, ans = 0;
        for (var curr = list; curr != null; curr = curr.next) {
            if (ThreadLocalRandom.current().nextInt(i++) == 0) {
                ans = curr.val;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution(ListNode.build(1, 2, 3));
        System.out.println(solution.getRandom());
        System.out.println(solution.getRandom());
        System.out.println(solution.getRandom());
        System.out.println(solution.getRandom());
        System.out.println(solution.getRandom());
    }

}
