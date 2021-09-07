package problem.p1346checkifnanditsdoubleexist;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1346. Check If N and Its Double Exist
 *
 * https://leetcode-cn.com/problems/check-if-n-and-its-double-exist/
 *
 * Given an array arr of integers, check if there exists two integers N and M such that
 * N is the double of M ( i.e. N = 2 * M).
 *
 * More formally check if there exists two indices i and j such that :
 *
 * i != j
 * 0 <= i, j < arr.length
 * arr[i] == 2 * arr[j]
 */

public class Solution {

    public boolean checkIfExist(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (var n : arr) map.merge(n, 1, Integer::sum);

        for (var n : arr) {
            if (map.containsKey(n + n)) {
                if (n != 0 || map.get(0) > 1) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().checkIfExist(new int[]{-20,8,-6,-14,0,-19,14,4});
        assert new Solution().checkIfExist(new int[]{-10,12,-20,-8,15});

        assert new Solution().checkIfExist(new int[]{10,2,5,3});
        assert new Solution().checkIfExist(new int[]{7,1,14,11});
        assert !new Solution().checkIfExist(new int[]{3,1,7,11});
    }

}
