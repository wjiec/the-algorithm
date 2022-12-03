package lcci.s16.p26calculatorlcci;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 面试题 16.26. 计算器
 *
 * https://leetcode.cn/problems/calculator-lcci/
 *
 * 给定一个包含正整数、加(+)、减(-)、乘(*)、除(/)的算数表达式(括号除外)，计算其结果。
 *
 * 表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int calculate(String s) {
        s += '#';
        Deque<Integer> stack = new ArrayDeque<>();
        char prevOperator = '+'; int number = 0;
        for (var c : s.toCharArray()) {
            if (c == ' ') continue;

            if (!Character.isDigit(c)) {
                switch (prevOperator) {
                    case '+' -> stack.push(number);
                    case '-' -> stack.push(-number);
                    case '*' -> stack.push(stack.pop() * number);
                    case '/' -> stack.push(stack.pop() / number);
                }

                prevOperator = c; number = 0;
            } else number = number * 10 + (c - '0');
        }

        while (!stack.isEmpty()) number += stack.pop();
        return number;
    }

    public static void main(String[] args) {
        assert new Solution().calculate("3+2*2") == 7;
        assert new Solution().calculate(" 3/2 ") == 1;
        assert new Solution().calculate(" 3+5 / 2 ") == 5;
    }

}
