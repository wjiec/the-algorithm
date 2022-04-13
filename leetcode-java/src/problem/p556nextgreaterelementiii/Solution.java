package problem.p556nextgreaterelementiii;

/**
 * 556. Next Greater Element III
 *
 * https://leetcode-cn.com/problems/next-greater-element-iii/
 *
 * Given a positive integer n, find the smallest integer which has exactly the same digits existing
 * in the integer n and is greater in value than n.
 *
 * If no such positive integer exists, return -1.
 *
 * Note that the returned integer should fit in 32-bit integer, if there is a valid answer,
 * but it does not fit in 32-bit integer, return -1.
 */

public class Solution {

    public int nextGreaterElement(int n) {
        char[] chars = String.valueOf(n).toCharArray();
        int a = chars.length - 2;
        while (a >= 0 && chars[a] >= chars[a + 1]) a--;
        if (a < 0) return -1;

        int b = chars.length - 1;
        while (b >= 0 && chars[b] <= chars[a]) b--;
        swap(chars, a, b);

        for (int l = a + 1, r = chars.length - 1; l < r; l++, r--) {
            swap(chars, l, r);
        }

        try {
            return Integer.parseInt(new String(chars));
        } catch (NumberFormatException ignored) {}
        return -1;
    }

    private void swap(char[] chars, int a, int b) {
        char tmp = chars[a];
        chars[a] = chars[b];
        chars[b] = tmp;
    }

    public static void main(String[] args) {
        assert new Solution().nextGreaterElement(2147483486) == -1;

        assert new Solution().nextGreaterElement(12) == 21;
        assert new Solution().nextGreaterElement(21) == -1;
    }

}
