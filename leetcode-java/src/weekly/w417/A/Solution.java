package weekly.w417.A;

/**
 * 100446. Find the K-th Character in String Game I
 *
 * https://leetcode.cn/contest/weekly-contest-417/problems/find-the-k-th-character-in-string-game-i/
 *
 * Alice and Bob are playing a game. Initially, Alice has a string word = "a".
 *
 * You are given a positive integer k.
 *
 * Now Bob will ask Alice to perform the following operation forever:
 *
 * Generate a new string by changing each character in word to its next character
 * in the English alphabet, and append it to the original word.
 *
 * For example, performing the operation on "c" generates "cd" and performing the operation
 * on "zb" generates "zbac".
 *
 * Return the value of the kth character in word, after enough operations have been done for word
 * to have at least k characters.
 *
 * Note that the character 'z' can be changed to 'a' in the operation.
 */

public class Solution {

    public char kthCharacter(int k) {
        StringBuilder curr = new StringBuilder("a");
        while (curr.length() <= k) {
            StringBuilder sb = new StringBuilder();
            for (var v : curr.toString().toCharArray()) {
                sb.append((char) ('a' + (((v - 'a') + 1) % 26)));
            }
            curr.append(sb);
        }

        return curr.charAt(k - 1);
    }

    public static void main(String[] args) {
        assert new Solution().kthCharacter(5) == 'b';
        assert new Solution().kthCharacter(10) == 'c';
    }

}
