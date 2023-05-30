package weekly.bw105.C;

import java.util.PriorityQueue;

public class Solution {

    public long maxStrength(int[] nums) {
        if (nums.length == 1) return nums[0];

        long np = 0, nn = 0, pos = 1;
        PriorityQueue<Integer> neg = new PriorityQueue<>();
        for (var v : nums) {
            if (v > 0) { np++; pos *= v; }
            else if (v < 0) { nn++; neg.add(v); }
        }
        if (np == 0 && nn < 2) return 0;

        for (; nn > 1; nn -= 2) {
            pos *= (long) neg.remove() * neg.remove();
        }
        return pos;
    }

    public static void main(String[] args) {
    }

}
