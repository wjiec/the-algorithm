package daily.d211013p412fizzbuzz;

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
        List<String> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) ans.add("FizzBuzz");
            else if (i % 3 == 0) ans.add("Fizz");
            else if (i % 5 == 0) ans.add("Buzz");
            else ans.add(String.valueOf(i));
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().fizzBuzz(100));
    }

}
