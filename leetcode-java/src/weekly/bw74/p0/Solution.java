package weekly.bw74.p0;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public boolean divideArray(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (var n : nums) map.merge(n, 1, Integer::sum);

        for (var c : map.values()) {
            if (c % 2 != 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
    }

}
