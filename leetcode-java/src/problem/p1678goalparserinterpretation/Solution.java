package problem.p1678goalparserinterpretation;

/**
 * 1678. Goal Parser Interpretation
 *
 * https://leetcode-cn.com/problems/goal-parser-interpretation/
 *
 * You own a Goal Parser that can interpret a string command.
 *
 * The command consists of an alphabet of "G", "()" and/or "(al)" in some order.
 *
 * The Goal Parser will interpret "G" as the string "G", "()" as the string "o", and "(al)" as the string "al".
 *
 * The interpreted strings are then concatenated in the original order.
 *
 * Given the string command, return the Goal Parser's interpretation of command.
 */

public class Solution {

    public String interpret(String command) {
        boolean al = false;
        StringBuilder sb = new StringBuilder();
        for (var c : command.toCharArray()) {
            switch (c) {
                case 'G': sb.append(c); break;
                case '(': al = false; break;
                case 'a':
                case 'l':
                    al = true;
                    sb.append(c);
                    break;
                case ')': if (!al) sb.append('o'); break;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().interpret("G()(al)").equals("Goal");
        assert new Solution().interpret("G()()()()(al)").equals("Gooooal");
        assert new Solution().interpret("(al)G(al)()()G").equals("alGalooG");
    }

}
