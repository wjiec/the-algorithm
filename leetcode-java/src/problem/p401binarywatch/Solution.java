package problem.p401binarywatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 401. Binary Watch
 *
 * https://leetcode-cn.com/problems/binary-watch/
 *
 * A binary watch has 4 LEDs on the top which represent the hours (0-11),
 * and the 6 LEDs on the bottom represent the minutes (0-59).
 * Each LED represents a zero or one, with the least significant bit on the right.
 */

public class Solution {

    public List<String> readBinaryWatch(int turnedOn) {
        if (turnedOn == 0) {
            return List.of("0:00");
        }

        List<String> times = new ArrayList<>();
        for (int i = 0; i <= turnedOn; i++) {
            times.addAll(binaryWatch(i, turnedOn - i));
        }
        return times;
    }

    private List<String> binaryWatch(int hour, int minute) {
        List<String> times = new ArrayList<>();
        for (var h : selectHours(hour)) {
            for (var m : selectMinutes(minute)) {
                times.add(h + ":" + m);
            }
        }
        return times;
    }

    private List<String> selectHours(int n) {
        switch (n) {
            case 0:
                return List.of("0");
            case 1:
                return List.of("1", "2", "4", "8");
            case 2:
                return List.of("3", "5", "9", "6", "10");
            case 3:
                return List.of("7", "11");
            default:
                return Collections.emptyList();
        }
    }

    private List<String> selectMinutes(int n) {
        switch (n) {
            case 0:
                return List.of("00");
            case 1:
                return List.of("01", "02", "04", "08", "16", "32");
            case 2:
                return List.of(
                    "03", "05", "09", "17", "33",
                    "06", "10", "18", "34",
                    "12", "20", "36",
                    "24", "40",
                    "48"
                );
            case 3:
                return List.of(
                    "07", "11", "19", "35",
                    "13", "21", "37",
                    "25", "41",
                    "49",

                    "14", "22", "38",
                    "26", "42",
                    "50",

                    "28", "44",
                    "52",

                    "56"
                );
            case 4:
                return List.of(
                    "15", "23", "39",
                    "27", "43",
                    "51",

                    "29", "45",
                    "53",

                    "57",

                    "30", "46",
                    "54",

                    "58"
                );
            case 5:
                return List.of(
                    "31", "47",
                    "55",

                    "59"
                );
            default:
                return Collections.emptyList();
        }
    }

    public static void main(String[] args) {
        var minutes = new int[]{1,2,4,8,16,32};
        for (int i = 0; i < minutes.length; i++) {
            System.out.printf("\"%02d\", ", minutes[i]);
        }

        System.out.println();
        for (int i = 0; i < minutes.length; i++) {
            for (int j = i + 1; j < minutes.length; j++) {
                System.out.printf("\"%02d\", ", minutes[i] + minutes[j]);
            }
            System.out.println();
        }

        System.out.println();
        for (int i = 0; i < minutes.length; i++) {
            for (int j = i + 1; j < minutes.length; j++) {
                for (int k = j + 1; k < minutes.length; k++) {
                    System.out.printf("\"%02d\", ", minutes[i] + minutes[j] + minutes[k]);
                }
                System.out.println();
            }
        }

        System.out.println();
        for (int i = 0; i < minutes.length; i++) {
            for (int j = i + 1; j < minutes.length; j++) {
                for (int k = j + 1; k < minutes.length; k++) {
                    for (int l = k + 1; l < minutes.length; l++) {
                        System.out.printf("\"%02d\", ", minutes[i] + minutes[j] + minutes[k] + minutes[l]);
                    }
                    System.out.println();
                }
            }
        }

        System.out.println();
        for (int i = 0; i < minutes.length; i++) {
            for (int j = i + 1; j < minutes.length; j++) {
                for (int k = j + 1; k < minutes.length; k++) {
                    for (int l = k + 1; l < minutes.length; l++) {
                        for (int m = l + 1; m < minutes.length; m++) {
                            System.out.printf("\"%02d\", ", minutes[i] + minutes[j] + minutes[k] + minutes[l] + minutes[m]);
                        }
                        System.out.println();
                    }
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            System.out.printf("%d: %s\n", i, new Solution().readBinaryWatch(i));
        }
    }

}
