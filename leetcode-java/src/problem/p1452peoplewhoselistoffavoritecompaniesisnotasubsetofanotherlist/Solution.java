package problem.p1452peoplewhoselistoffavoritecompaniesisnotasubsetofanotherlist;

import common.Checker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1452. People Whose List of Favorite Companies Is Not a Subset of Another List
 *
 * https://leetcode.cn/problems/people-whose-list-of-favorite-companies-is-not-a-subset-of-another-list/
 *
 * Given the array favoriteCompanies where favoriteCompanies[i] is the list of favorites
 * companies for the ith person (indexed from 0).
 *
 * Return the indices of people whose list of favorite companies is not a subset
 * of any other list of favorites companies.
 *
 * You must return the indices in increasing order.
 */

public class Solution {

    private record Favorite(Set<String> companies, int index) {}

    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        Favorite[] favorites = new Favorite[favoriteCompanies.size()];
        for (int i = 0; i < favoriteCompanies.size(); i++) {
            favorites[i] = new Favorite(new HashSet<>(favoriteCompanies.get(i)), i);
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < favorites.length; i++) {
            if (favorites[i] != null) {
                boolean unique = true;
                Set<String> ref = favorites[i].companies;
                for (int j = 0; j < favorites.length; j++) {
                    if (i != j && favorites[j] != null && favorites[j].companies.containsAll(ref)) {
                        unique = false; break;
                    }
                }
                if (unique) ans.add(favorites[i].index);
                else favorites[i] = null;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().peopleIndexes(List.of(
            List.of("leetcode","google","facebook"),
            List.of("google","microsoft"),
            List.of("google","facebook"),
            List.of("google"),
            List.of("amazon")
        )), List.of(0,1,4));

        assert Checker.check(new Solution().peopleIndexes(List.of(
            List.of("leetcode","google","facebook"),
            List.of("leetcode","amazon"),
            List.of("facebook","google")
        )), List.of(0,1));

        assert Checker.check(new Solution().peopleIndexes(List.of(
            List.of("leetcode"),
            List.of("google"),
            List.of("facebook"),
            List.of("amazon")
        )), List.of(0,1,2,3));
    }

}
