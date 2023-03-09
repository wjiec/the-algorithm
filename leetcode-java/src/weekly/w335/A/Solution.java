package weekly.w335.A;

/**
 * 2582. Pass the Pillow
 *
 * https://leetcode.cn/contest/weekly-contest-335/problems/pass-the-pillow/
 *
 * There are n people standing in a line labeled from 1 to n.
 *
 * The first person in the line is holding a pillow initially.
 *
 * Every second, the person holding the pillow passes it to the
 * next person standing in the line. Once the pillow reaches the
 * end of the line, the direction changes, and people continue
 * passing the pillow in the opposite direction.
 *
 * For example, once the pillow reaches the nth person they pass it
 * to the n - 1th person, then to the n - 2th person and so on.
 *
 * Given the two positive integers n and time, return the index of the
 * person holding the pillow after time seconds.
 */

public class Solution {

    public int passThePillow(int n, int time) {
        int ans = 1, base = 1;
        for (int i = 0; i < time; i++) {
            if (ans == 1) base = 1;
            if (ans == n) base = -1;
            ans += base;
        }
        return ans;
    }

    private static class ByMath {
        public int passThePillow(int n, int time) {
            time %= 2 * n - 2;
            return time < n ? (time + 1) : (n - (time - n) - 1);
        }
    }

    public static void main(String[] args) {
        assert new ByMath().passThePillow(4, 5) == 2;
        assert new ByMath().passThePillow(3, 2) == 3;
    }

}
