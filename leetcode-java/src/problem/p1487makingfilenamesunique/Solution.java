package problem.p1487makingfilenamesunique;

import common.Checker;

import java.util.HashMap;
import java.util.Map;

/**
 * 1487. Making File Names Unique
 *
 * https://leetcode.cn/problems/making-file-names-unique/
 *
 * Given an array of strings names of size n. You will create n folders in your file system
 * such that, at the ith minute, you will create a folder with the name names[i].
 *
 * Since two files cannot have the same name, if you enter a folder name that was
 * previously used, the system will have a suffix addition to its name in the
 * form of (k), where, k is the smallest positive integer such that
 * the obtained name remains unique.
 *
 * Return an array of strings of length n where ans[i] is the actual name the
 * system will assign to the ith folder when you create it.
 */

public class Solution {

    public String[] getFolderNames(String[] names) {
        String[] ans = new String[names.length];
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            if (!map.containsKey(name)) {
                ans[i] = name; map.put(name, 0);
            } else {
                int nextIndex = map.get(name) + 1;
                String nextName = name + "(" + nextIndex + ")";
                while (map.containsKey(nextName)) {
                    nextIndex++;
                    nextName = name + "(" + nextIndex + ")";
                }

                ans[i] = nextName;
                map.put(nextName, 0);
                map.put(name, nextIndex);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().getFolderNames(new String[]{
            "kaido","kaido(1)","kaido","kaido(1)","kaido(2)"
        }), new String[]{"kaido","kaido(1)","kaido(2)","kaido(1)(1)","kaido(2)(1)"});

        assert Checker.check(new Solution().getFolderNames(new String[]{
            "pes","fifa","gta","pes(2019)"
        }), new String[]{"pes","fifa","gta","pes(2019)"});

        assert Checker.check(new Solution().getFolderNames(new String[]{
            "gta","gta(1)","gta","avalon"
        }), new String[]{"gta","gta(1)","gta(2)","avalon"});

        assert Checker.check(new Solution().getFolderNames(new String[]{
            "onepiece","onepiece(1)","onepiece(2)","onepiece(3)","onepiece"
        }), new String[]{"onepiece","onepiece(1)","onepiece(2)","onepiece(3)","onepiece(4)"});

        assert Checker.check(new Solution().getFolderNames(new String[]{
            "wano","wano","wano","wano"
        }), new String[]{"wano","wano(1)","wano(2)","wano(3)"});

        assert Checker.check(new Solution().getFolderNames(new String[]{
            "kaido","kaido(1)","kaido","kaido(1)"
        }), new String[]{"kaido","kaido(1)","kaido(2)","kaido(1)(1)"});
    }

}
