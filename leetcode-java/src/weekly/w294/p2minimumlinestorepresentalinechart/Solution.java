package weekly.w294.p2minimumlinestorepresentalinechart;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 6076. Minimum Lines to Represent a Line Chart
 *
 * https://leetcode.cn/contest/weekly-contest-294/problems/minimum-lines-to-represent-a-line-chart/
 *
 * You are given a 2D integer array stockPrices where stockPrices[i] = [dayi, pricei] indicates
 * the price of the stock on day dayi is pricei. A line chart is created from the array by
 * plotting the points on an XY plane with the X-axis representing the day and the Y-axis
 * representing the price and connecting adjacent points.
 */

public class Solution {

    public int minimumLines(int[][] stockPrices) {
        if (stockPrices.length == 1) return 0;
        if (stockPrices.length == 2) return 1;
        Arrays.sort(stockPrices, Comparator.comparingInt(v -> v[0]));

        int ans = 1;
        for (int i = 1; i < stockPrices.length - 1; i++) {
            if (!equals(stockPrices[i - 1], stockPrices[i], stockPrices[i + 1])) ans++;
        }
        return ans;
    }

    private boolean equals(int[] a, int[] b, int[] c) {
        return ((long) a[1] - (long) b[1]) * ((long) b[0] - (long) c[0]) == ((long) a[0] - (long) b[0]) * ((long) b[1] - (long) c[1]);
    }

    public static void main(String[] args) {
    }

}
