package aoc2017.day7;

import common.Node;

import java.util.*;

public class ProgramTreeMapper {

    public Map<String, Node<Integer>> buildTree(String[] input) {

        Map<String, Node<Integer>> tree = new HashMap<>();

        for (String line : input) {

            String[] family = line.split(" -> ");
            String parent = family[0];

            String name = parent.substring(0, parent.indexOf(' '));
            int weight = Integer.parseInt(parent.substring(parent.indexOf('(') + 1, parent.indexOf(')')));

            Node<Integer> parentNode = tree.get(name) != null ? tree.get(name) : new Node<>(name);
            parentNode.setData(weight);

            if (family.length > 1) {
                String[] children = family[1].split(", ");

                for (String child : children) {

                    Node<Integer> childNode = tree.get(child) != null ? tree.get(child) : new Node<>(child);
                    childNode.setParent(parentNode);

                    tree.put(child, childNode);
                }
            }
            tree.put(name, parentNode);
        }
        return tree;
    }

    public String findRoot(Map<String, Node<Integer>> tree) {

        for (Map.Entry<String, Node<Integer>> node : tree.entrySet()) {

            if (node.getValue().isRoot()) {
                return node.getKey();
            }
        }
        return "Root not found";
    }

    public List<String> findUnbalancedSubTrees(Map<String, Node<Integer>> tree) {

        List<String> unbalancedTrees = new ArrayList<>();

        for (Map.Entry<String, Node<Integer>> node : tree.entrySet()) {

            if (!node.getValue().getChildren().isEmpty()) {
                Map<String, Integer> childWeights = new HashMap<>();

                for (Node<Integer> child : node.getValue().getChildren()) {
                    childWeights.put(child.getId(), getSubtreeWeight(child));
                }

                Map.Entry<String, Integer> maxEntry = Collections.max(childWeights.entrySet(),
                        Comparator.comparing(Map.Entry::getValue));
                int maxStack = maxEntry.getValue();

                Map.Entry<String, Integer> minEntry = Collections.min(childWeights.entrySet(),
                        Comparator.comparing(Map.Entry::getValue));
                int minStack = minEntry.getValue();

                if (maxStack != minStack) {

                    int requiredWeight = tree.get(maxEntry.getKey()).getData() - (maxStack - minStack);
                    unbalancedTrees.add("Node [" + maxEntry.getKey() + "] should weigh [" + requiredWeight + "]");
                }
            }
        }
        return unbalancedTrees;
    }

    public int getSubtreeWeight(Node<Integer> node) {

        int childWeight = 0;
        for (Node<Integer> child : node.getChildren()) {

            childWeight += getSubtreeWeight(child);
        }

        return node.getData() + childWeight;
    }
}
