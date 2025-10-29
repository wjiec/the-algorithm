package weekly.w467.C;

import java.util.Arrays;

public class Solution {

    public boolean[] subsequenceSumAfterCapping(int[] nums, int k) {
        // ans[i] 表示是否可以通过在经过处理后找到一个子序列的和为 k
        //  - 处理操作: 将数组中的所有位置 j 修改为 min(nums[j], i)

        // 由于我们只需要选择子序列, 所以我们可以对数组进行排序
        Arrays.sort(nums);

        // 枚举我们需要截断的数字 x, 在数组中找到 >= x 的第一个位置 i
        //  - 此时在 [0, i) 都是小于 x 的,
        //  - 在 [i, n) 都是大于等于 x 的, 经过操作之后, 也就是此时有 n - i 个 x 在数组中
        //
        // 此时的总和为 sum([0, i)) + (n - i) * x
        //  - 如果不足, 则直接返回 false
        //
        // 如果需要凑数的话, 需要 k / x 个 x, 加上一个 k % x
        //  - 也有可能是 k / x - 1 个 x, 加上 a, b 两个数, 满足 (a + b) % x = k % x
        //  - 如果 x 的数量不够的话, 也还是需要在 [0, i) 内凑齐
        return new boolean[0];
    }

    public static void main(String[] args) {
    }

}
