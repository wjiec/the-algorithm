package offer2.p72jJ0w9p;

/**
 * 剑指 Offer II 072. 求平方根
 *
 * https://leetcode-cn.com/problems/jJ0w9p/
 *
 * 给定一个非负整数 x ，计算并返回 x 的平方根，即实现 int sqrt(int x) 函数。
 *
 * 正数的平方根有两个，只输出其中的正数平方根。
 *
 * 如果平方根不是整数，输出只保留整数的部分，小数部分将被舍去。
 */

public class Solution {

    public int mySqrt(int x) {
        int l = 1, r = x;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (mid <= x / mid) {
                if ((mid + 1) > x / (mid + 1)) return mid;
                l = mid + 1;
            } else r = mid - 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        assert new Solution().mySqrt(4) == 2;
        assert new Solution().mySqrt(8) == 2;
    }

}
