package problem.p722removecomments;

import common.PrettyPrinter;

import java.util.ArrayList;
import java.util.List;

/**
 * 722. Remove Comments
 *
 * https://leetcode.cn/problems/remove-comments/
 *
 * Given a C++ program, remove comments from it. The program source is an array of strings source
 * where source[i] is the ith line of the source code.
 * This represents the result of splitting the original source code string by
 * the newline character '\n'.
 *
 * In C++, there are two types of comments, line comments, and block comments.
 *
 * The string "//" denotes a line comment, which represents that it and the rest of
 * the characters to the right of it in the same line should be ignored.
 * The string "/*" denotes a block comment, which represents that all characters
 * until the next (non-overlapping) occurrence of "*\/" should be ignored.
 * (Here, occurrences happen in reading order: line by line from left to right.) To be clear,
 * the string "/*\/" does not yet end the block comment,
 * as the ending would be overlapping the beginning.
 *
 * The first effective comment takes precedence over others.
 *
 * For example, if the string "//" occurs in a block comment, it is ignored.
 * Similarly, if the string "/*" occurs in a line or block comment, it is also ignored.
 * If a certain line of code is empty after removing comments, you must not output that line:
 * each string in the answer list will be non-empty.
 *
 * There will be no control characters, single quote, or double quote characters.
 *
 * For example, source = "string s = "/* Not a comment. *\/";"will not be a test case.
 * Also,nothing else such as defines or macros will interfere with the comments.
 *
 * It is guaranteed that every open block comment will eventually be closed,so"/*"outside of a line
 * or block comment always starts a new comment.
 *
 * Finally,implicit newline characters can be deleted by block comments.Please see the examples below for details.
 *
 * After removing the comments from the source code,return the source code in the same format.
 */

public class Solution {

    public List<String> removeComments(String[] source) {
        boolean block = false;
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (var line : source) {
            int n = line.length();
            boolean inline = false;
            for (int i = 0; i < n; i++) {
                char curr = line.charAt(i);
                switch (curr) {
                    case '/' -> {
                        if (i + 1 <n && (!inline && !block)) {
                            char next = line.charAt(i + 1);
                            if (next == '/' || next == '*') {
                                inline = next == '/';
                                block = next == '*';
                                i++; continue;
                            }
                        }
                    }
                    case '*' -> {
                        if (i + 1 < n) {
                            if (line.charAt(i + 1) == '/' && block) {
                                block = false;
                                i++; continue;
                            }
                        }
                    }
                }

                if (!inline && !block) sb.append(curr);
            }
            if (!sb.isEmpty() && !block) {
                ans.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().removeComments(new String[]{
            "/*Test program */",
            "int main()", "{ ",
            "  // variable declaration ",
            "int a, b, c;",
            "/* This is a test",
            "   multiline  ",
            "   comment for ",
            "   testing */",
            "a = b + c;",
            "}"
        }));

        PrettyPrinter.println(new Solution().removeComments(new String[]{
            "a/*comment",
            "line",
            "more_comment*/b"
        }));

        PrettyPrinter.println(new Solution().removeComments(new String[]{
            "a///*comment",
            "line",
            "more_comment*/b"
        }));
    }

}
