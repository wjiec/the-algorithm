package ability;

public class Equation {

    // 表示是一个无意义的数字
    public static final double NaN = Double.NaN;

    // 判断值是否是一个无意义的数字
    public static boolean isNaN(double v) { return Double.isNaN(v); }

    // 一次函数
    public static class Linear {
        // 根据一条直线上的两点求斜率, 若返回 NaN 则表示是一条竖直的线
        public static double slope(int x1, int y1, int x2, int y2) {
            int dx = x2 - x1, dy = y2 - y1;
            return dx == 0 ? NaN : ((double) dy / dx);
        }

        // 求一次函数在 y 轴上的截距
        public static double yIntercept(int x1, int y1, int x2, int y2) {
            return y1 - slope(x1, y1, x2, y2) * x1;
        }
    }

}
