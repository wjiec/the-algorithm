package weekly.w317.B;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 6221. Most Popular Video Creator
 *
 * https://leetcode.cn/contest/weekly-contest-317/problems/most-popular-video-creator/
 *
 * You are given two string arrays creators and ids, and an integer array views, all of length n.
 * The ith video on a platform was created by creator[i], has an id of ids[i], and has views[i] views.
 *
 * The popularity of a creator is the sum of the number of views on all of the creator's videos.
 * Find the creator with the highest popularity and the id of their most viewed video.
 *
 * If multiple creators have the highest popularity, find all of them.
 * If multiple videos have the highest view count for a creator, find the lexicographically smallest id.
 * Return a 2D array of strings answer where answer[i] = [creatori, idi] means that creatori has the
 * highest popularity and idi is the id of their most popular video.
 *
 * The answer can be returned in any order.
 */

public class Solution {

    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        Map<String, Integer> maximum = new HashMap<>();
        Map<String, Integer> popularity = new HashMap<>();
        for (int i = 0; i < creators.length; i++) {
            popularity.merge(creators[i], views[i], Integer::sum);
            if (!maximum.containsKey(creators[i])) {
                maximum.put(creators[i], i);
            } else {
                int prev = maximum.get(creators[i]);
                if (views[prev] < views[i]) {
                    maximum.put(creators[i], i);
                } else if (views[prev] == views[i]) {
                    if (ids[i].compareTo(ids[prev]) < 0) {
                        maximum.put(creators[i], i);
                    }
                }
            }
        }

        int max = 0;
        List<List<String>> ans = new ArrayList<>();
        for (var kv : popularity.entrySet()) {
            if (kv.getValue() > max) {
                max = kv.getValue();
                ans.clear();
                ans.add(List.of(kv.getKey(), ids[maximum.get(kv.getKey())]));
            } else if (kv.getValue() == max) {
                ans.add(List.of(kv.getKey(), ids[maximum.get(kv.getKey())]));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
