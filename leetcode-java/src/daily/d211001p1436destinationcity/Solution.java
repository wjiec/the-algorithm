package daily.d211001p1436destinationcity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1436. Destination City
 *
 * https://leetcode-cn.com/problems/destination-city/
 *
 * You are given the array paths, where paths[i] = [cityAi, cityBi] means there
 * exists a direct path going from cityAi to cityBi.
 *
 * Return the destination city, that is, the city without any path outgoing to another city.
 *
 * It is guaranteed that the graph of paths forms a line without any loop,
 * therefore, there will be exactly one destination city.
 */

public class Solution {

    public String destCity(List<List<String>> paths) {
        Set<String> uniq = new HashSet<>();
        for (var path : paths) uniq.add(path.get(0));
        for (var path : paths) {
            if (!uniq.contains(path.get(1))) return path.get(1);
        }
        return "";
    }

    public static void main(String[] args) {
        assert new Solution().destCity(List.of(List.of("a", "b"))).equals("b");
    }

}
