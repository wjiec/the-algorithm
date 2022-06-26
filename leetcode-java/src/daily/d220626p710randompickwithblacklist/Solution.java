package daily.d220626p710randompickwithblacklist;

import java.util.*;

/**
 * 710. Random Pick with Blacklist
 *
 * https://leetcode.cn/problems/random-pick-with-blacklist/
 *
 * You are given an integer n and an array of unique integers blacklist. Design an algorithm to pick a
 * random integer in the range [0, n - 1] that is not in blacklist. Any integer that is in the mentioned
 * range and not in blacklist should be equally likely to be returned.
 *
 * Optimize your algorithm such that it minimizes the number of calls to the built-in
 * random function of your language.
 *
 * Implement the Solution class:
 *
 * Solution(int n, int[] blacklist) Initializes the object with the integer n and the blacklisted integers blacklist.
 * int pick() Returns a random integer in the range [0, n - 1] and not in blacklist.
 */

public class Solution {

    private final int bound;
    private final Random rand = new Random();
    private final Map<Integer, Integer> map = new HashMap<>();

    public Solution(int n, int[] blacklist) {
        bound = n - blacklist.length;

        Set<Integer> black = new HashSet<Integer>();
        for (int b : blacklist) {
            if (b >= bound) {
                black.add(b);
            }
        }

        int next = bound;
        for (int v : blacklist) {
            if (v < bound) {
                while (black.contains(next)) ++next;
                map.put(v, next);
                ++next;
            }
        }
    }

    public int pick() {
        int v = rand.nextInt(bound);
        return map.getOrDefault(v, v);
    }

    public static void main(String[] args) {
        Solution solution = new Solution(7, new int[]{2, 3, 5});
        System.out.println(solution.pick());
        System.out.println(solution.pick());
        System.out.println(solution.pick());
        System.out.println(solution.pick());
        System.out.println(solution.pick());
    }

}
