package problem.p1996thenumberofweakcharactersinthegame;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 1996. The Number of Weak Characters in the Game
 *
 * https://leetcode.cn/problems/the-number-of-weak-characters-in-the-game/
 *
 * You are playing a game that contains multiple characters, and each of the characters has
 * two main properties: attack and defense. You are given a 2D integer array properties
 * where properties[i] = [attacki, defensei] represents the properties of the ith character
 * in the game.
 *
 * A character is said to be weak if any other character has both attack and defense levels
 * strictly greater than this character's attack and defense levels. More formally, a
 * character i is said to be weak if there exists another character j where attackj > attacki
 * and defensej > defensei.
 *
 * Return the number of weak characters.
 */

public class Solution {

    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (a, b) -> a[0] == b[0] ? (b[1] - a[1]) : (a[0] - b[0]));

        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (var p : properties) {
            while (!stack.isEmpty() && stack.peek() < p[1]) {
                stack.pop(); ans++;
            }
            stack.push(p[1]);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numberOfWeakCharacters(new int[][]{{1,1},{2,1},{2,2},{1,2}}) == 1;

        assert new Solution().numberOfWeakCharacters(new int[][]{{5,5},{6,3},{3,6}}) == 0;
        assert new Solution().numberOfWeakCharacters(new int[][]{{2,2},{3,3}}) == 1;
        assert new Solution().numberOfWeakCharacters(new int[][]{{1,5},{10,4},{4,3}}) == 1;
    }

}
