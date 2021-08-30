package daily.d210830p528randompickwithweight;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 528. Random Pick with Weight
 *
 * https://leetcode-cn.com/problems/random-pick-with-weight/
 *
 * You are given an array of positive integers w where w[i] describes the weight of ith index (0-indexed).
 *
 * We need to call the function pickIndex() which randomly returns an integer in the range [0, w.length - 1].
 * pickIndex() should return the integer proportional to its weight in the w array.
 *
 * For example, for w = [1, 3], the probability of picking the index 0 is 1 / (1 + 3) = 0.25 (i.e 25%)
 * while the probability of picking the index 1 is 3 / (1 + 3) = 0.75 (i.e 75%).
 *
 * More formally, the probability of picking index i is w[i] / sum(w).
 */

public class Solution {

    static class Picker {

        private int sum = 0;
        private final int[] prefix;

        public Picker(int[] w) {
            prefix = new int[w.length];
            prefix[0] = w[0]; sum += w[0];
            for (int i = 1, l = w.length; i < l; i++) {
                prefix[i] = prefix[i - 1] + w[i];
                sum += w[i];
            }
        }

        public int pickIndex() {
            int x = ThreadLocalRandom.current().nextInt(sum);
            int lo = 0, hi = prefix.length - 1;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (prefix[mid] < x) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            return lo;
        }
    }

    public static void main(String[] args) {
        Picker picker = new Picker(new int[]{1,3});
        System.out.println(picker.pickIndex());
        System.out.println(picker.pickIndex());
        System.out.println(picker.pickIndex());
        System.out.println(picker.pickIndex());
        System.out.println(picker.pickIndex());
    }

}
