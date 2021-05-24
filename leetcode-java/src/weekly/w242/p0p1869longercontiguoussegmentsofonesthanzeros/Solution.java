package weekly.w242.p0p1869longercontiguoussegmentsofonesthanzeros;

/**
 * 1869. Longer Contiguous Segments of Ones than Zeros
 *
 * Given a binary string s, return true if the longest contiguous segment of 1s is strictly longer than
 * the longest contiguous segment of 0s in s. Return false otherwise.
 *
 * For example, in s = "110100010" the longest contiguous segment of 1s has length 2,
 * and the longest contiguous segment of 0s has length 3.
 *
 * Note that if there are no 0s, then the longest contiguous segment of 0s is considered to have length 0.
 * The same applies if there are no 1s.
 */

public class Solution {

    public boolean checkZeroOnes(String s) {
        int s0 = 0, s1 = 0, last = 0, c0 = 0, c1 = 0;
        for (var c : s.toCharArray()) {
            switch (c) {
                case '0':
                    if (c == last) {
                        c0++;
                    } else {
                        c0 = 1;
                        s1 = Math.max(s1, c1);
                    }
                    break;
                case '1':
                    if (c == last) {
                        c1++;
                    } else {
                        c1 = 1;
                        s0 = Math.max(s0, c0);
                    }
            }
            last = c;
        }
        return Math.max(s1, c1) > Math.max(s0, c0);
    }

    public static void main(String[] args) {
        assert new Solution().checkZeroOnes("1101");
        assert !new Solution().checkZeroOnes("111000");
        assert !new Solution().checkZeroOnes("110100010");
    }

}
