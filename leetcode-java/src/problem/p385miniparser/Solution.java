package problem.p385miniparser;

import common.NestedInteger;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 385. Mini Parser
 *
 * https://leetcode-cn.com/problems/mini-parser/
 *
 * Given a string s represents the serialization of a nested list,
 * implement a parser to deserialize it and return the deserialized NestedInteger.
 *
 * Each element is either an integer or a list whose elements may also be integers or other lists.
 */

public class Solution {

    public NestedInteger deserialize(String s) {
        NestedInteger ans = null;
        Deque<NestedInteger> stack = new ArrayDeque<>();
        for (int i = 0, base = 1, n = s.length(); i < n; i++) {
            char curr = s.charAt(i);
            if (curr == '-') base = -1;
            else if (curr == ']') {
                while (!stack.isEmpty() && stack.peek().isInteger()) stack.pop();
                ans = stack.pop();
                base = 1;
            }
            else if (curr == ',' && !stack.isEmpty() && stack.peek().isInteger()) {
                stack.pop();
                base = 1;
            }
            else if (curr == '[') {
                NestedInteger list = new NestedInteger();
                if (!stack.isEmpty()) stack.peek().add(list);
                stack.push(list);
            } else {
                NestedInteger top = stack.peek();
                if (Character.isDigit(curr)) {
                    if (top != null && top.isInteger()) {
                        top.setInteger(top.getInteger() * 10 + (curr - '0') * base);
                    } else {
                        NestedInteger val = new NestedInteger((curr - '0') * base);
                        if (top != null) top.add(val);
                        stack.push(val);
                    }
                }
            }
        }
        return stack.isEmpty() ? ans : stack.pop();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().deserialize("-234989"));
        System.out.println(new Solution().deserialize("[-234989,234989,-234989,[123]]"));

        System.out.println(new Solution().deserialize("324"));
        System.out.println(new Solution().deserialize("[123,[456,[789]]]"));
        System.out.println(new Solution().deserialize("[1,[-2,[3,[-4,[]]]]]"));
    }

}
