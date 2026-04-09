package ability.searches;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("DuplicatedCode")
public class BinarySearch {

    // 找到第一个 >= target 的元素的位置, 找不到则返回 len(array)
    public static int lower(int[] array, int target) {
        int l = -1, r = array.length;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (array[mid] < target) l = mid;
            else r = mid;
        }
        return r;
    }

    private static class BurstForce {
        public static int search(int[] array, int target) {
            int ans = 0;
            while (ans < array.length && array[ans] < target) ans++;
            return ans;
        }
    }

    public static void main(String[] args) {
        final int MAX_N = 100_000;

        int[] array = new int[MAX_N];
        Arrays.setAll(array, i -> ThreadLocalRandom.current().nextInt(1000));
        Arrays.sort(array);

        for (int i = 0; i < MAX_N; i++) {
            int target = ThreadLocalRandom.current().nextInt(1200);
            assert lower(array, target) == BurstForce.search(array, target);
        }
    }

}
