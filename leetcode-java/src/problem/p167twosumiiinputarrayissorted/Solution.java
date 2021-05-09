package problem.p167twosumiiinputarrayissorted;

import common.Checker;

public class Solution {

    public int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        for (; l < r; l++) {
            int v = target - numbers[l];
            for (; numbers[r] > v; r--);
            if (numbers[r] == v) {
                break;
            }
        }
        return new int[]{l + 1, r + 1};
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().twoSum(new int[]{2,7,11,15}, 9), new int[]{1,2});
        assert Checker.check(new Solution().twoSum(new int[]{2,3,4}, 6), new int[]{1,3});
        assert Checker.check(new Solution().twoSum(new int[]{-1,0}, -1), new int[]{1,2});
        assert Checker.check(new Solution().twoSum(new int[]{-1,0,1}, 0), new int[]{1,3});
    }

}
