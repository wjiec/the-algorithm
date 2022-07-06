package daily.d220706p736parselispexpression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 736. Parse Lisp Expression
 *
 * https://leetcode.cn/problems/parse-lisp-expression/
 *
 * You are given a string expression representing a Lisp-like expression to return the integer value of.
 *
 * The syntax for these expressions is given as follows.
 *
 * An expression is either an integer, let expression, add expression, mult expression, or an assigned variable.
 * Expressions always evaluate to a single integer.
 * (An integer could be positive or negative.)
 * A let expression takes the form "(let v1 e1 v2 e2 ... vn en expr)", where let is always the string "let", then
 * there are one or more pairs of alternating variables and expressions, meaning that the first variable v1 is
 * assigned the value of the expression e1, the second variable v2 is assigned the value of the expression e2, and
 * so on sequentially; and then the value of this let expression is the value of the expression expr.
 * An add expression takes the form "(add e1 e2)" where add is always the string "add", there are always two
 * expressions e1, e2 and the result is the addition of the evaluation of e1 and the evaluation of e2.
 * A mult expression takes the form "(mult e1 e2)" where mult is always the string "mult", there are always two
 * expressions e1, e2 and the result is the multiplication of the evaluation of e1 and the evaluation of e2.
 * For this question, we will use a smaller subset of variable names. A variable starts with a lowercase letter, then
 * zero or more lowercase letters or digits. Additionally, for your convenience, the names
 * "add", "let", and "mult" are protected and will never be used as variable names.
 * Finally, there is the concept of scope. When an expression of a variable name is evaluated, within the context
 * of that evaluation, the innermost scope (in terms of parentheses) is checked first for the value of that
 * variable, and then outer scopes are checked sequentially. It is guaranteed that every expression is legal.
 *
 * Please see the examples for more details on the scope.
 */

@SuppressWarnings("ConstantConditions")
public class Solution {

    private static class Context {
        private final Context parent;
        private final Map<String, Integer> map = new HashMap<>();
        public Context() { parent = null; }
        private Context(Context parent) { this.parent = parent; }
        public Context with() { return new Context(this); }
        public void set(String k, Integer v) { map.put(k, v); }
        public Integer get(String k) {
            if (map.containsKey(k)) return map.get(k);
            if (parent != null) return parent.get(k);
            return null;
        }
    }

    private int idx = 0;
    private List<String> tokens;

    public int evaluate(String expression) {
        tokens = getTokens(expression);
        return eval(new Context());
    }

    private int eval(Context ctx) {
        String token = tokens.get(idx++);
        if (isNumber(token.toCharArray())) {
            return Integer.valueOf(token, 10);
        }

        if (token.equals("(")) return eval(ctx.with());

        if (token.equals("add")) {
            int left = eval(ctx.with());
            int right = eval(ctx.with());
            return left + right;
        }

        if (token.equals("mult")) {
            int left = eval(ctx.with());
            int right = eval(ctx.with());
            return left * right;
        }

        if (token.equals("let")) {
            while (idx < tokens.size()) {
                String name = tokens.get(idx++);
                if (name.equals("(")) {
                    return eval(ctx.with());
                }
                if (idx < tokens.size() && tokens.get(idx).equals(")")) {
                    return ctx.get(name);
                }
                int value = eval(ctx.with());
                ctx.set(name, value);
            }
        }

        Integer ans = ctx.get(token);
        return ans == null ? 0 : ans;
    }

    private List<String> getTokens(String expression) {
        List<String> token = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (var c : expression.toCharArray()) {
            if (c == ' ' || c == '(' || c == ')') {
                if (!sb.isEmpty()) token.add(sb.toString());
                if (c == '(' || c == ')') token.add(String.valueOf(c));
                sb = new StringBuilder();
            } else sb.append(c);
        }
        return token;
    }

    private boolean isNumber(char[] chars) {
        if (chars[0] == '-' || Character.isDigit(chars[0])) {
            for (int i = 1; i < chars.length; i++) {
                if (!Character.isDigit(chars[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().evaluate("(let x 2 (add (let x 3 (let x 4 x)) x))") == 6;

        assert new Solution().evaluate("(let x 2 (mult x (let x 3 y 4 (add x y))))") == 14;
        assert new Solution().evaluate("(let x 3 x 2 x)") == 2;
        assert new Solution().evaluate("(let x 1 y 2 x (add x y) (add x y))") == 5;
    }

}
