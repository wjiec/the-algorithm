package problem.p1946largestnumberaftermutatingsubstring;

/**
 * 1946. Largest Number After Mutating Substring
 *
 * https://leetcode.cn/problems/largest-number-after-mutating-substring/
 *
 * You are given a string num, which represents a large integer. You are also given a 0-indexed integer
 * array change of length 10 that maps each digit 0-9 to another digit.
 *
 * More formally, digit d maps to digit change[d].
 *
 * You may choose to mutate a single substring of num. To mutate a substring, replace each digit num[i] with
 * the digit it maps to in change (i.e. replace num[i] with change[num[i]]).
 *
 * Return a string representing the largest possible integer after mutating (or choosing not to) a
 * single substring of num.
 *
 * A substring is a contiguous sequence of characters within the string.
 */

public class Solution {

    public String maximumNumber(String num, int[] change) {
        char[] chars = num.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int curr = chars[i] - '0';
            if (change[curr] > curr) {
                for (int j = i; j < chars.length; j++) {
                    int bit = chars[j] - '0';
                    if (change[bit] >= bit) {
                        chars[j] = (char) (change[bit] + '0');
                    } else break;
                }
                return new String(chars);
            }
        }
        return num;
    }

    public static void main(String[] args) {
        assert new Solution().maximumNumber("00001472194", new int[]{9,3,6,3,7,3,1,4,5,8}).equals("99993772194");

        assert new Solution().maximumNumber("132", new int[]{9,8,5,0,3,6,4,2,6,8}).equals("832");
        assert new Solution().maximumNumber("021", new int[]{9,4,3,5,7,2,1,9,0,6}).equals("934");
        assert new Solution().maximumNumber("5", new int[]{1,4,7,5,3,2,5,6,9,4}).equals("5");
    }

}
