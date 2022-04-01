package daily.d220401p954arrayofdoubledpairs;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 954. Array of Doubled Pairs
 *
 * https://leetcode-cn.com/problems/array-of-doubled-pairs/
 *
 * Given an integer array of even length arr, return true if it is possible to
 * reorder arr such that arr[2 * i + 1] = 2 * arr[2 * i] for every 0 <= i < len(arr) / 2,
 * or false otherwise.
 */

public class Solution {

    public boolean canReorderDoubled(int[] arr) {
        int zero = 0;
        TreeSet<Integer> set = new TreeSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (var n : arr) {
            if (n == 0) { zero++; continue; }

            set.add(n);
            map.merge(n, 1, Integer::sum);
        }

        if (zero % 2 != 0) return false;
        for (var n : set) {
            int c1 = map.getOrDefault(n, 0);
            if (c1 != 0) {
                if (n < 0 && n % 2 != 0) return false;

                int pair = n < 0 ? n / 2 : 2 * n;
                int c2 = map.getOrDefault(pair, 0);
                if (c2 < c1) return false;
                map.put(pair, c2 - c1);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        assert new Solution().canReorderDoubled(new int[]{2,4,0,0,8,1});
        assert new Solution().canReorderDoubled(new int[]{10,20,40,80});

        assert new Solution().canReorderDoubled(new int[]{});
        assert !new Solution().canReorderDoubled(new int[]{3,1,3,6});
        assert !new Solution().canReorderDoubled(new int[]{2,1,2,6});
        assert new Solution().canReorderDoubled(new int[]{4,-2,2,-4});
        assert !new Solution().canReorderDoubled(new int[]{-7,-3,4,-2,2,-4});
        assert new Solution().canReorderDoubled(new int[]{4,-2,0,0,2,-4});
        assert !new Solution().canReorderDoubled(new int[]{4,-2,0,0,0,1,2,-4});
    }

}
