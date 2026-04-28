package weekly.w485.C;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Q3. Design Auction System
 *
 * https://leetcode.cn/contest/weekly-contest-485/problems/design-auction-system/
 *
 * You are asked to design an auction system that manages bids from multiple users in real time.
 *
 * Each bid is associated with a userId, an itemId, and a bidAmount.
 *
 * Implement the AuctionSystem class:
 *
 * AuctionSystem(): Initializes the AuctionSystem object.
 * void addBid(int userId, int itemId, int bidAmount): Adds a new bid for itemId by userId with bidAmount.
 * If the same userId already has a bid on itemId, replace it with the new bidAmount.
 *
 * void updateBid(int userId, int itemId, int newAmount): Updates the existing bid of userId
 * for itemId to newAmount. It is guaranteed that this bid exists.
 *
 * void removeBid(int userId, int itemId): Removes the bid of userId for itemId.
 * It is guaranteed that this bid exists.
 *
 * int getHighestBidder(int itemId): Returns the userId of the highest bidder for itemId.
 * If multiple users have the same highest bidAmount, return the user with the highest userId.
 * If no bids exist for the item, return -1.
 */

public class Solution {

    private static class AuctionSystem {
        private record UserAuction(int userId, int ts, int amount) {}
        private final Map<Integer, Map<Integer, Integer>> userTimestamp = new HashMap<>();
        private final Map<Integer, PriorityQueue<UserAuction>> itemAuctions = new HashMap<>();
        public AuctionSystem() {}

        public void addBid(int userId, int itemId, int bidAmount) {
            updateBid(userId, itemId, bidAmount);
        }

        public void updateBid(int userId, int itemId, int newAmount) {
            int ts = getUserTimestamp(userId).merge(itemId, 1, Integer::sum);
            getItemQueue(itemId).add(new UserAuction(userId, ts, newAmount));
        }

        public void removeBid(int userId, int itemId) {
            updateBid(userId, itemId, -1);
        }

        public int getHighestBidder(int itemId) {
            var q = getItemQueue(itemId);
            while (!q.isEmpty()) {
                var curr = q.peek();
                if (curr.ts == getUserTimestamp(curr.userId).get(itemId) && curr.amount > 0) {
                    return curr.userId;
                }
                q.remove(); // expired and remove it
            }
            return -1;
        }

        private PriorityQueue<UserAuction> getItemQueue(int itemId) {
            return itemAuctions.computeIfAbsent(itemId, k -> new PriorityQueue<>((a, b) -> {
                if (a.amount == b.amount) return b.userId - a.userId;
                return b.amount - a.amount;
            }));
        }

        private Map<Integer, Integer> getUserTimestamp(int userId) {
            return userTimestamp.computeIfAbsent(userId, k -> new HashMap<>());
        }
    }


    public static void main(String[] args) {
        AuctionSystem s = new AuctionSystem();
        s.addBid(8, 10, 8);
        s.removeBid(8, 10);
        assert s.getHighestBidder(10) == -1;
    }

}
