package weekly.w308.B;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    public String removeStars(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (var c : s.toCharArray()) {
            if (c == '*') stack.pop();
            else stack.push(c);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.append(stack.pop());
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
    }

}
