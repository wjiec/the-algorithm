package problem.p1054distantbarcodes;

import common.PrettyPrinter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 1054. Distant Barcodes
 *
 * https://leetcode.cn/problems/distant-barcodes/
 *
 * In a warehouse, there is a row of barcodes, where the ith barcode is barcodes[i].
 *
 * Rearrange the barcodes so that no two adjacent barcodes are equal. You may return
 * any answer, and it is guaranteed an answer exists.
 */

public class Solution {

    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> map = new HashMap<>();
        for (var code : barcodes) map.merge(code, 1, Integer::sum);

        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        queue.addAll(map.entrySet());

        int idx = 0;
        int[] ans = new int[barcodes.length];
        while (!queue.isEmpty()) {
            Map.Entry<Integer, Integer> a = queue.poll();
            Map.Entry<Integer, Integer> b = queue.poll();

            ans[idx++] = a.getKey();
            if (b != null) ans[idx++] = b.getKey();

            if (a.getValue() != 1) {
                a.setValue(a.getValue() - 1);
                queue.add(a);
            }

            if (b != null && b.getValue() != 1) {
                b.setValue(b.getValue() - 1);
                queue.add(b);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().rearrangeBarcodes(new int[]{1,1,1,2,2,2}));
        PrettyPrinter.println(new Solution().rearrangeBarcodes(new int[]{1,1,1,1,2,2,3,3}));
    }

}
