package weekly.bw172.D;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

/**
 * Q4. Last Remaining Integer After Alternating Deletion Operations
 *
 * https://leetcode.cn/contest/biweekly-contest-172/problems/last-remaining-integer-after-alternating-deletion-operations/
 *
 * You are given an integer n.
 *
 * We write the integers from 1 to n in a sequence from left to right. Then, alternately apply
 * the following two operations until only one integer remains, starting with operation 1:
 *
 * Operation 1: Starting from the left, delete every second number.
 * Operation 2: Starting from the right, delete every second number.
 *
 * Return the last remaining integer.
 */

public class Solution {

    public long lastInteger(long n) {
        // 从左侧开始, 隔一个数删除一个数; 然后再从右侧开始, 隔一个数删除一个数
        //
        // 原数组是一个公差 d = 1, 首项 start = 1, 项数为 n 的等差数列
        //  - 执行一次操作之后, d' = 2, start' = 1, n' = ceil(n / 2)
        //  - 执行第二次操作时, 我们将其翻转过来
        //      - 也就是 d'' = -4, start'' = last, n'' = ceil(n' / 2)
        //
        // 此时考虑最后一项 last 的值
        //  - 如果 n 为奇数, 那么删除之后首尾保持不变, last = start + (n - 1) * d
        //  - 如果 n 为偶数, 那么删除之后尾项会被删除, 留下倒数第二项, last = start + (n - 2) * d
        //
        // 循环直到 n = 1 结束, 答案为 start
        long start = 1, d = 1; // n = n
        while (n > 1) {
            // 执行一次操作后
            //  - start 会变为尾项
            start = start + (n - 2 + (n & 1)) * d;
            //  - d 会变成 -2 倍 (需要翻转)
            d *= -2;
            //  - n 会变为 ceil(n / 2)
            n = (n + 1) / 2;
        }
        return start;
    }

    public static void main(String[] args) {
        IntFunction<Integer> burstForce = (c) -> {
            int[] numbers = IntStream.range(1, c).toArray();
            for (int i = 1; numbers.length > 1; i++) {
                List<Integer> next = new ArrayList<>();
                if ((i & 1) == 1) { // 从左到右
                    for (int j = 0; j < numbers.length; j++) {
                        if ((j & 1) == 0) next.add(numbers[j]);
                    }
                } else { // 从右到左
                    for (int j = 0; j < numbers.length; j++) {
                        if ((j & 1) == 0) next.add(0, numbers[numbers.length - j - 1]);
                    }
                }

                numbers = next.stream().mapToInt(Integer::intValue).toArray();
            }
            return numbers[0];
        };
        IntFunction<Boolean> check = (c) -> new Solution().lastInteger(c) == burstForce.apply(c + 1);

        for (int i = 1; i <= 200; i++) {
            assert check.apply(i);
        }
    }

}
