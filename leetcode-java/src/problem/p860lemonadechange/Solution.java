package problem.p860lemonadechange;

/**
 * 860. Lemonade Change
 *
 * https://leetcode-cn.com/problems/lemonade-change/
 *
 * At a lemonade stand, each lemonade costs $5. 
 *
 * Customers are standing in a queue to buy from you, and order one at a time (in the order specified by bills).
 *
 * Each customer will only buy one lemonade and pay with either a $5, $10, or $20 bill.
 * You must provide the correct change to each customer, so that the net transaction is that the customer pays $5.
 *
 * Note that you don't have any change in hand at first.
 *
 * Return true if and only if you can provide every customer with correct change.
 */

public class Solution {

    public boolean lemonadeChange(int[] bills) {
        int[] dollars = new int[21];
        for (var bill : bills) {
            dollars[bill]++;
            if (bill == 10 && --dollars[5] < 0) {
                return false;
            } else if (bill == 20) {
                if (--dollars[5] < 0) {
                    return false;
                }

                if (dollars[10] > 0) {
                    --dollars[10];
                } else if (dollars[5] >= 2) {
                    dollars[5] -= 2;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().lemonadeChange(new int[]{5,5,5,10,20});
        assert new Solution().lemonadeChange(new int[]{5,5,5,10});
        assert !new Solution().lemonadeChange(new int[]{10,10});
        assert !new Solution().lemonadeChange(new int[]{5,5,10,10,20});
    }

}
