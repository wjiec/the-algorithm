package problem.p2094finding3digitevennumbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2094. Finding 3-Digit Even Numbers
 *
 * https://leetcode-cn.com/problems/finding-3-digit-even-numbers/
 *
 * You are given an integer array digits, where each element is a digit. The array may contain duplicates.
 *
 * You need to find all the unique integers that follow the given requirements:
 *
 * The integer consists of the concatenation of three elements from digits in any arbitrary order.
 * The integer does not have leading zeros.
 * The integer is even.
 * For example, if the given digits were [1, 2, 3], integers 132 and 312 follow the requirements.
 *
 * Return a sorted array of the unique integers.
 */

public class Solution {

    public int[] findEvenNumbers(int[] digits) {
        int[] map = new int[10];
        for (var n : digits) map[n]++;

        if (map[0] == 0 && map[2] == 0 && map[4] == 0 && map[6] == 0 && map[8] == 0) return new int[]{};

        int[] curr = new int[10];
        List<Integer> list = new ArrayList<>();

        for (int i = 100; i < 1000; i += 2) {
            Arrays.fill(curr, 0);
            for (int n = i; n != 0; n /= 10) curr[n % 10]++;

            boolean ok = true;
            for (int j = 0; j < 10; j++) {
                if (curr[j] > map[j]) {
                    ok = false;
                    break;
                }
            }

            if (ok) list.add(i);
        }

        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().findEvenNumbers(new int[]{2,1,3,0})));
        System.out.println(Arrays.toString(new Solution().findEvenNumbers(new int[]{2,2,8,8,2})));
        System.out.println(Arrays.toString(new Solution().findEvenNumbers(new int[]{3,7,5})));
        System.out.println(Arrays.toString(new Solution().findEvenNumbers(new int[]{0,2,0,0})));
        System.out.println(Arrays.toString(new Solution().findEvenNumbers(new int[]{0,0,0})));
    }

}
