package offer.p40zuixiaodekgeshulcof;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 剑指 Offer 40. 最小的k个数
 *
 * https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
 *
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 */

public class Solution {

    public int[] getLeastNumbers(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((l, r) -> r - l);
        for (var n : arr) {
            queue.add(n);
            if (queue.size() > k) queue.remove();
        }

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) ans[i] = queue.remove();
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().getLeastNumbers(new int[]{0, 1, 2, 1}, 1)));
        System.out.println(Arrays.toString(new Solution().getLeastNumbers(new int[]{8, 7, 6, 5, 4, 3, 2, 1}, 2)));

        System.out.println(Arrays.toString(new Solution().getLeastNumbers(new int[]{3, 2, 1}, 2)));
        System.out.println(Arrays.toString(new Solution().getLeastNumbers(new int[]{0}, 1)));
    }

}
