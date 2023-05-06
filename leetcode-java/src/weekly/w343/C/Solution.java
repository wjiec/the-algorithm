package weekly.w343.C;

/**
 * 2662. Minimum Cost of a Path With Special Roads
 *
 * https://leetcode.cn/contest/weekly-contest-343/problems/minimum-cost-of-a-path-with-special-roads/
 *
 * You are given an array start where start = [startX, startY] represents
 * your initial position (startX, startY) in a 2D space.
 *
 * You are also given the array target where target = [targetX, targetY] represents
 * your target position (targetX, targetY).
 *
 * The cost of going from a position (x1, y1) to any other position
 * in the space (x2, y2) is |x2 - x1| + |y2 - y1|.
 *
 * There are also some special roads. You are given a 2D array specialRoads
 * where specialRoads[i] = [x1i, y1i, x2i, y2i, costi] indicates that the ith
 * special road can take you from (x1i, y1i) to (x2i, y2i) with a cost equal to costi.
 *
 * You can use each special road any number of times.
 *
 * Return the minimum cost required to go from (startX, startY) to (targetX, targetY).
 */

public class Solution {

    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        int ans = cost(start, target);
        for (var road : specialRoads) {
            if (road[4] > ans) continue;

            int c1 = cost(start[0], start[1], road[0], road[1]);
            int c2 = cost(road[2], road[3], target[0], target[1]);
            if (c1 + c2 + road[4] < ans) {
                ans = Math.min(c1 + c2 + road[4],
                    road[4]
                        + minimumCost(start, new int[]{road[0], road[1]}, specialRoads) // c1
                        + minimumCost(new int[]{road[2], road[3]}, target, specialRoads) // c2
                );
            }
        }
        return ans;
    }

    private int cost(int[] a, int[] b) { return cost(a[0], a[1], b[0], b[1]); }
    private int cost(int x1, int y1, int x2, int y2) { return Math.abs(x1 - x2) + Math.abs(y1 - y2); }

    public static void main(String[] args) {
        assert new Solution().minimumCost(new int[]{25474,6807}, new int[]{79990,8721}, new int[][]{
            {74424,7597,67411,8117,3136},{46064,7220,60104,7608,972},{33205,7150,30376,8154,3486},
            {44572,7790,36216,8659,4435},{62918,7094,55522,8343,7739},{53433,8472,37182,7182,82087},
            {31376,8627,29299,7991,685},{49348,7413,73130,8592,17125},{48717,8215,27956,7087,7385},
            {38429,6954,40336,8520,2489},{37802,8213,73097,7979,27130},{39268,6880,55116,7921,386},
            {39776,8201,58436,7117,14949},{33684,6817,53957,8120,5973},{36715,7531,66803,7722,8950},
            {64405,7204,74454,7764,2059},{43369,8710,77409,7935,2330},{69502,8600,32952,7593,1136},
            {76666,7768,50545,8589,6349},{31742,7687,55294,7078,5371},{60850,7554,33170,6951,80246},
            {39111,7274,45805,8346,275},{69125,7301,31880,7662,30917},{56928,7299,71068,8250,5758},
            {37661,7070,40892,7979,2851},{54680,7023,61464,8294,4291},{58580,7494,63156,8591,1915},
            {67181,7027,57596,7256,6258},{60841,7190,53466,7344,2791},{30685,7016,52496,8390,12865},
            {68609,7965,37663,7088,10586},{64755,8203,77992,7465,465},{77961,8331,44375,6887,34383},
            {39213,7935,36364,6863,450},{72542,7240,68599,7873,57153},{28620,7850,64459,8123,11136},
            {65946,8570,58695,7236,82273},{79972,7478,37365,8579,5187},{48540,6910,41989,7503,6347},
            {27621,7691,58613,7129,7380},{41229,6894,66240,7305,16609},{43994,7132,27156,7938,11555},
            {69017,8213,48685,8252,17310},{71590,7538,29153,7575,39978},{41908,7713,36640,8008,4918},
            {72318,7246,74047,7590,1249},{66831,8238,70061,8067,2120},{36229,7000,75144,7361,30444},
            {26850,7208,44439,7856,84195},{63479,7131,42028,8682,21903},{72985,8569,53458,8571,4050},
            {41222,7968,31019,8604,10152},{61239,8248,64192,8394,1262},{34324,7984,27437,8394,322},
            {28452,8264,43857,8659,12440},{44195,7660,53669,8221,2747},{37302,7992,30056,7646,7103},
            {62931,8303,27403,7411,17272},{60032,7824,74152,6959,55978},{46249,7926,52582,7697,3509},
            {67922,7447,54553,8498,11331},{58842,8478,37041,8324,5227},{53764,8652,58655,7424,4930},
            {39490,7393,34300,7981,1742},{69822,7562,65893,7295,951},
        }) == 18430;

        assert new Solution().minimumCost(new int[]{1, 1}, new int[]{4, 5}, new int[][]{
            {1,2,3,3,2}, {3,4,4,5,1}
        }) == 5;

        assert new Solution().minimumCost(new int[]{3, 2}, new int[]{5, 7}, new int[][]{
            {3,2,3,4,4},{3,3,5,5,5},{3,4,5,6,6}
        }) == 7;
    }

}
