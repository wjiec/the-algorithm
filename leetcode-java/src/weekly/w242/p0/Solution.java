package weekly.w242.p0;

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
