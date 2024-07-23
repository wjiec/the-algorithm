package weekly.w407.B;

/**
 * 3227. Vowels Game in a String
 *
 * https://leetcode.cn/contest/weekly-contest-407/problems/vowels-game-in-a-string/
 *
 * Alice and Bob are playing a game on a string.
 *
 * You are given a string s, Alice and Bob will take turns playing the following game where Alice starts first:
 *
 * On Alice's turn, she has to remove any non-empty substring from s that contains an odd number of vowels.
 * On Bob's turn, he has to remove any non-empty substring from s that contains an even number of vowels.
 * The first player who cannot make a move on their turn loses the game. We assume that both Alice and Bob play optimally.
 *
 * Return true if Alice wins the game, and false otherwise.
 *
 * The English vowels are: a, e, i, o, and u.
 */

@SuppressWarnings("IfStatementWithIdenticalBranches")
public class Solution {

    public boolean doesAliceWin(String s) {
        int vowel = 0;
        for (var c : s.toCharArray()) if (isVowel(c)) vowel++;
        // 如果没办法拿到奇数个元音字母, 直接失败
        if (vowel == 0) return false;
        // 如果有奇数个元音字母的话, 直接全部拿走就能赢
        if ((vowel & 1) == 1) return true;

        // 如果一共有偶数个元音字母, 有以下几种形式
        //  baab    abab    aabb    bbaa    abba    baba
        // 小红都可以赢
        return true;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public static void main(String[] args) {
    }

}
