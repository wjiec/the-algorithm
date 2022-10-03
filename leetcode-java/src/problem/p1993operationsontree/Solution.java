package problem.p1993operationsontree;

import java.util.*;

/**
 * 1993. Operations on Tree
 *
 * https://leetcode.cn/problems/operations-on-tree/
 *
 * You are given a tree with n nodes numbered from 0 to n - 1 in the form of a parent
 * array parent where parent[i] is the parent of the ith node. The root of the tree is
 * node 0, so parent[0] = -1 since it has no parent. You want to design a data structure
 * that allows users to lock, unlock, and upgrade nodes in the tree.
 *
 * The data structure should support the following functions:
 *
 * Lock: Locks the given node for the given user and prevents other users from locking
 * the same node. You may only lock a node using this function if the node is unlocked.
 * Unlock: Unlocks the given node for the given user. You may only unlock a node using
 * this function if it is currently locked by the same user.
 * Upgrade: Locks the given node for the given user and unlocks all of its descendants
 * regardless of who locked it. You may only upgrade a node if all 3 conditions are true:
 *
 * The node is unlocked,
 * It has at least one locked descendant (by any user), and
 * It does not have any locked ancestors.
 *
 * Implement the LockingTree class:
 *
 * LockingTree(int[] parent) initializes the data structure with the parent array.
 *
 * lock(int num, int user) returns true if it is possible for the user with id user to
 * lock the node num, or false otherwise. If it is possible, the node num will become
 * locked by the user with id user.
 *
 * unlock(int num, int user) returns true if it is possible for the user with id user
 * to unlock the node num, or false otherwise. If it is possible, the node num will
 * become unlocked.
 *
 * upgrade(int num, int user) returns true if it is possible for the user with id user
 * to upgrade the node num, or false otherwise. If it is possible, the node num will be
 * upgraded.
 */

public class Solution {

    private static class LockingTree {
        private final int[] parent;
        private final Map<Integer, Set<Integer>> children;
        private final int[] locked;
        public LockingTree(int[] parent) {
            this.parent = parent;
            children = new HashMap<>();
            locked = new int[parent.length];
            for (int i = 0; i < parent.length; i++) {
                children.computeIfAbsent(parent[i], v -> new HashSet<>())
                    .add(i);
            }
        }

        public boolean lock(int num, int user) {
            if (locked[num] != 0) return false;
            locked[num] = user;
            return true;
        }

        public boolean unlock(int num, int user) {
            if (locked[num] != user) return false;
            locked[num] = 0;
            return true;
        }

        public boolean upgrade(int num, int user) {
            if (locked[num] != 0) return false;
            if (parentUnlocked(num) && childrenLocked(num)) {
                locked[num] = user;
                unlockChildren(num);
                return true;
            }
            return false;
        }

        private boolean childrenLocked(int num) {
            Set<Integer> set = children.get(num);
            if (set != null) {
                for (var c : set) {
                    if (locked[c] != 0) return true;
                    if (childrenLocked(c)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean parentUnlocked(int num) {
            int p = parent[num];
            while (p != -1) {
                if (locked[p] != 0) return false;
                p = parent[p];
            }
            return true;
        }

        private void unlockChildren(int num) {
            Queue<Integer> queue = new ArrayDeque<>();
            for (queue.add(num); !queue.isEmpty(); ) {
                int curr = queue.remove();
                if (children.containsKey(curr)) {
                    for (var c : children.get(curr)) {
                        if (c > 0) {
                            locked[c] = 0;
                            queue.add(c);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        LockingTree lockingTree = new LockingTree(new int[]{-1, 0, 0, 1, 1, 2, 2});
        assert lockingTree.lock(2, 2);
        assert !lockingTree.unlock(2, 3);
        assert lockingTree.unlock(2, 2);
        assert lockingTree.lock(4, 5);
        assert lockingTree.upgrade(0, 1);
        assert !lockingTree.lock(0, 1);
    }

}
