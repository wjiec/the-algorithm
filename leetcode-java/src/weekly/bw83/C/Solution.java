package weekly.bw83.C;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Solution {

    private static class NumberContainers {
        private final Map<Integer, Integer> map = new HashMap<>();
        private final Map<Integer, TreeSet<Integer>> idx = new HashMap<>();
        public NumberContainers() {}

        public void change(int index, int number) {
            if (map.containsKey(index)) {
                int v = map.get(index);
                idx.get(v).remove(index);
            }

            map.put(index, number);
            idx.computeIfAbsent(number, v -> new TreeSet<>()).add(index);
        }

        public int find(int number) {
            TreeSet<Integer> s = idx.get(number);
            if (s != null && !s.isEmpty()) {
                return s.first();
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        NumberContainers nc = new NumberContainers();
        assert nc.find(10) == -1;
        nc.change(2, 10);
        nc.change(1, 10);
        nc.change(3, 10);
        nc.change(5, 10);
        assert nc.find(10) == 1;
        nc.change(1, 20);
        assert nc.find(10) == 2;
    }

}
