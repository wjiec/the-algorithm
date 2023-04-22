package season.spring2023.A;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. 补给马车
 *
 * https://leetcode.cn/contest/season/2023-spring/problems/hqCnmP/
 *
 * 远征队即将开启未知的冒险之旅，不过在此之前，将对补给车队进行最后的检查。supplies[i] 表示编号为 i 的补给马车装载的物资数量。
 * 考虑到车队过长容易被野兽偷袭，他们决定将车队的长度变为原来的一半（向下取整），计划为：
 *
 * 找出车队中 物资之和最小 两辆 相邻 马车，将它们车辆的物资整合为一辆。若存在多组物资之和相同的马车，则取编号最小的两辆马车进行整合；
 * 重复上述操作直到车队长度符合要求。
 * 请返回车队长度符合要求后，物资的分布情况。
 */

public class Solution {

    public int[] supplyWagon(int[] supplies) {
        List<Integer> list = new ArrayList<>();
        for (var v : supplies) list.add(v);

        while (list.size() > supplies.length / 2) {
            int mi = 0, mx = Integer.MAX_VALUE;
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i) + list.get(i - 1) < mx) {
                    mi = i - 1;
                    mx = list.get(i) + list.get(i - 1);
                }
            }
            list.set(mi, list.get(mi) + list.get(mi + 1));
            list.remove(mi + 1);
        }
        return list.stream().mapToInt(v -> v).toArray();
    }

    public static void main(String[] args) {
    }

}
