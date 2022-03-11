package daily.d220311p2049countnodeswiththehighestscore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2049. Count Nodes With the Highest Score
 *
 * https://leetcode-cn.com/problems/count-nodes-with-the-highest-score/
 *
 * There is a binary tree rooted at 0 consisting of n nodes. The nodes are labeled from 0 to n - 1.
 * You are given a 0-indexed integer array parents representing the tree, where parents[i] is the parent of node i.
 * Since node 0 is the root, parents[0] == -1.
 *
 * Each node has a score. To find the score of a node, consider if the node and the edges connected to it were removed.
 * The tree would become one or more non-empty subtrees. The size of a subtree is the number of the nodes in it.
 * The score of the node is the product of the sizes of all those subtrees.
 * Return the number of nodes that have the highest score.
 */

public class Solution {

    private long ans = 0, max = 0, len = 0;
    private final Map<Integer, List<Integer>> children = new HashMap<>();

    public int countHighestScoreNodes(int[] parents) {
        len = parents.length;
        for (int node = 0; node < parents.length; node++) {
            if (parents[node] != -1) {
                if (!children.containsKey(parents[node])) {
                    children.put(parents[node], new ArrayList<>());
                }
                children.get(parents[node]).add(node);
            }
        }
        dfs(0);
        return (int) ans;
    }

    private int dfs(int node) {
        long score = 1, size = len - 1;
        if (children.containsKey(node)) {
            for (var child : children.get(node)) {
                int t = dfs(child);
                score *= t;
                size -= t;
            }
        }

        if (node != 0) {
            score *= size;
        }

        if (score > max) {
            max = score;
            ans = 1;
        } else if (score == max) ans++;

        return (int) (len - size);
    }

    public static void main(String[] args) {
        assert new Solution().countHighestScoreNodes(new int[]{-1,2,0,2,0}) == 3;
        assert new Solution().countHighestScoreNodes(new int[]{-1,2,0}) == 2;
    }

}
