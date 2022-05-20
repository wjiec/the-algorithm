package problem.p841keysandrooms;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * 841. Keys and Rooms
 *
 * https://leetcode.cn/problems/keys-and-rooms/
 *
 * There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0.
 * Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.
 *
 * When you visit a room, you may find a set of distinct keys in it. Each key has a number on it,
 * denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.
 *
 * Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i,
 * return true if you can visit all the rooms, or false otherwise.
 */

public class Solution {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int count = 0;
        boolean[] visited = new boolean[rooms.size()];
        Queue<Integer> queue = new ArrayDeque<>();
        for (queue.add(0); !queue.isEmpty(); ) {
            int curr = queue.remove();
            if (!visited[curr]) count++;

            visited[curr] = true;
            for (int next : rooms.get(curr)) {
                if (!visited[next]) {
                    queue.add(next);
                }
            }
        }
        return count == rooms.size();
    }

    public static void main(String[] args) {
        assert new Solution().canVisitAllRooms(List.of(List.of(1), List.of(2), List.of(3), List.of()));
        assert !new Solution().canVisitAllRooms(List.of(List.of(1,3), List.of(3,0,1), List.of(2), List.of(0)));
    }

}
