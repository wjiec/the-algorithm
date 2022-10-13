package problem.p2115findallpossiblerecipesfromgivensupplies;

import common.Checker;

import java.util.*;

/**
 * 2115. Find All Possible Recipes from Given Supplies
 *
 * https://leetcode.cn/problems/find-all-possible-recipes-from-given-supplies/
 *
 * You have information about n different recipes. You are given a string array recipes and a 2D
 * string array ingredients. The ith recipe has the name recipes[i], and you can create it if you
 * have all the needed ingredients from ingredients[i]. Ingredients to a recipe may need to be created
 * from other recipes, i.e., ingredients[i] may contain a string that is in recipes.
 *
 * You are also given a string array supplies containing all the ingredients that you initially
 * have, and you have an infinite supply of all of them.
 *
 * Return a list of all the recipes that you can create. You may return the answer in any order.
 *
 * Note that two recipes may contain each other in their ingredients.
 */

public class Solution {

    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, Set<String>> deps = new HashMap<>();
        Map<String, Integer> reqs = new HashMap<>();
        for (int i = 0; i < recipes.length; i++) {
            reqs.put(recipes[i], ingredients.get(i).size());
            for (var ing : ingredients.get(i)) {
                deps.computeIfAbsent(ing, v -> new HashSet<>())
                    .add(recipes[i]);
            }
        }

        List<String> ans = new ArrayList<>();
        Queue<String> queue = new ArrayDeque<>(Arrays.asList(supplies));
        while (!queue.isEmpty()) {
            String done = queue.remove();
            if (deps.containsKey(done)) {
                for (var next : deps.get(done)) {
                    if (reqs.merge(next, -1, Integer::sum) == 0) {
                        ans.add(next); queue.add(next);
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().findAllRecipes(
            new String[]{"bread"},
            List.of(List.of("yeast","flour")),
            new String[]{"yeast","flour","corn"}
        ), List.of("bread"));

        assert Checker.anyOrder(new Solution().findAllRecipes(
            new String[]{"bread","sandwich"},
            List.of(List.of("yeast","flour"), List.of("bread","meat")),
            new String[]{"yeast","flour","meat"}
        ), List.of("bread","sandwich"));

        assert Checker.anyOrder(new Solution().findAllRecipes(
            new String[]{"bread","sandwich","burger"},
            List.of(List.of("yeast","flour"), List.of("bread","meat"), List.of("sandwich","meat","bread")),
            new String[]{"yeast","flour","meat"}
        ), List.of("bread","sandwich","burger"));

        assert Checker.anyOrder(new Solution().findAllRecipes(
            new String[]{"bread"},
            List.of(List.of("yeast","flour")),
            new String[]{"yeast"}
        ), List.of());
    }

}
