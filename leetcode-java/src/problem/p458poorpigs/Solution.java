package problem.p458poorpigs;

import common.TODO;
import common.Tag;

/**
 * 458. Poor Pigs
 *
 * https://leetcode.cn/problems/poor-pigs/
 *
 * There are buckets buckets of liquid, where exactly one of the buckets is poisonous.
 * To figure out which one is poisonous, you feed some number of (poor) pigs the liquid
 * to see whether they will die or not. Unfortunately, you only have minutesToTest minutes
 * to determine which bucket is poisonous.
 *
 * You can feed the pigs according to these steps:
 *
 * Choose some live pigs to feed.
 *
 * For each pig, choose which buckets to feed it. The pig will consume all the chosen buckets
 * simultaneously and will take no time. Each pig can feed from any number of buckets, and each
 * bucket can be fed from by any number of pigs.
 *
 * Wait for minutesToDie minutes. You may not feed any other pigs during this time.
 *
 * After minutesToDie minutes have passed, any pigs that have been fed the poisonous bucket
 * will die, and all others will survive.
 *
 * Repeat this process until you run out of time.
 *
 * Given buckets, minutesToDie, and minutesToTest, return the minimum number of pigs needed
 * to figure out which bucket is poisonous within the allotted time.
 */

public class Solution {

    @TODO(tips = "信息论")
    @Tag("通过 N 轮测试确定 M 个数据中的某一个")
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        double c = (double) minutesToTest / minutesToDie + 1;
        return (int) Math.ceil(Math.log(buckets) / Math.log(c) - 1e-5);
    }

    public static void main(String[] args) {
        assert new Solution().poorPigs(125, 1, 4) == 3;

        // 11_1110_0100
        assert new Solution().poorPigs(996, 15, 60) == 5;
        assert new Solution().poorPigs(996, 15, 45) == 5;
        assert new Solution().poorPigs(996, 15, 30) == 7;
        assert new Solution().poorPigs(996, 15, 15) == 10;

        assert new Solution().poorPigs(1000, 15, 60) == 5;
        assert new Solution().poorPigs(4, 15, 15) == 2;
        assert new Solution().poorPigs(4, 15, 30) == 2;
    }

}
