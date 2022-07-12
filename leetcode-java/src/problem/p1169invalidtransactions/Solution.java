package problem.p1169invalidtransactions;

import common.Checker;

import java.util.*;

/**
 * 1169. Invalid Transactions
 *
 * https://leetcode.cn/problems/invalid-transactions/
 *
 * A transaction is possibly invalid if:
 *
 * the amount exceeds $1000, or;
 * if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.
 *
 * You are given an array of strings transaction where transactions[i] consists of comma-separated values
 * representing the name, time (in minutes), amount, and city of the transaction.
 *
 * Return a list of transactions that are possibly invalid. You may return the answer in any order.
 */

public class Solution {

    private record Transaction(int index, String name, int time, int amount, String city) {}

    public List<String> invalidTransactions(String[] transactions) {
        Transaction[] list = new Transaction[transactions.length];
        for (int i = 0; i < transactions.length; i++) {
            String[] ss = transactions[i].split(",");
            list[i] = new Transaction(i, ss[0], Integer.parseInt(ss[1]), Integer.parseInt(ss[2]), ss[3]);
        }
        Arrays.sort(list, Comparator.comparingInt(v -> v.time));

        Set<Integer> indexes = new HashSet<>();
        Queue<Transaction> queue = new ArrayDeque<>();
        for (Transaction curr : list) {
            if (curr.amount > 1000) indexes.add(curr.index);


            while (!queue.isEmpty() && queue.peek().time + 60 < curr.time)
                queue.remove();

            for (var item : queue) {
                if (item.name.equals(curr.name) && !item.city.equals(curr.city)) {
                    indexes.add(item.index);
                    indexes.add(curr.index);
                }
            }

            queue.add(curr);
        }

        List<String> ans = new ArrayList<>();
        for (var index : indexes) {
            ans.add(transactions[index]);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().invalidTransactions(new String[]{
            "alex,676,260,bangkok","bob,656,1366,bangkok",
            "alex,393,616,bangkok","bob,820,990,amsterdam",
            "alex,596,1390,amsterdam"
        }), List.of(
            "bob,656,1366,bangkok","alex,596,1390,amsterdam"
        ));

        assert Checker.anyOrder(new Solution().invalidTransactions(new String[]{
            "alice,20,800,mtv", "bob,50,1200,mtv",
            "alice,20,800,mtv", "alice,50,1200,mtv",
            "alice,20,800,mtv", "alice,50,100,beijing"
        }), List.of(
            "alice,20,800,mtv","bob,50,1200,mtv",
            "alice,20,800,mtv","alice,50,1200,mtv",
            "alice,20,800,mtv","alice,50,100,beijing"
        ));

        assert Checker.anyOrder(new Solution().invalidTransactions(new String[]{
            "alice,20,1220,mtv","alice,20,1220,mtv"
        }), List.of("alice,20,1220,mtv","alice,20,1220,mtv"));

        assert Checker.anyOrder(new Solution().invalidTransactions(new String[]{
            "alice,20,800,mtv","alice,50,100,beijing"
        }), List.of("alice,20,800,mtv","alice,50,100,beijing"));

        assert Checker.anyOrder(new Solution().invalidTransactions(new String[]{
            "alice,20,800,mtv","alice,50,1200,mtv"
        }), List.of("alice,50,1200,mtv"));

        assert Checker.anyOrder(new Solution().invalidTransactions(new String[]{
            "alice,20,800,mtv","bob,50,1200,mtv"
        }), List.of("bob,50,1200,mtv"));
    }

}
