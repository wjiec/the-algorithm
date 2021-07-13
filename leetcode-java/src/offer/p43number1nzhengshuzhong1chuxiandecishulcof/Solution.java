package offer.p43number1nzhengshuzhong1chuxiandecishulcof;

/**
 * 剑指 Offer 43. 1～n 整数中 1 出现的次数
 *
 * https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/
 *
 * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 *
 * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
 */

public class Solution {

    public int countDigitOne(int n) {
        int high = n / 10, curr = n % 10, low = 0, base = 1, ans = 0;
        while (high != 0 || curr != 0) {
            if (curr == 0) ans += high * base;
            else if (curr == 1) ans += high * base + low + 1;
            else ans += (high + 1) * base;

            low += curr * base;
            curr = high % 10;
            high /= 10;
            base *= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countDigitOne(12) == 5;
        assert new Solution().countDigitOne(13) == 6;
    }

}
