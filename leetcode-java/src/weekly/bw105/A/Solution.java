package weekly.bw105.A;

import java.util.Arrays;

public class Solution {

    public int buyChoco(int[] prices, int money) {
        Arrays.sort(prices);
        if (prices[0] + prices[1] <= money) {
            return money - prices[0] - prices[1];
        }
        return money;
    }

    public static void main(String[] args) {
    }

}
