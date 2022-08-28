package weekly.w308.C;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {

    public int garbageCollection(String[] garbage, int[] travel) {
        int[] sum = new int[travel.length + 1];
        for (int i = 1; i <= travel.length; i++) {
            sum[i] = sum[i - 1] + travel[i - 1];
        }

        int t = 0, m = 0, p = 0, g = 0;
        for (int i = 0; i < garbage.length; i++) {
            t += garbage[i].length();
            if (garbage[i].contains("M")) m = i;
            if (garbage[i].contains("P")) p = i;
            if (garbage[i].contains("G")) g = i;
        }

        return t + sum[m] + sum[p] + sum[g];
    }

    public static void main(String[] args) {
        assert new Solution().garbageCollection(new String[]{"G","P","GP","GG"}, new int[]{2,4,3}) == 21;
        assert new Solution().garbageCollection(new String[]{"MMM","PGM","GP"}, new int[]{3,10}) == 37;
    }

}
