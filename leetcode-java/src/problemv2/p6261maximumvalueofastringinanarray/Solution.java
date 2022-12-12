package problemv2.p6261maximumvalueofastringinanarray;

/**
 * 6261. Maximum Value of a String in an Array
 *
 * https://leetcode.cn/problems/maximum-value-of-a-string-in-an-array/
 *
 * The value of an alphanumeric string can be defined as:
 *
 * The numeric representation of the string in base 10, if it comprises of digits only.
 * The length of the string, otherwise.
 * Given an array strs of alphanumeric strings, return the maximum value of any string in strs.
 */

public class Solution {

    public int maximumValue(String[] strs) {
        int ans = 0;
        for (var word : strs) {
            int number = 0;
            for (var c : word.toCharArray()) {
                if (Character.isDigit(c)) {
                    number = number * 10 + c - '0';
                } else {
                    number = word.length(); break;
                }
            }
            ans = Math.max(ans, number);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maximumValue(new String[]{"alic3","bob","3","4","00000"}) == 5;
        assert new Solution().maximumValue(new String[]{"1","01","001","0001"}) == 1;
    }

}
