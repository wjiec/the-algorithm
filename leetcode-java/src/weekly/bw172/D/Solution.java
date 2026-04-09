package weekly.bw172.D;

import java.util.ArrayList;
import java.util.List;
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
        // 从左侧开始, 隔一个数删除一个数; 然后再从右侧开始, 隔一个数删除一个数;
        //  - 1 2 3 4 5 6 7 8 9
        // -> 1   3   5   7   9
        //    1       5       9 <-
        // -> 1               9
        //                    9 <-
        //
        //  - 1 2 3 4 5 6 7 8
        // -> 1   3   5   7
        //        3       7     <-
        // ->     3
        //
        //  - 1 2 3 4 5 6 7
        // -> 1   3   5   7
        //        3       7     <-
        // ->     3
        //
        //  - 1 2 3 4 5 6
        // -> 1   3   5
        //    1       5         <-
        // -> 1
        //
        //  - 1 2 3 4 5
        // -> 1   3   5
        //    1       5         <-
        // -> 1
        //
        // 有以下性质:
        //  - 一次操作就会删除 n / 2 个数, 所以最终是经过 ceil(log2(n)) 次操作
        //      - 1e15 也就是最多操作 50 次
        //  - 如果每轮剩下的数是奇数, 则会保留两端, 否则会去掉操作的对端的数

        return n;
    }

    public static void main(String[] args) {
        int[] numbers = IntStream.range(1, 1001).toArray();
        for (int i = 1; numbers.length > 1; i++) {
            List<Integer> next = new ArrayList<>();
            if ((i & 1) == 1) { // 从左到右
                for (int j = 0; j < numbers.length; j++) {
                    if ((j & 1) == 0) next.add(numbers[j]);
                }
            } else {
                for (int j = 0; j < numbers.length; j++) {
                    if ((j & 1) == 0) next.add(0, numbers[numbers.length - j - 1]);
                }
            }

            System.out.printf("%02d (%4d): %s\n", i, next.size(), next);
            numbers = next.stream().mapToInt(Integer::intValue).toArray();
        }
    }

}
