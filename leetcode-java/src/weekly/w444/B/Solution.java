package weekly.w444.B;

import common.Checker;

import java.util.*;

/**
 * 3508. Implement Router
 *
 * https://leetcode.cn/contest/weekly-contest-444/problems/implement-router/
 *
 * Design a data structure that can efficiently manage data packets
 * in a network router. Each data packet consists of the following attributes:
 *
 * source: A unique identifier for the machine that generated the packet.
 * destination: A unique identifier for the target machine.
 * timestamp: The time at which the packet arrived at the router.
 * Implement the Router class:
 *
 * Router(int memoryLimit): Initializes the Router object with a fixed memory limit.
 *
 * memoryLimit is the maximum number of packets the router can store at any given time.
 * If adding a new packet would exceed this limit, the oldest packet must be removed to free up space.
 *
 * bool addPacket(int source, int destination, int timestamp): Adds a packet with the given attributes to the router.
 *
 * A packet is considered a duplicate if another packet with the same source, destination, and timestamp already exists in the router.
 * Return true if the packet is successfully added (i.e., it is not a duplicate); otherwise return false.
 *
 * int[] forwardPacket(): Forwards the next packet in FIFO (First In First Out) order.
 *
 * Remove the packet from storage.
 * Return the packet as an array [source, destination, timestamp].
 * If there are no packets to forward, return an empty array.
 *
 * int getCount(int destination, int startTime, int endTime):
 *
 * Returns the number of packets currently stored in the router (i.e., not yet forwarded) that
 * have the specified destination and have timestamps in the inclusive range [startTime, endTime].
 *
 * Note that queries for addPacket will be made in increasing order of timestamp.
 */

public class Solution {

    // 需要判断 (src, dst, ts) 组是否在队列中, 支持删除以及新增
    // 需要能在所有 dst 中查找 ts 在 [l, r + 1) 的数量
    /** @noinspection unchecked*/
    private static class Router {
        private final int limit;
        private final Map<Integer, Integer> uniqDst = new HashMap<>();
        private final Queue<int[]> queue = new ArrayDeque<>();
        private final List<Integer>[] dstTs = new List[100_001];
        { Arrays.setAll(dstTs, i -> new ArrayList<>()); }
        private final Map<Long, Set<Integer>> uniq = new HashMap<>();
        public Router(int memoryLimit) { limit = memoryLimit; }

        public boolean addPacket(int source, int destination, int timestamp) {
            boolean dup = contains(source, destination, timestamp);
            if (!dup) add(source, destination, timestamp);
            if (queue.size() > limit) remove();
            return !dup;
        }

        public int[] forwardPacket() {
            while (!queue.isEmpty() && !contains(queue.peek())) remove();
            return queue.isEmpty() ? new int[0] : remove();
        }

        public int getCount(int destination, int startTime, int endTime) {
            var dst = dstTs[dstId(destination)];
            return lowerBound(dst, endTime + 1) - lowerBound(dst, startTime);
        }

        private boolean contains(int[] packet) {
            return contains(packet[0], packet[1], packet[2]);
        }

        private boolean contains(int src, int dst, int ts) {
            long key = ((long) src << 32) | dst;
            return uniq.containsKey(key) && uniq.get(key).contains(ts);
        }

        private void add(int src, int dst, int ts) {
            long key = ((long) src << 32) | dst;
            queue.add(new int[]{src, dst, ts});
            dstTs[dstId(dst)].add(ts);
            uniq.computeIfAbsent(key, k -> new HashSet<>()).add(ts);
        }

        private int[] remove() {
            int[] removed = queue.remove();
            long key = ((long) removed[0] << 32) | removed[1];
            uniq.get(key).remove(removed[2]);
            if (uniq.get(key).isEmpty()) {
                uniq.remove(key);
            }
            dstTs[dstId(removed[1])].remove(0);

            return removed;
        }

        private int dstId(int dst) {
            return uniqDst.computeIfAbsent(dst, i -> uniqDst.size());
        }

        private static int lowerBound(List<Integer> list, int target) {
            if (list == null) return 0;

            int l = 0, r = list.size();
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (list.get(mid) < target) l = mid + 1;
                else r = mid;
            }

            return l;
        }
    }

    public static void main(String[] args) {
        Router r1 = new Router(3);
        assert r1.addPacket(1, 4, 90); // 数据包被添加，返回 True。
        assert r1.addPacket(2, 5, 90); // 数据包被添加，返回 True。
        assert !r1.addPacket(1, 4, 90); // 这是一个重复数据包，返回 False。
        assert r1.addPacket(3, 5, 95); // 数据包被添加，返回 True。
        assert r1.addPacket(4, 5, 105); // 数据包被添加，[1, 4, 90] 被移除，因为数据包数量超过限制，返回 True。
        assert Checker.check(r1.forwardPacket(), new int[]{2,5,90}); // 转发数据包 [2, 5, 90] 并将其从路由器中移除。
        assert r1.addPacket(5, 2, 110); // 数据包被添加，返回 True。
        assert r1.getCount(5, 100, 110) == 1; // 唯一目标地址为 5 且时间在 [100, 110] 范围内的数据包是 [4, 5, 105]，返回 1。

        Router r2 = new Router(4);
        assert r2.addPacket(1,5,1);
        assert r2.addPacket(4,3,5);
        assert r2.addPacket(1,3,5);
        assert r2.getCount(5,2,2) == 0;
        assert Checker.check(r2.forwardPacket(), new int[]{1,5,1});

        Router r3 = new Router(3);
        assert r3.addPacket(5, 3, 2);
        assert r3.addPacket(2, 1, 2);
        assert r3.getCount(3,1,2) == 1;
    }

}
