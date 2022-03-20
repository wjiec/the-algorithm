package daily.d220320pthetimewhenthenetworkbecomesidle;

import java.util.*;

/**
 * 2039. The Time When the Network Becomes Idle
 *
 * https://leetcode-cn.com/problems/the-time-when-the-network-becomes-idle/
 *
 * There is a network of n servers, labeled from 0 to n - 1. You are given a 2D integer array edges,
 * where edges[i] = [ui, vi] indicates there is a message channel between servers ui and vi,
 * and they can pass any number of messages to each other directly in one second.
 *
 * You are also given a 0-indexed integer array patience of length n.
 *
 * All servers are connected, i.e., a message can be passed from one server to any
 * other server(s) directly or indirectly through the message channels.
 *
 * The server labeled 0 is the master server. The rest are data servers. Each data server needs to send
 * its message to the master server for processing and wait for a reply.
 * Messages move between servers optimally, so every message takes the least amount of time to
 * arrive at the master server. The master server will process all newly arrived messages instantly and
 * send a reply to the originating server via the reversed path the message had gone through.
 *
 * At the beginning of second 0, each data server sends its message to be processed. Starting from second 1,
 * at the beginning of every second, each data server will check if it has received a reply to
 * the message it sent (including any newly arrived replies) from the master server:
 *
 * If it has not, it will resend the message periodically. The data server i will resend the message
 * every patience[i] second(s), i.e., the data server i will resend the message if patience[i] second(s)
 * have elapsed since the last time the message was sent from this server.
 *
 * Otherwise, no more resending will occur from this server.
 * The network becomes idle when there are no messages passing between servers or arriving at servers.
 *
 * Return the earliest second starting from which the network becomes idle.
 */

public class Solution {

    public int networkBecomesIdle(int[][] edges, int[] patience) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (var edge : edges) {
            if (!map.containsKey(edge[0])) {
                map.put(edge[0], new HashSet<>());
            }
            map.get(edge[0]).add(edge[1]);

            if (!map.containsKey(edge[1])) {
                map.put(edge[1], new HashSet<>());
            }
            map.get(edge[1]).add(edge[0]);
        }

        int ans = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        int[] distances = new int[patience.length];
        for (queue.add(new int[]{0, 0}); !queue.isEmpty(); ) {
            int[] curr = queue.remove();
            for (var next : map.get(curr[0])) {
                if (distances[next] == 0 && next != 0) {
                    distances[next] = curr[1] + 1;
                    queue.add(new int[]{next, curr[1] + 1});

                    int time = patience[next] * ((2 * distances[next] - 1) / patience[next]) + 2 * distances[next] + 1;
                    ans = Math.max(ans, time);
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().networkBecomesIdle(new int[][]{{0,1},{1,2}}, new int[]{0,2,1}) == 8;
        assert new Solution().networkBecomesIdle(new int[][]{{0,1},{0,2},{1,2}}, new int[]{0,10,10}) == 3;
    }

}
