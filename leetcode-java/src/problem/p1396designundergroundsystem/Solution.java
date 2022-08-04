package problem.p1396designundergroundsystem;

import common.Checker;

import java.util.HashMap;
import java.util.Map;

/**
 * 1396. Design Underground System
 *
 * https://leetcode.cn/problems/design-underground-system/
 *
 * An underground railway system is keeping track of customer travel times between different stations.
 * They are using this data to calculate the average time it takes to travel from one station to another.
 *
 * Implement the UndergroundSystem class:
 *
 * void checkIn(int id, string stationName, int t)
 * A customer with a card ID equal to id, checks in at the station stationName at time t.
 * A customer can only be checked into one place at a time.
 * void checkOut(int id, string stationName, int t)
 * A customer with a card ID equal to id, checks out from the station stationName at time t.
 * double getAverageTime(string startStation, string endStation)
 * Returns the average time it takes to travel from startStation to endStation.
 * The average time is computed from all the previous traveling times from startStation to endStation
 * that happened directly, meaning a check in at startStation followed by a check out from endStation.
 * The time it takes to travel from startStation to endStation may be different from the time it takes
 * to travel from endStation to startStation.
 * There will be at least one customer that has traveled from startStation to endStation
 * before getAverageTime is called.
 * You may assume all calls to the checkIn and checkOut methods are consistent. If a customer
 * checks in at time t1 then checks out at time t2, then t1 < t2.
 * All events happen in chronological order.
 */

public class Solution {


    private static class UndergroundSystem {
        private record Checkpoint(String station, int time) {}

        private final Map<Integer, Checkpoint> checks = new HashMap<>();
        private final Map<String, Map<String, int[]>> map = new HashMap<>();
        public UndergroundSystem() {}

        public void checkIn(int id, String stationName, int t) {
            checks.put(id, new Checkpoint(stationName, t));
        }

        public void checkOut(int id, String stationName, int t) {
            Checkpoint cp = checks.remove(id);
            int[] stat = map.computeIfAbsent(cp.station, v -> new HashMap<>())
                .computeIfAbsent(stationName, v -> new int[2]);
            stat[0] += t - cp.time; stat[1]++;
        }

        public double getAverageTime(String startStation, String endStation) {
            if (map.containsKey(startStation)) {
                int[] stat = map.get(startStation).get(endStation);
                if (stat != null) {
                    return ((double) stat[0]) / stat[1];
                }
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        UndergroundSystem undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(45, "Leyton", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);
        undergroundSystem.checkOut(45, "Waterloo", 15);
        undergroundSystem.checkOut(27, "Waterloo", 20);
        undergroundSystem.checkOut(32, "Cambridge", 22);
        assert Checker.check(undergroundSystem.getAverageTime("Paradise", "Cambridge"), 14.0);
        assert Checker.check(undergroundSystem.getAverageTime("Leyton", "Waterloo"), 11.0);
        undergroundSystem.checkIn(10, "Leyton", 24);
        assert Checker.check(undergroundSystem.getAverageTime("Leyton", "Waterloo"), 11.0);
        undergroundSystem.checkOut(10, "Waterloo", 38);
        assert Checker.check(undergroundSystem.getAverageTime("Leyton", "Waterloo"), 12.0);

        undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(10, "Leyton", 3);
        undergroundSystem.checkOut(10, "Paradise", 8);
        assert Checker.check(undergroundSystem.getAverageTime("Leyton", "Paradise"), 5.0);
        undergroundSystem.checkIn(5, "Leyton", 10);
        undergroundSystem.checkOut(5, "Paradise", 16);
        assert Checker.check(undergroundSystem.getAverageTime("Leyton", "Paradise"), 5.5);
        undergroundSystem.checkIn(2, "Leyton", 21);
        undergroundSystem.checkOut(2, "Paradise", 30);
        assert Checker.check(undergroundSystem.getAverageTime("Leyton", "Paradise"), 6.66667);
    }

}
