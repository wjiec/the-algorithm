package season.spring2022.p0;

public class Solution {

    public int giveGem(int[] gem, int[][] operations) {
        for (var op : operations) {
            int x = op[0], y = op[1];
            int v = gem[x] / 2;
            gem[x] -= v;
            gem[y] += v;
        }

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (var n : gem) {
            if (n < min) min = n;
            if (n > max) max = n;
        }
        return max - min;
    }

    public static void main(String[] args) {
    }

}
