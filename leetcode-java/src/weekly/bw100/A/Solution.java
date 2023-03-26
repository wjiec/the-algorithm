package weekly.bw100.A;

/**
 * 2591. Distribute Money to Maximum Children
 *
 * https://leetcode.cn/contest/biweekly-contest-100/problems/distribute-money-to-maximum-children/
 *
 * You are given an integer money denoting the amount of money (in dollars) that you have and
 * another integer children denoting the number of children that you must distribute the money to.
 *
 * You have to distribute the money according to the following rules:
 *
 * All money must be distributed.
 * Everyone must receive at least 1 dollar.
 * Nobody receives 4 dollars.
 * Return the maximum number of children who may receive exactly 8 dollars if you distribute the
 * money according to the aforementioned rules. If there is no way to distribute the money, return -1.
 */

public class Solution {

    public int distMoney(int money, int children) {
        if (money < children) return -1;
        if (money == 4 && children == 1) return -1;
        if (money != 8 && children == 1) return 0;

        int next = distMoney(money - 8, children - 1);
        return next == -1 ? 0 : (next + 1);
    }

    public static void main(String[] args) {
        assert new Solution().distMoney(10, 2) == 1;
        assert new Solution().distMoney(27, 20) == 1;
        assert new Solution().distMoney(13, 3) == 1;
        assert new Solution().distMoney(20, 3) == 1;
    }

}
