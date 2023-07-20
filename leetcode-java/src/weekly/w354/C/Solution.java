package weekly.w354.C;

import ability.Ability;

import java.util.List;
import java.util.Set;

public class Solution {

    public int minimumIndex(List<Integer> nums) {
        Ability.Frequency<Integer> left = new Ability.Frequency<>();
        Ability.Frequency<Integer> right = new Ability.Frequency<>();
        for (var v : nums) right.push(v);

        for (int i = 0; i < nums.size() - 1; i++) {
            left.push(nums.get(i));
            right.remove(nums.get(i));

            Set<Integer> ls = left.lastSet(), rs = right.lastSet();
            if (ls.size() == 1 && rs.size() == 1) {
                if (ls.containsAll(rs)) return nums.get(i);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
    }

}
