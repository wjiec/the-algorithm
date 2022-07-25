package problem.p1311getwatchedvideosbyyourfriends;

import common.Checker;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 1311. Get Watched Videos by Your Friends
 *
 * https://leetcode.cn/problems/get-watched-videos-by-your-friends/
 *
 * There are n people, each person has a unique id between 0 and n-1. Given the arrays
 * watchedVideos and friends, where watchedVideos[i] and friends[i] contain the list of
 * watched videos and the list of friends respectively for the person with id = i.
 *
 * Level 1 of videos are all watched videos by your friends, level 2 of videos are
 * all watched videos by the friends of your friends and so on. In general, the level
 * k of videos are all watched videos by people with the shortest path exactly equal
 * to k with you. Given your id and the level of videos, return the list of videos
 * ordered by their frequencies (increasing). For videos with the same frequency order
 * them alphabetically from least to greatest.
 */

public class Solution {

    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        boolean[] visited = new boolean[watchedVideos.size()];
        visited[id] = true;

        // [id, level]
        Queue<int[]> queue = new ArrayDeque<>();
        Map<String, Integer> map = new HashMap<>();
        for (queue.add(new int[]{id, 0}); !queue.isEmpty(); ) {
            int[] curr = queue.remove();
            for (var friend : friends[curr[0]]) {
                if (!visited[friend]) {
                    visited[friend] = true;
                    if (curr[1] + 1 == level) {
                        for (var video : watchedVideos.get(friend)) {
                            map.merge(video, 1, Integer::sum);
                        }
                    } else queue.add(new int[]{friend, curr[1] + 1});
                }
            }
        }


        return map.entrySet().stream()
            .sorted(Comparator
                .comparingInt(Map.Entry<String, Integer>::getValue)
                .thenComparing(Map.Entry::getKey))
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().watchedVideosByFriends(
            List.of(List.of("A","B"), List.of("C"), List.of("B","C"), List.of("D")),
            new int[][]{{1,2}, {0,3}, {0,3}, {1,2}},
            0, 1
        ), List.of("B", "C"));

        assert Checker.check(new Solution().watchedVideosByFriends(
            List.of(List.of("A","B"), List.of("C"), List.of("B","C"), List.of("D")),
            new int[][]{{1,2}, {0,3}, {0,3}, {1,2}},
            0, 2
        ), List.of("D"));
    }

}
