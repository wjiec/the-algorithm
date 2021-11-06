package problem.p1694reformatphonenumber;

import java.util.ArrayDeque;
import java.util.Stack;

/**
 * 1694. Reformat Phone Number
 *
 * https://leetcode-cn.com/problems/reformat-phone-number/
 *
 * You are given a phone number as a string number. number consists of digits, spaces ' ', and/or dashes '-'.
 *
 * You would like to reformat the phone number in a certain manner. Firstly, remove all spaces and dashes.
 *
 * Then, group the digits from left to right into blocks of length 3 until there are 4 or fewer digits.
 *
 * The final digits are then grouped as follows:
 *
 * 2 digits: A single block of length 2.
 * 3 digits: A single block of length 3.
 * 4 digits: Two blocks of length 2 each.
 *
 * The blocks are then joined by dashes.
 *
 * Notice that the reformatting process should never produce any blocks of length 1
 * and produce at most two blocks of length 2.
 *
 * Return the phone number after formatting.
 */

public class Solution {

    public String reformatNumber(String number) {
        ArrayDeque<Character> deque = new ArrayDeque<>();
        for (var c : number.toCharArray()) {
            if (c >= '0' && c <= '9') deque.add(c);
        }
        int div = deque.size() / 3, remain = deque.size() % 3;
        if (remain == 1 && div != 0) { div--; remain += 3; }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < div; i++) {
            sb.append(deque.removeFirst()).append(deque.removeFirst()).append(deque.removeFirst()).append('-');
        }

        if (remain == 0) sb.deleteCharAt(sb.length() - 1);
        if (remain <= 3) {
            while (!deque.isEmpty()) {
                sb.append(deque.removeFirst());
            }
        } else {
            sb.append(deque.removeFirst()).append(deque.removeFirst())
                .append('-')
                .append(deque.removeFirst()).append(deque.removeFirst());
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().reformatNumber("915-03").equals("915-03");

        assert new Solution().reformatNumber("1").equals("1");
        assert new Solution().reformatNumber("12").equals("12");
        assert new Solution().reformatNumber("123").equals("123");
        assert new Solution().reformatNumber("1234").equals("12-34");
        assert new Solution().reformatNumber("12345").equals("123-45");
        assert new Solution().reformatNumber("123456").equals("123-456");
        assert new Solution().reformatNumber("1234567").equals("123-45-67");
        assert new Solution().reformatNumber("12345678").equals("123-456-78");
        assert new Solution().reformatNumber("123456789").equals("123-456-789");

        assert new Solution().reformatNumber("1-23-45 6").equals("123-456");
        assert new Solution().reformatNumber("123 4-567").equals("123-45-67");
        assert new Solution().reformatNumber("123 4-5678").equals("123-456-78");
        assert new Solution().reformatNumber("12").equals("12");
        assert new Solution().reformatNumber("--17-5 229 35-39475 ").equals("175-229-353-94-75");
    }

}
