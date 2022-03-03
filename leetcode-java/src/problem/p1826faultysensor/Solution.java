package problem.p1826faultysensor;

/**
 * 1826. Faulty Sensor
 *
 * https://leetcode-cn.com/problems/faulty-sensor/
 *
 * An experiment is being conducted in a lab. To ensure accuracy,
 * there are two sensors collecting data simultaneously.
 *
 * You are given two arrays sensor1 and sensor2, where sensor1[i] and sensor2[i]
 * are the ith data points collected by the two sensors.
 *
 * However, this type of sensor has a chance of being defective
 * which causes exactly one data point to be dropped.
 *
 * After the data is dropped, all the data points to the right of the dropped
 * data are shifted one place to the left, and the last data point is replaced
 * with some random value. It is guaranteed that this random value
 * will not be equal to the dropped value.
 *
 * For example, if the correct data is [1,2,3,4,5] and 3 is dropped,
 * the sensor could return [1,2,4,5,7] (the last position can be any value, not just 7).
 *
 * We know that there is a defect in at most one of the sensors.
 * Return the sensor number (1 or 2) with the defect.
 *
 * If there is no defect in either sensor or if it is impossible
 * to determine the defective sensor, return -1.
 */

public class Solution {

    public int badSensor(int[] sensor1, int[] sensor2) {
        int i = 0, n = sensor1.length;
        while (i < n && sensor1[i] == sensor2[i]) i++;
        if (i == n || i == n - 1) return -1;

        boolean a = isBad(sensor1, sensor2, i);
        boolean b = isBad(sensor2, sensor1, i);

        if (a && b) return -1;
        return a ? 1 : 2;
    }

    private boolean isBad(int[] a, int[] b, int i) {
        while (i < a.length - 1 && a[i] == b[i + 1]) i++;
        return i >= a.length - 1;
    }

    public static void main(String[] args) {
        assert new Solution().badSensor(new int[]{2,3,4,5}, new int[]{2,1,3,4}) == 1;
        assert new Solution().badSensor(new int[]{2,2,2,2,2}, new int[]{2,2,2,2,5}) == -1;
        assert new Solution().badSensor(new int[]{2,3,2,2,3,2}, new int[]{2,3,2,3,2,7}) == 2;
    }

}
