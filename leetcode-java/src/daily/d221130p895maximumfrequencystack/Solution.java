package daily.d221130p895maximumfrequencystack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 895. Maximum Frequency Stack
 *
 * https://leetcode.cn/problems/maximum-frequency-stack/
 *
 * Design a stack-like data structure to push elements to the stack and
 * pop the most frequent element from the stack.
 *
 * Implement the FreqStack class:
 *
 * FreqStack() constructs an empty frequency stack.
 * void push(int val) pushes an integer val onto the top of the stack.
 * int pop() removes and returns the most frequent element in the stack.
 *
 * If there is a tie for the most frequent element, the element closest to the
 * stack's top is removed and returned.
 */

public class Solution {

    private static class FreqStack {
        private int mostFreq = 0;
        private final Map<Integer, Integer> freq = new HashMap<>();
        private final Map<Integer, Deque<Integer>> group = new HashMap<>();
        public FreqStack() {}

        public void push(int val) {
            int currFreq = freq.merge(val, 1, Integer::sum);
            group.computeIfAbsent(currFreq, v -> new ArrayDeque<>()).push(val);
            mostFreq = Math.max(mostFreq, currFreq);
        }

        public int pop() {
            int val = group.get(mostFreq).pop();
            freq.merge(val, -1, Integer::sum);
            if (group.get(mostFreq).isEmpty()) mostFreq--;
            return val;
        }
    }


    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(4);
        freqStack.push(5);
        assert freqStack.pop() == 5;
        assert freqStack.pop() == 7;
        assert freqStack.pop() == 5;
        assert freqStack.pop() == 4;
    }

}
