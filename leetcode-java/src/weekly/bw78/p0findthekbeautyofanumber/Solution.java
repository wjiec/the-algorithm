package weekly.bw78.p0findthekbeautyofanumber;

/**
 * 5299. Find the K-Beauty of a Number
 *
 * https://leetcode.cn/contest/biweekly-contest-78/problems/find-the-k-beauty-of-a-number/
 *
 * The k-beauty of an integer num is defined as the number of substrings of num
 * when it is read as a string that meet the following conditions:
 *
 * It has a length of k.
 * It is a divisor of num.
 * Given integers num and k, return the k-beauty of num.
 *
 * Note:
 * Leading zeros are allowed.
 * 0 is not a divisor of any value.
 * A substring is a contiguous sequence of characters in a string.
 */

public class Solution {

    public int divisorSubstrings(int num, int k) {
        String s = String.valueOf(num);
        StringBuilder sb = new StringBuilder();
        sb.append(s, 0, k - 1);

        int ans = 0;
        for (int i = k - 1; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if (sb.length() > k) sb.deleteCharAt(0);

            int v = Integer.parseInt(sb.toString());
            if (v != 0 && num % v == 0) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
