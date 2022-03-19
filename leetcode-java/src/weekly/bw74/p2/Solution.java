package weekly.bw74.p2;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

    public int halveArray(int[] nums) {
        double sum = 0;
        PriorityQueue<Double> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (var n : nums) {
            sum += n;
            queue.add((double) n);
        }

        int ans = 0;
        for (double curr = 0, half = sum / 2; curr < half; ans++) {
            double val = queue.remove();
            curr += val / 2;
            queue.add(val / 2);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().halveArray(new int[]{1}) == 1;
        assert new Solution().halveArray(new int[]{5,19,8,1}) == 3;
        assert new Solution().halveArray(new int[]{3,8,20}) == 3;
    }

}
