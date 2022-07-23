package weekly.bw83.A;

public class Solution {

    public String bestHand(int[] ranks, char[] suits) {
        boolean flush = true;
        for (int i = 1; i < suits.length; i++) {
            flush = flush && suits[i] == suits[i - 1];
        }
        if (flush) return "Flush";

        int[] map = new int[100];
        for (var n : ranks) map[n]++;
        for (var n : map) if (n >= 3) return "Three of a Kind";
        for (var n : map) if (n >= 2) return "Pair";
        return "High Card";
    }

    public static void main(String[] args) {
    }

}
