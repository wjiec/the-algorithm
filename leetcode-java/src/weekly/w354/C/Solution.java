package weekly.w354.C;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Solution {

    public int minimumIndex(List<Integer> nums) {
        Map<Integer, Integer> leftFreq = new HashMap<>();
        Map<Integer, Integer> rightFreq = new HashMap<>();
        for (var v : nums) rightFreq.merge(v, 1, Integer::sum);

        TreeMap<Integer, Integer> lff = new TreeMap<>();
        TreeMap<Integer, Integer> rff = new TreeMap<>();
        for (var f : rightFreq.values()) rff.merge(f, 1, Integer::sum);

        for (int i = 0; i < nums.size(); i++) {
            int curr = leftFreq.merge(nums.get(i), 1, Integer::sum);
            lff.merge(curr - 1, -1, Integer::sum);
            lff.merge(curr, 1, Integer::sum);
        }
        return 1;
    }

    public static void main(String[] args) {
    }

}
