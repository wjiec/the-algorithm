package problem.p721accountsmerge;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 721. Accounts Merge
 *
 * https://leetcode-cn.com/problems/accounts-merge/
 *
 * Given a list of accounts where each element accounts[i] is a list of strings,
 * where the first element accounts[i][0] is a name, and the rest of the elements
 * are emails representing emails of the account.
 *
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person
 * if there is some common email to both accounts.
 *
 * Note that even if two accounts have the same name,
 * they may belong to different people as people could have the same name.
 *
 * A person can have any number of accounts initially,
 * but all of their accounts definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format:
 * the first element of each account is the name, and the rest of the elements are emails in sorted order.
 * The accounts themselves can be returned in any order.
 */

public class Solution {

    private static class UnionFind {
        private final int[] parent;
        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        public void union(int a, int b) {
            parent[find(b)] = find(a);
        }
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        AtomicInteger count = new AtomicInteger();

        Map<String, String> names = new HashMap<>();
        Map<String, Integer> emails = new HashMap<>();
        for (var account : accounts) {
            for (int i = 1; i < account.size(); i++) {
                names.put(account.get(i), account.get(0));
                emails.computeIfAbsent(account.get(i), v -> count.getAndIncrement());
            }
        }

        UnionFind uf = new UnionFind(count.get());
        for (var account : accounts) {
            int a = emails.get(account.get(1));
            for (int i = 2; i < account.size(); i++) {
                uf.union(a, emails.get(account.get(i)));
            }
        }

        Map<Integer, List<String>> indexes = new HashMap<>();
        for (var email : emails.keySet()) {
            int index = uf.find(emails.get(email));
            indexes.computeIfAbsent(index, v -> new ArrayList<>());
            indexes.get(index).add(email);
        }

        List<List<String>> ans = new ArrayList<>();
        for (var account : indexes.values()) {
            Collections.sort(account);
            List<String> curr = new ArrayList<>();

            curr.add(names.get(account.get(0)));
            curr.addAll(account);
            ans.add(curr);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().accountsMerge(List.of(
            List.of("David","David0@m.co","David1@m.co"),
            List.of("David","David3@m.co","David4@m.co"),
            List.of("David","David4@m.co","David5@m.co"),
            List.of("David","David2@m.co","David3@m.co"),
            List.of("David","David1@m.co","David2@m.co")
        )));

        System.out.println(new Solution().accountsMerge(List.of(
            List.of("John", "johnsmith@mail.com", "john00@mail.com"),
            List.of("John", "johnnybravo@mail.com"),
            List.of("John", "johnsmith@mail.com", "john_newyork@mail.com"),
            List.of("Mary", "mary@mail.com")
        )));

        System.out.println(new Solution().accountsMerge(List.of(
            List.of("Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"),
            List.of("Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"),
            List.of("Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"),
            List.of("Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"),
            List.of("Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co")
        )));
    }

}
