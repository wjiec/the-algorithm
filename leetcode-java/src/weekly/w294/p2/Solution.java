package weekly.w294.p2;

import java.util.Arrays;
import java.util.Comparator;

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
