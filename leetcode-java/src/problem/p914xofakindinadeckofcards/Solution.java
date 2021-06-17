package problem.p914xofakindinadeckofcards;

/**
 * 914. X of a Kind in a Deck of Cards
 *
 * https://leetcode-cn.com/problems/x-of-a-kind-in-a-deck-of-cards/
 *
 * In a deck of cards, each card has an integer written on it.
 *
 * Return true if and only if you can choose X >= 2 such that it is possible to
 * split the entire deck into 1 or more groups of cards, where:
 *
 * Each group has exactly X cards.
 * All the cards in each group have the same integer.
 */

public class Solution {

    private int[] primes = new int[]{
        2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97
    };

    public boolean hasGroupsSizeX(int[] deck) {
        int[] map = new int[10000];
        for (var n : deck) {
            map[n]++;
        }

        int max = 0, min = 10000;
        for (var n : map) {
            if (n > max) max = n;
            if (n < min) min = n;
        }

        if (max == 1 || min == 1) return false;
        if (max == min) return true;

        for (var prime : primes) {
            if (min % prime == 0 && max % prime == 0) {
                boolean ok = true;
                for (var n : map) {
                    if (n != 0 && n % prime != 0) {
                        ok = false;
                        break;
                    }
                }

                if (ok) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        assert new Solution().hasGroupsSizeX(new int[]{1,1,1,1,2,2,2,2,2,2});
        assert new Solution().hasGroupsSizeX(new int[]{1,2,3,4,4,3,2,1});
        assert !new Solution().hasGroupsSizeX(new int[]{1,1,1,2,2,2,3,3});
        assert !new Solution().hasGroupsSizeX(new int[]{1});
        assert new Solution().hasGroupsSizeX(new int[]{1,1});
        assert new Solution().hasGroupsSizeX(new int[]{1,1,2,2,2,2});
    }

}
