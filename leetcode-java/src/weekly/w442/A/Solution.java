package weekly.w442.A;

/**
 * 3492. Maximum Containers on a Ship
 *
 * https://leetcode.cn/contest/weekly-contest-442/problems/maximum-containers-on-a-ship/
 *
 * You are given a positive integer n representing an n x n cargo deck on a ship.
 * Each cell on the deck can hold one container with a weight of exactly w.
 *
 * However, the total weight of all containers, if loaded onto the deck, must not
 * exceed the ship's maximum weight capacity, maxWeight.
 *
 * Return the maximum number of containers that can be loaded onto the ship.
 */

public class Solution {

    public int maxContainers(int n, int w, int maxWeight) {
        return Math.max(n * n, maxWeight / w);
    }

    public static void main(String[] args) {
    }

}
