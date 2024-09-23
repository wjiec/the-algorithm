package weekly.w416.B;

/**
 * 3296. Minimum Number of Seconds to Make Mountain Height Zero
 *
 * https://leetcode.cn/contest/weekly-contest-416/problems/minimum-number-of-seconds-to-make-mountain-height-zero/
 *
 * You are given an integer mountainHeight denoting the height of a mountain.
 *
 * You are also given an integer array workerTimes representing the work time of workers in seconds.
 *
 * The workers work simultaneously to reduce the height of the mountain. For worker i:
 *
 * To decrease the mountain's height by x, it takes workerTimes[i] + workerTimes[i] * 2 + ... + workerTimes[i] * x seconds.
 *
 * For example:
 * To reduce the height of the mountain by 1, it takes workerTimes[i] seconds.
 * To reduce the height of the mountain by 2, it takes workerTimes[i] + workerTimes[i] * 2 seconds, and so on.
 *
 * Return an integer representing the minimum number of seconds required for the workers to make the height of the mountain 0.
 */

public class Solution {

    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long l = 1, r = Long.MAX_VALUE / 10, ans = 0;
        while (l < r) {
            long mid = l + (r - l) / 2;
            // 如果当前时间可以满足要求, 那么就缩小范围, 尝试找到更小的值
            if (check(mountainHeight, workerTimes, mid)) {
                ans = mid; r = mid;
            } else l = mid + 1;
        }
        return ans;
    }

    // 检查是否能在 time 时间内, 将 mountainHeight 降低为 0
    private boolean check(long mountainHeight, int[] workerTimes, long time) {
        for (var wt : workerTimes) {
            // 在 time 时间内, 计算每个 worker 可以降低的高度为 x
            //  wt * (1 + 2 + ... + n) <= time
            //  wt * n * (1 + n) / 2 <= time
            //  wt * n * (1 + n) <= 2 * time
            //  n^2 + n <= 2 * time / wt
            //  令 C = 2 * time / wt
            long c = 2 * time / wt;
            //  得 n^2 + n - c <= 0
            //      根据求根公式 R = (-b ± sqrt(b^2 - 4ac)) / 2a
            //      求得 n <= (-1 ± sqrt(1 + 4c)) / 2
            double r = (-1 + Math.sqrt(1 + 4 * c)) / 2;
            // 检查是否还有剩余的高度
            if ((mountainHeight -= (long) r) <= 0) break;
        }
        return mountainHeight <= 0;
    }

    public static void main(String[] args) {
        assert new Solution().minNumberOfSeconds(4, new int[]{2,1,1}) == 3;
        assert new Solution().minNumberOfSeconds(10, new int[]{3,2,2,4}) == 12;
        assert new Solution().minNumberOfSeconds(5, new int[]{1}) == 15;
    }

}
