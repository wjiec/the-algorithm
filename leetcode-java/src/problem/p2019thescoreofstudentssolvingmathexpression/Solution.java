package problem.p2019thescoreofstudentssolvingmathexpression;

import ability.Benchmark;

import java.util.*;

/**
 * 2019. The Score of Students Solving Math Expression
 *
 * https://leetcode.cn/problems/the-score-of-students-solving-math-expression
 *
 * You are given a string s that contains digits 0-9, addition symbols '+', and multiplication
 * symbols '*' only, representing a valid math expression of single digit numbers (e.g., 3+5*2).
 *
 * This expression was given to n elementary school students. The students were instructed to
 * get the answer of the expression by following this order of operations:
 *
 * Compute multiplication, reading from left to right; Then,
 * Compute addition, reading from left to right.
 *
 * You are given an integer array answers of length n, which are the submitted answers of the
 * students in no particular order. You are asked to grade the answers, by following these rules:
 *
 * If an answer equals the correct answer of the expression, this student will be rewarded 5 points;
 *
 * Otherwise, if the answer could be interpreted as if the student applied the operators in the wrong
 * order but had correct arithmetic, this student will be rewarded 2 points;
 *
 * Otherwise, this student will be rewarded 0 points.
 *
 * Return the sum of the points of the students.
 */

public class Solution {

    public int scoreOfStudents(String s, int[] answers) {
        char[] chars = s.toCharArray();
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '*') {
                dq.push(dq.pop() * (chars[++i] - '0'));
            } else if (chars[i] != '+') dq.push(chars[i] - '0');
        }

        int correct = 0;
        while (!dq.isEmpty()) correct += dq.remove();

        Set<Integer> wrongs = eval(chars, 0, chars.length);

        int ans = 0;
        for (var elem : answers) {
            if (elem == correct) ans += 5;
            else if (wrongs.contains(elem)) ans += 2;
        }
        return ans;
    }

    private final Map<Integer, Set<Integer>> memo = new HashMap<>();

    private Set<Integer> eval(char[] chars, int l, int r) {
        int key = (l << 16) | r;
        if (!memo.containsKey(key)) {
            Set<Integer> ans = new HashSet<>();
            if (r - l == 1) ans.add(chars[l] - '0');

            for (int i = l + 1; i < r; i++) {
                if (chars[i] == '*' || chars[i] == '+') {
                    for (var x : eval(chars, l, i)) {
                        if (x > 1000) continue;

                        for (var y : eval(chars, i + 1, r)) {
                            if (y > 1000) continue;

                            switch (chars[i]) {
                                case '+' -> { if (x + y <= 1000) ans.add(x + y); }
                                case '*' -> { if (x * y <= 1000) ans.add(x * y); }
                            }
                        }
                    }
                }
            }

            memo.put(key, ans);
        }

        return memo.get(key);
    }

    public static void main(String[] args) {
        Benchmark.benchmark("simple", () -> {
            assert new Solution().scoreOfStudents("4+4*8+8*4+4*8+4*8+4*4+8*4+8*4+4", new int[]{
                216,216,640,216,660,419,788,584,150,216,162,216,216,
            }) == 38;

            assert new Solution().scoreOfStudents("6+3*6+2*9+9*4+9*9+5*9+6", new int[]{
                512,210,210,594,210,875,762,210,210,270,342,330,210,168,767,22,462,634,194,
                210,552,210,210,384,210,348,210,267,553,139,210,389,614,210,825,234,900,435,
                210,699,454,504,300,210,210,210,215,984,210,522,943,654,793,714,870,693,210,
                686,210,720,153,210,957,510,210,810,210,846,714,528,942,210,342,974,210,71,
                906,402,726,924,388,696,210,366,210,888,825,210,210,210,654,996,846,559,210,
                492,210,698,210,856,576,324,822,894,666,210,935,210,840,45,641,210,210,588,
                708,966,462,300,210,759,480,210
            }) == 291;
            assert new Solution().scoreOfStudents("6+0*1", new int[]{12,9,6,4,8,6}) == 10;
            assert new Solution().scoreOfStudents("1+2*3+4", new int[]{13,21,11,15}) == 11;

            assert new Solution().scoreOfStudents("7+3*1*2", new int[]{20,13,42}) == 7;
            assert new Solution().scoreOfStudents("3+5*2", new int[]{13,0,10,13,13,16,16}) == 19;
            assert new Solution().scoreOfStudents("6+0*1", new int[]{12,9,6,4,8,6}) == 10;
        });
    }

}
