package weekly.bw158.C;

import common.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ability.Ability.Math.gcd;

/**
 * Q3. Maximize Subarray GCD Score
 *
 * https://leetcode.cn/contest/biweekly-contest-158/problems/maximize-subarray-gcd-score/
 *
 * You are given an array of positive integers nums and an integer k.
 *
 * You may perform at most k operations. In each operation, you can choose one element
 * in the array and double its value. Each element can be doubled at most once.
 *
 * The score of a contiguous subarray is defined as the product of its length and the
 * greatest common divisor (GCD) of all its elements.
 *
 * Your task is to return the maximum score that can be achieved by
 * selecting a contiguous subarray from the modified array.
 *
 * Note:
 * The greatest common divisor (GCD) of an array is the largest integer that
 * evenly divides all the array elements.
 */

public class Solution {

    private static final List<Integer> primes = new ArrayList<>();
    static {
        boolean[] sifting = new boolean[(int) (Math.sqrt(1e9) + 1)];
        Arrays.fill(sifting, true);
        for (int i = 2; i < sifting.length; i++) {
            if (sifting[i]) {
                for (int j = 2; i * j < sifting.length; j++) {
                    sifting[i * j] = false;
                }
            }
        }

        for (int i = 2; i < sifting.length; i++) {
            if (sifting[i]) primes.add(i);
        }
    }

    private static class DynamicList {
        private int len = 0;
        private long score = 1;
        private final int[] data = new int[31];
        public void add(int v) { data[len++] = v; score *= v; }
        public void intersect(DynamicList dl) {
            int nl = 0; long ns = 1;
            for (int i = 0, j = 0; i < len && j < dl.len; ) {
                if (data[i] == dl.data[j]) {
                    data[nl++] = data[i];
                    ns *= data[i];
                    i++; j++;
                } else if (data[i] < dl.data[j]) j++;
                else i++;
            }
            len = nl; score = ns;
        }
    }

    @Tag("因数分解")
    public long maxGCDScore(int[] nums, int k) {
        int n = nums.length; long ans = 0;

        int[] factor2 = new int[n];
        DynamicList[] dls = new DynamicList[n];
        Arrays.setAll(dls, i -> new DynamicList());
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, nums[i] * 2L);

            factor2[i] = Integer.numberOfTrailingZeros(nums[i]);
            nums[i] >>= factor2[i];

            for (var p : primes) {
                for (; nums[i] % p == 0; nums[i] /= p) dls[i].add(p);
                if (p > Math.sqrt(nums[i])) break;
            }
            if (nums[i] > 1) dls[i].add(nums[i]);
        }

        // 能翻倍也就是可以在因子中多加一个 2, 现在要求的是所有子数组里的最大 GCD 分数
        //  - 也就是求子数组的最长公共子串的乘积是多少
        //  - 可以在不超过 k 个元素里加入因子 2
        for (int i = 0; i < n; i++) {
            // 使用单个元素作为子数组的情况, 可以直接乘以 2
            ans = Math.max(ans, nums[i] * 2L);

            // 找到第一组值不为 0 的 f2 组
            int first2 = factor2[i];
            int[] groups = new int[31]; groups[factor2[i]]++;

            // 枚举子数组 [i, j], 计算所有可能的值
            for (int j = i + 1; j < n; j++) {
                groups[factor2[j]]++;
                dls[i].intersect(dls[j]);
                first2 = Math.min(first2, factor2[j]);

                // 如果 k 次操作能让这组的都增加 1 个 2 的话, 那就执行操作
                ans = Math.max(ans, ((j - i + 1) * dls[i].score) << (first2 + (k >= groups[first2] ? 1 : 0)));
            }
        }

        return ans;
    }

    private static class Optimization {
        public long maxGCDScore(int[] nums, int k) {
            int[] fac2 = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                fac2[i] = Integer.numberOfTrailingZeros(nums[i]);
                nums[i] >>= fac2[i];
            }

            long ans = 0;
            for (int l = 0; l < nums.length; l++) {
                int g = 0; int[] f2 = new int[32]; int x = 31;
                for (int r = l; r < nums.length; r++) {
                    g = gcd(g, nums[r]);
                    f2[fac2[r]]++;
                    x = Math.min(x, fac2[r]);
                    ans = Math.max(ans, ((r - l + 1L) * g) << (x + (f2[x] <= k ? 1 : 0)));
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Solution().maxGCDScore(new int[]{
            524692812,996985597,19176074,628687465,965627687,975892415,582472842,44634185,
            691006067,177053843,978358212,584830077,954892460,363140935,778393689,466618093,
            53760934,381439354,208315246,444117540,946170248,417075347,796176397,363186397,
            474585270,515115648,604942885,111612992,672598729,173446785,882452577,298450591,
            378221065,177412830,245256762,365764939,681965817,76531160,826849462,732615690,
            426397135,86027381,903980043,314255624,517082950,813851668,128611852,899805944,
            418271366,24209703,379643091,634972885,44153367,622832730,691995943,223627454
        }, 53) == 1993971194;

        assert new Solution().maxGCDScore(new int[]{
            111986331,169021160,372174769,718453856,896578728,241690242,539236501,
            97518362,903404866,893947538,851049062,694972873,511542245,734689882,
            888541965,762464056,606751294,679706753,815108887,170619220,478034776,
            227987865,249145015,152576483,832972900,182816491,433081210,435581407,
            919058708,307341920,910869175,106105446,862910126,941162126,551209572,
            20914360,444198810,952548680,215823426,610239514,582025544,935868232,
            632595894,408995680,919774066,262445303,423492973,713282013,77128055,
            49829862,46082947}, 50) == 1905097360;

        assert new Solution().maxGCDScore(new int[]{2, 4}, 1) == 8;
        assert new Solution().maxGCDScore(new int[]{3, 5, 7}, 2) == 14;
        assert new Solution().maxGCDScore(new int[]{5, 5, 5}, 1) == 15;

        assert new Optimization().maxGCDScore(new int[]{2, 4}, 1) == 8;
        assert new Optimization().maxGCDScore(new int[]{3, 5, 7}, 2) == 14;
        assert new Optimization().maxGCDScore(new int[]{5, 5, 5}, 1) == 15;
    }

}
