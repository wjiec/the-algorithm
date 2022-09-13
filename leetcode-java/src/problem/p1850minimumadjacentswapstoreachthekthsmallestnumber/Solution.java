package problem.p1850minimumadjacentswapstoreachthekthsmallestnumber;

import common.Tag;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1850. Minimum Adjacent Swaps to Reach the Kth Smallest Number
 *
 * https://leetcode.cn/problems/minimum-adjacent-swaps-to-reach-the-kth-smallest-number/
 *
 * You are given a string num, representing a large integer, and an integer k.
 *
 * We call some integer wonderful if it is a permutation of the digits in num and is greater in value than num.
 * There can be many wonderful integers. However, we only care about the smallest-valued ones.
 *
 * For example, when num = "5489355142":
 * The 1st smallest wonderful integer is "5489355214".
 * The 2nd smallest wonderful integer is "5489355241".
 * The 3rd smallest wonderful integer is "5489355412".
 * The 4th smallest wonderful integer is "5489355421".
 * Return the minimum number of adjacent digit swaps that needs to be applied to
 * num to reach the kth smallest wonderful integer.
 *
 * The tests are generated in such a way that kth smallest wonderful integer exists.
 */

public class Solution {

    @Tag({"下一个更大的数"})
    public int getMinSwaps(String num, int k) {
        char[] chars = num.toCharArray();
        while (--k >= 0) nextNumber(chars);
        return swaps(num.toCharArray(), chars);
    }

    private void nextNumber(char[] chars) {
        // [value, index]
        Deque<int[]> stack = new ArrayDeque<>();
        for (int i = chars.length - 1; i >= 0; i--) {
            if (!stack.isEmpty() && stack.peek()[0] > chars[i]) {
                int idx = i;
                while (!stack.isEmpty() && stack.peek()[0] > chars[i]) {
                    idx = stack.remove()[1];
                }

                swap(chars, i, idx);
                sort(chars, i + 1);
                return;
            }
            stack.push(new int[]{chars[i], i});
        }
    }

    private int swaps(char[] a, char[] b) {
        int ans = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == b[i]) continue;

            // 找到最近的数字
            int j = i;
            while (j < b.length && b[j] != a[i]) j++;

            // 冒泡上来
            ans += j - i;
            for (; j > i; j--) swap(b, j, j - 1);
        }
        return ans;
    }

    private void swap(char[] cs, int l, int r) {
        char temp = cs[l]; cs[l] = cs[r]; cs[r] = temp;
    }

    private void sort(char[] chars, int i) {
        int[] map = new int[10];
        for (int j = i; j < chars.length; j++) {
            map[chars[j] - '0']++;
        }

        for (int j = 0; j < map.length; j++) {
            for (; map[j] != 0; map[j]--) {
                chars[i++] = (char) (j + '0');
            }
        }
    }

    public static void main(String[] args) {
        // 5489355142 5489355214  5489355241  5489355412  5489355421
        assert new Solution().getMinSwaps("5489355142", 4) == 2;
        assert new Solution().getMinSwaps("11112", 4) == 4;
        assert new Solution().getMinSwaps("00123", 1) == 1;
    }

}
