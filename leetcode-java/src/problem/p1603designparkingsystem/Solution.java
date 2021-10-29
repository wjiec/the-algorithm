package problem.p1603designparkingsystem;

/**
 * 1603. Design Parking System
 *
 * https://leetcode-cn.com/problems/design-parking-system/
 *
 * Design a parking system for a parking lot. The parking lot has three kinds of parking spaces:
 * big, medium, and small, with a fixed number of slots for each size.
 *
 * Implement the ParkingSystem class:
 *
 * ParkingSystem(int big, int medium, int small) Initializes object of the ParkingSystem class.
 *  The number of slots for each parking space are given as part of the constructor.
 *
 * bool addCar(int carType) Checks whether there is a parking space of carType for the car
 * that wants to get into the parking lot. carType can be of three kinds:
 *  big, medium, or small, which are represented by 1, 2, and 3 respectively.
 * A car can only park in a parking space of its carType. If there is no space available,
 * return false, else park the car in that size space and return true.
 */

public class Solution {

    static class ParkingSystem {
        private int big, medium, small;

        public ParkingSystem(int big, int medium, int small) {
            this.big = big;
            this.medium = medium;
            this.small = small;
        }

        public boolean addCar(int carType) {
            switch (carType) {
                case 1: return big-- > 0;
                case 2: return medium-- > 0;
                case 3: return small-- > 0;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        ParkingSystem system = new ParkingSystem(1, 1, 0);
        assert system.addCar(1);
        assert system.addCar(2);
        assert !system.addCar(3);
        assert !system.addCar(1);
    }

}
