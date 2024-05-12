package daily.d240512p1553minimumnumberofdaystoeatnoranges;

import java.util.HashMap;
import java.util.Map;

/**
 * 1553. Minimum Number of Days to Eat N Oranges
 *
 * https://leetcode.cn/problems/minimum-number-of-days-to-eat-n-oranges/
 *
 * There are n oranges in the kitchen and you decided to eat some of these oranges every day as follows:
 *
 * Eat one orange.
 * If the number of remaining oranges n is divisible by 2 then you can eat n / 2 oranges.
 * If the number of remaining oranges n is divisible by 3 then you can eat 2 * (n / 3) oranges.
 * You can only choose one of the actions per day.
 *
 * Given the integer n, return the minimum number of days to eat n oranges.
 */

public class Solution {

    private final Map<Integer, Integer> memo = new HashMap<>();

    public int minDays(int n) {
        if (n <= 1) return n;
        if (!memo.containsKey(n)) memo.put(n, 1 + Math.min(n % 2 + minDays(n / 2), n % 3 + minDays(n / 3)));
        return memo.get(n);
    }

    public static void main(String[] args) {
        assert new Solution().minDays(10) == 4;
        assert new Solution().minDays(9209408) == 23;
    }

}
