package weekly.w391.B;

/**
 * 3100. Water Bottles II
 *
 * https://leetcode.cn/contest/weekly-contest-391/problems/water-bottles-ii/
 *
 * You are given two integers numBottles and numExchange.
 *
 * numBottles represents the number of full water bottles that you initially have.
 *
 * In one operation, you can perform one of the following operations:
 *
 * Drink any number of full water bottles turning them into empty bottles.
 * Exchange numExchange empty bottles with one full water bottle. Then, increase numExchange by one.
 *
 * Note that you cannot exchange multiple batches of empty bottles for the same value of numExchange.
 * For example, if numBottles == 3 and numExchange == 1, you cannot exchange 3 empty water bottles for 3 full bottles.
 *
 * Return the maximum number of water bottles you can drink.
 */

public class Solution {

    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int ans = numBottles, empty = numBottles;
        while (empty >= numExchange) {
            ans++; // 喝掉一瓶
            empty -= numExchange; // 交换
            numExchange++; // 代价增加
            empty++;
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
