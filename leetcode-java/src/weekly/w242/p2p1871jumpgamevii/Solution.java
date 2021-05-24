package weekly.w242.p2p1871jumpgamevii;

import java.util.ArrayList;
import java.util.List;

/**
 * 1871. Jump Game VII
 *
 * You are given a 0-indexed binary string s and two integers minJump and maxJump.
 * In the beginning, you are standing at index 0, which is equal to '0'.
 * You can move from index i to index j if the following conditions are fulfilled:
 *
 * i + minJump <= j <= min(i + maxJump, s.length - 1), and
 * s[j] == '0'.
 *
 * Return true if you can reach index s.length - 1 in s, or false otherwise.
 */

public class Solution {

    public boolean canReach(String s, int minJump, int maxJump) {
        int sz = s.length();
        if (s.charAt(sz - 1) != '0') {
            return false;
        }

        List<Integer> canJump = new ArrayList<>(); canJump.add(0);
        for (int i = 1; i < sz; i++) {
            if (s.charAt(i) == '1') {
                continue;
            }

            int l = i - maxJump, r = i - minJump;
            Integer v = find(canJump, l);
            if (v == null || v > r) {
                continue;
            }
            canJump.add(i);
        }
        return canJump.get(canJump.size() - 1) == sz - 1;
    }

    private Integer find(List<Integer> can, int l) {
        for (var n : can) {
            if (n >= l) {
                return n;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        assert !new Solution().canReach("01", 1, 1);
        assert new Solution().canReach("00", 1, 1);
        assert new Solution().canReach("011010", 2, 3);
        assert !new Solution().canReach("01101110", 2, 3);
        assert new Solution().canReach("0000000000", 2, 5);
        assert !new Solution().canReach("0111111111111111111111111111111101111101111111111111111110", 5, 26);
    }

}
