package problem.p1670designfrontmiddlebackqueue;

import common.PrettyPrinter;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1670. Design Front Middle Back Queue
 *
 * https://leetcode.cn/problems/design-front-middle-back-queue/
 *
 * Design a queue that supports push and pop operations in the front, middle, and back.
 *
 * Implement the FrontMiddleBack class:
 *
 * FrontMiddleBack() Initializes the queue.
 * void pushFront(int val) Adds val to the front of the queue.
 * void pushMiddle(int val) Adds val to the middle of the queue.
 * void pushBack(int val) Adds val to the back of the queue.
 * int popFront() Removes the front element of the queue and returns it. If the queue is empty, return -1.
 * int popMiddle() Removes the middle element of the queue and returns it. If the queue is empty, return -1.
 * int popBack() Removes the back element of the queue and returns it. If the queue is empty, return -1.
 * Notice that when there are two middle position choices, the operation is performed on the frontmost
 * middle position choice. For example:
 *
 * Pushing 6 into the middle of [1, 2, 3, 4, 5] results in [1, 2, 6, 3, 4, 5].
 * Popping the middle from [1, 2, 3, 4, 5, 6] returns 3 and results in [1, 2, 4, 5, 6].
 */

public class Solution {

    private static class FrontMiddleBackQueue {
        // front ... back
        private final Deque<Integer> front = new ArrayDeque<>();
        private final Deque<Integer> back = new ArrayDeque<>();
        public FrontMiddleBackQueue() {}
        private void adjust() {
            // keep front.size == back.size || front.size == back.size+1
            while (!(front.size() == back.size() || front.size() == back.size() + 1)) {
                if (back.size() > front.size()) front.addLast(back.removeFirst());
                else if (front.size() > back.size() + 1) back.addFirst(front.removeLast());
            }

            PrettyPrinter.println(front);
            PrettyPrinter.println(back);
        }

        public void pushFront(int val) { front.addFirst(val); adjust(); }
        public void pushBack(int val) { back.addLast(val); adjust(); }
        public void pushMiddle(int val) {
            if (front.size() > back.size()) {
                back.addFirst(front.removeLast());
            }
            front.addLast(val);
            adjust();
        }

        public int popFront() {
            if (front.isEmpty()) return -1;
            int val = front.removeFirst();
            adjust();
            return val;
        }

        public int popMiddle() {
            if (front.isEmpty()) return -1;
            int val = front.removeLast();
            adjust();
            return val;
        }

        public int popBack() {
            if (!back.isEmpty()) {
                int val = back.removeLast();
                adjust();
                return val;
            } else if (!front.isEmpty()) {
                int val = front.removeLast();
                adjust();
                return val;
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        FrontMiddleBackQueue q = new FrontMiddleBackQueue();
        q.pushFront(1);
        q.pushBack(2);
        q.pushMiddle(3);
        q.pushMiddle(4);
        assert q.popFront() == 1;
        assert q.popMiddle() == 3;
        assert q.popMiddle() == 4;
        assert q.popBack() == 2;
        assert q.popFront() == -1;
    }

}
