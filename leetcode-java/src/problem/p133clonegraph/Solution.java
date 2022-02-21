package problem.p133clonegraph;

import java.util.ArrayList;
import java.util.List;

/**
 * 133. Clone Graph
 *
 * https://leetcode-cn.com/problems/clone-graph/
 *
 * Given a reference of a node in a connected undirected graph.
 *
 * Return a deep copy (clone) of the graph.
 *
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 */

public class Solution {

    private static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() { val = 0; neighbors = new ArrayList<>(); }
        public Node(int v) { val = v; neighbors = new ArrayList<>(); }
        public Node(int v, List<Node> nodes) { val = v; neighbors = nodes; }
    }

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        Node[] nodes = new Node[101];
        return clone(node, nodes);
    }

    public Node clone(Node node, Node[] nodes) {
        if (nodes[node.val] == null) {
            nodes[node.val] = new Node(node.val);
            for (var neighbor : node.neighbors) {
                nodes[node.val].neighbors.add(clone(neighbor, nodes));
            }
        }
        return nodes[node.val];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().cloneGraph(new Node(1)));
    }

}
