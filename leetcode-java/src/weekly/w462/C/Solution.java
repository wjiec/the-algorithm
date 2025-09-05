package weekly.w462.C;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Q3. Maximum Total from Optimal Activation Order
 *
 * https://leetcode.cn/contest/weekly-contest-462/problems/maximum-total-from-optimal-activation-order/
 *
 * You are given two integer arrays value and limit, both of length n.
 *
 * Initially, all elements are inactive. You may activate them in any order.
 *
 * To activate an inactive element at index i, the number of currently
 * active elements must be strictly less than limit[i].
 *
 * When you activate the element at index i, it adds value[i] to the
 * total activation value (i.e., the sum of value[i] for all elements
 * that have undergone activation operations).
 *
 * After each activation, if the number of currently active elements becomes x,
 * then all elements j with limit[j] <= x become permanently inactive, even if they are already active.
 *
 * Return the maximum total you can obtain by choosing the activation order optimally.
 */

public class Solution {

    public long maxTotal(int[] value, int[] limit) {
        int n = value.length;
        // 规则如下:
        //  - 当前激活的元素的数量是 cnt, 我们只能激活 limit[i] > cnt 的所有元素位置 i
        //  - 当激活一个元素之后的数量为 cnt + 1, 则所有满足 limit[j] <= cnt + 1 的元素都会变为非活跃状态
        // 也就是我们只能选择 limit[i] > cnt 的元素激活, 所有 limit[j] <= cnt 的都无法再次选择

        // 按照 limit 从小往大排列, 如果 limit 相同则按照 value 从大到小排列
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) { pairs[i][0] = limit[i]; pairs[i][1] = value[i]; }
        Arrays.sort(pairs, (a, b) -> a[0] != b[0] ? (a[0] - b[0]) : (b[1] - a[1]));

        long ans = 0;
        PriorityQueue<Integer> activated = new PriorityQueue<>();
        for (int i = 0, disabled = 0; i < n; i++) {
            // 如果已经永久失效了, 那就不能再次激活了
            if (disabled < pairs[i][0]) {
                ans += pairs[i][1];
                activated.add(pairs[i][0]);

                // 我们需要记录已经永久失效的最大 limit
                disabled = Math.max(disabled, activated.size());
                while (!activated.isEmpty() && activated.peek() <= disabled) activated.remove();
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxTotal(new int[]{3,5,8}, new int[]{2,1,3}) == 16;
        assert new Solution().maxTotal(new int[]{4,2,6}, new int[]{1,1,1}) == 6;
        // 2, 3, 3, 3
        // 5, 4, 2, 1
        assert new Solution().maxTotal(new int[]{4,1,5,2}, new int[]{3,3,2,3}) == 12;
    }

}
