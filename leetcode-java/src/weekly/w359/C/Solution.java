package weekly.w359.C;

import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

/**
 * 2830. Maximize the Profit as the Salesman
 *
 * https://leetcode.cn/contest/weekly-contest-359/problems/maximize-the-profit-as-the-salesman/
 *
 * You are given an integer n representing the number of houses on a number line, numbered from 0 to n - 1.
 *
 * Additionally, you are given a 2D integer array offers where offers[i] = [starti, endi, goldi], indicating
 * that ith buyer wants to buy all the houses from starti to endi for goldi amount of gold.
 *
 * As a salesman, your goal is to maximize your earnings by strategically selecting and selling houses to buyers.
 *
 * Return the maximum amount of gold you can earn.
 *
 * Note that different buyers can't buy the same house, and some houses may remain unsold.
 */

public class Solution {

    public int maximizeTheProfit(int n, List<List<Integer>> offers) {
        offers.sort(Comparator.comparingInt(v -> v.get(1)));

        int ans = 0;
        TreeMap<Integer, Integer> dp = new TreeMap<>(); dp.put(-1, 0);
        for (var offer : offers) {
            int start = offer.get(0), end = offer.get(1) + 1, gold = offer.get(2);
            dp.put(end, Math.max(dp.getOrDefault(end, dp.floorEntry(end).getValue()), dp.floorEntry(start).getValue() + gold));
            ans = Math.max(ans, dp.get(end));
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
