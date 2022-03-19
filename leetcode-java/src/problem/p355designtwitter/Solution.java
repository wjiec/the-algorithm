package problem.p355designtwitter;

import java.util.*;

/**
 * 355. Design Twitter
 *
 * https://leetcode-cn.com/problems/design-twitter/
 *
 * Design a simplified version of Twitter where users can post tweets,
 * follow/unfollow another user, and is able to see the 10 most recent tweets
 * in the user's news feed.
 *
 * Implement the Twitter class:
 *
 * Twitter() Initializes your twitter object.
 *
 * void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId by the user userId.
 * Each call to this function will be made with a unique tweetId.
 *
 * List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs
 * in the user's news feed. Each item in the news feed must be posted by users who
 * the user followed or by the user themself.
 * Tweets must be ordered from most recent to least recent.
 *
 * void follow(int followerId, int followeeId) The user with ID followerId started
 * following the user with ID followeeId.
 *
 * void unfollow(int followerId, int followeeId) The user with ID followerId started
 * unfollowing the user with ID followeeId.
 */

public class Solution {

    private static class Twitter {
        private record Node(int index, int tweetId, Node prev) {}

        private int index = 0;
        private final Map<Integer, Node> tweets = new HashMap<>();
        private final Map<Integer, Set<Integer>> followers = new HashMap<>();
        public Twitter() {}

        public void postTweet(int userId, int tweetId) {
            tweets.put(userId, new Node(index++, tweetId, tweets.get(userId)));
        }

        public List<Integer> getNewsFeed(int userId) {
            PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> b.index - a.index);
            if (tweets.containsKey(userId)) queue.add(tweets.get(userId));
            if (followers.containsKey(userId)) {
                for (var followee : followers.get(userId)) {
                    if (tweets.containsKey(followee)) {
                        queue.add(tweets.get(followee));
                    }
                }
            }

            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < 10 && !queue.isEmpty(); i++) {
                Node curr = queue.remove();
                list.add(curr.tweetId);
                if (curr.prev != null) queue.add(curr.prev);
            }

            return list;
        }

        public void follow(int followerId, int followeeId) {
            if (!followers.containsKey(followerId)) {
                followers.put(followerId, new HashSet<>());
            }
            followers.get(followerId).add(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            if (followers.containsKey(followerId)) {
                followers.get(followerId).remove(followeeId);
            }
        }
    }

    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5);
        assert twitter.getNewsFeed(1).equals(List.of(5));
        twitter.follow(1, 2);
        twitter.postTweet(2, 6);
        assert twitter.getNewsFeed(1).equals(List.of(6,5));
        twitter.unfollow(1, 2);
        assert twitter.getNewsFeed(1).equals(List.of(5));
    }

}
