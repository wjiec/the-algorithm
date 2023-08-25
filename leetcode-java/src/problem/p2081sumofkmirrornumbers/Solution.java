package problem.p2081sumofkmirrornumbers;

import java.util.PriorityQueue;

/**
 * 2081. Sum of k-Mirror Numbers
 *
 * https://leetcode.cn/problems/sum-of-k-mirror-numbers/
 *
 * A k-mirror number is a positive integer without leading zeros that reads
 * the same both forward and backward in base-10 as well as in base-k.
 *
 * For example, 9 is a 2-mirror number. The representation of 9 in base-10 and
 * base-2 are 9 and 1001 respectively, which read the same both forward and backward.
 *
 * On the contrary, 4 is not a 2-mirror number. The representation of 4 in base-2 is 100, which
 * does not read the same both forward and backward.
 *
 * Given the base k and the number n, return the sum of the n smallest k-mirror numbers.
 */

public class Solution {

    public long kMirror(int k, int n) {
        long ans = 0; this.k = k;
        for (int i = 0; i < n; i++) ans += next();
        return ans;
    }

    private int k = 0, len = 1;
    private final PriorityQueue<Long> q = new PriorityQueue<>();

    private long next() {
        while (q.isEmpty()) fill();
        return q.remove();
    }

    private void fill() {
        int[] bits = new int[len / 2 + len % 2]; bits[0] = 1;
        do {
            long curr = toNumber(bits, len % 2 == 1);
            if (isMirror(curr)) q.add(curr);
        } while (!incr(bits));

        len++;
    }

    private long toNumber(int[] bits, boolean skip) {
        long ans = 0; int n = bits.length;
        for (int bit : bits) ans = ans * k + bit;
        for (int i = n - (skip ? 2 : 1); i >= 0; i--) {
            ans = ans * k + bits[i];
        }
        return ans;
    }

    private boolean isMirror(long v) {
        String s = String.valueOf(v);
        for (int l = 0, r = s.length() - 1; l < r; l++, r--) {
            if (s.charAt(l) != s.charAt(r)) return false;
        }
        return true;
    }

    // 返回值表示是否已经溢出
    private boolean incr(int[] bits) {
        for (int i = bits.length - 1; i >= 0; i--) {
            if (++bits[i] != k) break;
            bits[i] = 0;
        }
        return bits[0] == 0;
    }

    public static void main(String[] args) {
        assert new Solution().kMirror(5, 25) == 6849225412L;

        assert new Solution().kMirror(2, 5) == 25;
        assert new Solution().kMirror(3, 7) == 499;
        assert new Solution().kMirror(7, 17) == 20379000;
    }

}
