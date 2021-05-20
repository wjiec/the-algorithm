package problem.p412fizzbuzz;

import java.util.ArrayList;
import java.util.List;

/**
 * 412. Fizz Buzz
 *
 * https://leetcode-cn.com/problems/fizz-buzz/
 *
 * Given an integer n, return a string array answer (1-indexed) where:
 *
 * answer[i] == "FizzBuzz" if i is divisible by 3 and 5.
 * answer[i] == "Fizz" if i is divisible by 3.
 * answer[i] == "Buzz" if i is divisible by 5.
 * answer[i] == i if non of the above conditions are true.
 */

public class Solution {

    public List<String> fizzBuzz(int n) {
        List<String> rs = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                rs.add("FizzBuzz");
            } else if (i % 3 == 0) {
                rs.add("Fizz");
            } else if (i % 5 == 0) {
                rs.add("Buzz");
            } else {
                rs.add(String.valueOf(i));
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        assert new Solution().fizzBuzz(15).equals(List.of("1", "2", "Fizz", "4", "Buzz", "Fizz",
            "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz"));
    }

}
