package weekly.w487.C;

import common.PrettyPrinter;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

/**
 * Q3. Design Ride Sharing System
 *
 * https://leetcode.cn/contest/weekly-contest-487/problems/design-ride-sharing-system/
 *
 * A ride sharing system manages ride requests from riders and availability from drivers.
 * Riders request rides, and drivers become available over time. The system should match
 * riders and drivers in the order they arrive.
 *
 * Implement the RideSharingSystem class:
 *
 * RideSharingSystem() Initializes the system.
 *
 * void addRider(int riderId) Adds a new rider with the given riderId.
 *
 * void addDriver(int driverId) Adds a new driver with the given driverId.
 *
 * int[] matchDriverWithRider() Matches the earliest available driver with the earliest
 * waiting rider and removes both of them from the system. Returns an integer array of size 2
 * where result = [driverId, riderId] if a match is made. If no match is available, returns [-1, -1].
 *
 * void cancelRider(int riderId) Cancels the ride request of the rider with the given
 * riderId if the rider exists and has not yet been matched.
 */

public class Solution {

    private static class RideSharingSystem {
        private int timestamp = 0;
        private final TreeMap<Integer, Integer> riders = new TreeMap<>();
        private final TreeMap<Integer, Integer> drivers = new TreeMap<>();
        private final Set<Integer> available = new HashSet<>();
        public RideSharingSystem() {}

        public void addRider(int riderId) { riders.put(timestamp++, riderId); available.add(riderId); }
        public void addDriver(int driverId) { drivers.put(timestamp++, driverId); }
        public void cancelRider(int riderId) { available.remove(riderId); }

        public int[] matchDriverWithRider() {
            while (!riders.isEmpty() && !available.contains(riders.firstEntry().getValue())) riders.pollFirstEntry();
            if (!riders.isEmpty() && !drivers.isEmpty()) return new int[]{drivers.pollFirstEntry().getValue(), riders.pollFirstEntry().getValue()};
            return new int[]{-1, -1};
        }
    }

    public static void main(String[] args) {
        RideSharingSystem s = new RideSharingSystem();
        s.addRider(8);
        s.addDriver(8);
        s.addDriver(6);
        PrettyPrinter.println(s.matchDriverWithRider());
        s.addRider(2);
        s.cancelRider(2);
        PrettyPrinter.println(s.matchDriverWithRider());
    }

}
