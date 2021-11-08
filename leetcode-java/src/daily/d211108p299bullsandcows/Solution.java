package daily.d211108p299bullsandcows;

import java.util.HashSet;
import java.util.Set;

/**
 * 299. Bulls and Cows
 *
 * https://leetcode-cn.com/problems/bulls-and-cows/
 *
 * You are playing the Bulls and Cows game with your friend.
 *
 * You write down a secret number and ask your friend to guess what the number is.
 *
 * When your friend makes a guess, you provide a hint with the following info:
 *
 * The number of "bulls", which are digits in the guess that are in the correct position.
 * The number of "cows", which are digits in the guess that are in your secret number
 * but are located in the wrong position.
 *
 * Specifically, the non-bull digits in the guess that could be rearranged such that they become bulls.
 *
 * Given the secret number secret and your friend's guess guess, return the hint for your friend's guess.
 *
 * The hint should be formatted as "xAyB", where x is the number of bulls and y is the number of cows.
 *
 * Note that both secret and guess may contain duplicate digits.
 */

public class Solution {

    public String getHint(String secret, String guess) {
        Set[] map = new Set[10];
        for (int i = 0; i < 10; i++) map[i] = new HashSet<Integer>();
        for (int i = 0; i < secret.length(); i++) {
            map[secret.charAt(i) - '0'].add(i);
        }

        int a = 0, b = 0;
        int[] bs = new int[10];
        for (int i = 0; i < guess.length(); i++) {
            int val = guess.charAt(i) - '0';
            if (map[val].contains(i)) { a++; map[val].remove(i); }
            else if (map[val].size() != 0) bs[val]++;
        }

        for (int i = 0; i < map.length; i++) {
            b += Math.min(map[i].size(), bs[i]);
        }

        return String.format("%dA%dB", a, b);
    }

    public static void main(String[] args) {
        assert new Solution().getHint("1807", "7810").equals("1A3B");
        assert new Solution().getHint("1123", "0111").equals("1A1B");
        assert new Solution().getHint("1", "0").equals("0A0B");
        assert new Solution().getHint("1", "1").equals("1A0B");
    }

}
