package problem.p679a24game;

import java.util.ArrayList;
import java.util.List;

/**
 * 679. 24 Game
 *
 * https://leetcode.cn/problems/24-game/
 *
 * You are given an integer array cards of length 4. You have four cards, each
 * containing a number in the range [1, 9]. You should arrange the numbers on
 * these cards in a mathematical expression using the operators ['+', '-', '*', '/'] and
 * the parentheses '(' and ')' to get the value 24.
 *
 * You are restricted with the following rules:
 *
 * The division operator '/' represents real division, not integer division.
 * For example, 4 / (1 - 2 / 3) = 4 / (1 / 3) = 12.
 * Every operation done is between two numbers. In particular, we cannot use '-' as a unary operator.
 * For example, if cards = [1, 1, 1, 1], the expression "-1 - 1 - 1 - 1" is not allowed.
 * You cannot concatenate numbers together
 * For example, if cards = [1, 2, 1, 2], the expression "12 + 12" is not valid.
 *
 * Return true if you can get such expression that evaluates to 24, and false otherwise.
 */

public class Solution {

    private record Fraction(int top, int bot) {}

    public boolean judgePoint24(int[] cards) {
        return judge(List.of(
            new Fraction(cards[0], 1),
            new Fraction(cards[1], 1),
            new Fraction(cards[2], 1),
            new Fraction(cards[3], 1)
        ));
    }

    public boolean judge(List<Fraction> cards) {
        if (cards.size() == 1) {
            return cards.get(0).top != 0 && cards.get(0).bot != 0 &&
                (cards.get(0).top % cards.get(0).bot) == 0 &&
                (cards.get(0).top / cards.get(0).bot) == 24;
        }

        for (int i = 0; i < cards.size(); i++) {
            for (int j = 0; j < cards.size(); j++) {
                if (i == j) continue;
                for (var op : "+-*/".toCharArray()) {
                    Fraction curr = solve(cards.get(i), cards.get(j), op);
                    List<Fraction> args = new ArrayList<>();
                    for (int k = 0; k < cards.size(); k++) {
                        if (k != i && k != j) args.add(cards.get(k));
                        else if (k == i) args.add(curr);
                    }
                    if (judge(args)) return true;
                }
            }
        }
        return false;
    }

    private Fraction solve(Fraction a, Fraction b, char op) {
        Fraction ans = null;
        switch (op) {
            case '+' -> {
                if (a.top == 0) return b;
                if (b.top == 0) return a;

                int m = lcm(a.bot, b.bot);
                ans = new Fraction(a.top * m / a.bot + b.top * m / b.bot, m);
            }
            case '-' -> {
                if (a.top == 0) return new Fraction(-b.top, b.bot);
                if (b.top == 0) return a;

                int m = lcm(a.bot, b.bot);
                ans = new Fraction(a.top * m / a.bot - b.top * m / b.bot, m);
            }
            case '*' -> {
                if (a.top == 0 || b.top == 0) return new Fraction(0, 0);
                ans = new Fraction(a.top * b.top, a.bot * b.bot);
            }
            case '/' -> {
                ans = new Fraction(a.top * b.bot, a.bot * b.top);
            }
        }

        assert ans != null;
        if (ans.top == 0) return new Fraction(0, 0);
        return ans;
    }

    // 最大公约数
    public static int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }
    // 最小公倍数
    public static int lcm(int a, int b) { return a / gcd(a, b) * b; }

    public static void main(String[] args) {
        assert new Solution().judgePoint24(new int[]{3, 9, 7, 7});

        assert new Solution().judgePoint24(new int[]{4, 1, 8, 7});
        assert !new Solution().judgePoint24(new int[]{1, 2, 1, 2});
    }

}
