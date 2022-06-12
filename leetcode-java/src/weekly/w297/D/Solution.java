package weekly.w297.D;

import java.util.*;

/**
 * 6094. Naming a Company
 *
 * https://leetcode.cn/contest/weekly-contest-297/problems/naming-a-company/
 *
 * You are given an array of strings ideas that represents a list of names to be used in the
 * process of naming a company. The process of naming a company is as follows:
 *
 * Choose 2 distinct names from ideas, call them ideaA and ideaB.
 * Swap the first letters of ideaA and ideaB with each other.
 * If both of the new names are not found in the original ideas, then the name ideaA ideaB
 * (the concatenation of ideaA and ideaB, separated by a space) is a valid company name.
 * Otherwise, it is not a valid name.
 * Return the number of distinct valid names for the company.
 */

public class Solution {

    public long distinctNames(String[] ideas) {
        Map<Character, Set<String>> map = new HashMap<>();
        for (var idea : ideas) {
            map.computeIfAbsent(idea.charAt(0), v -> new HashSet<>())
                .add(idea.substring(1));
        }

        long ans = 0;
        for (char i = 'a'; i <= 'z'; i++) {
            for (char j = 'a'; j <= 'z'; j++) {
                if (i == j) continue;

                Set<String> la = map.getOrDefault(i, Collections.emptySet());
                Set<String> lb = map.getOrDefault(j, Collections.emptySet());
                Set<String> laDiff = new HashSet<>(la), lbDiff = new HashSet<>(lb);
                laDiff.removeAll(lb); lbDiff.removeAll(la);
                ans += (long) laDiff.size() * lbDiff.size();
            }
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
