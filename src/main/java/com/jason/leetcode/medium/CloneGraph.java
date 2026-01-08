package com.jason.leetcode.medium;

import com.jason.leetcode.common.Node;

import java.util.HashMap;
import java.util.Map;

public class CloneGraph {
    public Node cloneGraph(Node node) {
        return cloneGraph(node, new HashMap<>());
    }

    public Node cloneGraph(Node node, Map<Integer, Node> cloneMap) {
        if (node == null) {
            return null;
        } else if (cloneMap.containsKey(node.val)) {
            return cloneMap.get(node.val);
        }

        Node clone = new Node(node.val);
        cloneMap.put(node.val, clone);
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor, cloneMap));
        }
        return clone;
    }
}
