package daily.d220318p2043simplebanksystem;

/**
 * 2043. Simple Bank System
 *
 * https://leetcode-cn.com/problems/simple-bank-system/
 *
 * You have been tasked with writing a program for a popular bank that
 * will automate all its incoming transactions (transfer, deposit, and withdraw).
 *
 * The bank has n accounts numbered from 1 to n.
 * The initial balance of each account is stored in a 0-indexed integer array balance,
 * with the (i + 1)th account having an initial balance of balance[i].
 */

public class Solution {

    private static class Bank {
        private final long[] balance;
        public Bank(long[] balance) { this.balance = balance; }

        public boolean transfer(int account1, int account2, long money) {
            if (account1 < 1 || account1 > balance.length) return false;
            if (account2 < 1 || account2 > balance.length) return false;
            if (balance[account1 - 1] < money) return false;

            balance[account1 - 1] -= money;
            balance[account2 - 1] += money;
            return true;
        }

        public boolean deposit(int account, long money) {
            if (account < 1 || account > balance.length) return false;

            balance[account - 1] += money;
            return true;
        }

        public boolean withdraw(int account, long money) {
            if (account < 1 || account > balance.length) return false;
            if (balance[account - 1] < money) return false;

            balance[account - 1] -= money;
            return true;
        }
    }

    public static void main(String[] args) {
        Bank bank = new Bank(new long[]{10, 100, 20, 50, 30});
        assert bank.withdraw(3, 10);
        assert bank.transfer(5, 1, 20);
        assert bank.deposit(5, 20);
        assert !bank.transfer(3, 4, 15);
        assert !bank.withdraw(10, 50);
    }

}
