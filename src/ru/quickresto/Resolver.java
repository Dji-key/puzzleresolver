package ru.quickresto;

import java.util.*;

/**
 * Implementation of {@link PuzzleResolver}
 */
public class Resolver implements PuzzleResolver {

    private final int[] SOLUTION = {1, 2, 3, 4, 0, 5, 6, 7};
    private final int[][] GRAPH = {{1, 2}, {0, 2, 3}, {0, 1, 5}, {1, 4, 6}, {3, 5}, {2, 4, 7}, {3, 7}, {5, 6}};

    @Override
    public int[] resolve(int[] start) {
        if (start.length != 8) {
            throw new IllegalArgumentException("Incorrect input array");
        }

        LinkedList<Node> open = new LinkedList<>();
        Set<Node> closed = new HashSet<>();

        Node startNode = new Node().setState(start).setHeuristic(calculateHeuristic(start));
        open.add(startNode);

        while (!open.isEmpty()) {
            Node current = popLowestCostNode(open);
            if (isSolution(current)) {
                return getTrace(startNode, current);
            }
            open.addAll(getUncheckedNeighbors(current, closed));
            closed.add(current);
        }

        return new int[0];
    }

    private Collection<Node> getUncheckedNeighbors(Node parent, Set<Node> closed) {
        LinkedList<Node> neighbors = new LinkedList<>();
        int indexOfZero = -1;
        for (int i = 0; i < 8; i++) {
            if (parent.state[i] == 0) {
                indexOfZero = i;
                break;
            }
        }
        if (indexOfZero == -1) {
            throw new IllegalArgumentException("Where is free place?");
        }

        for (int index : GRAPH[indexOfZero]) {
            int[] state = parent.state.clone();
            state[indexOfZero] = state[index];
            state[index] = 0;
            Node node = new Node(parent, state, state[indexOfZero], calculateHeuristic(state), parent.distance + 1);
            if (!closed.contains(node)) {
                neighbors.add(node);
            }
        }
        return neighbors;
    }

    private int calculateHeuristic(int[] state) {
        int heuristic = 0;
        for (int i = 0; i < state.length; i++) {
            if (state[i] != SOLUTION[i]) {
                heuristic++;
            }
        }
        return heuristic;
    }

    private Node popLowestCostNode(List<Node> list) {
        int minIndex = 0;
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCost() < minCost) {
                minCost = list.get(i).getCost();
                minIndex = i;
            }
        }
        return list.remove(minIndex);
    }

    private boolean isSolution(Node node) {
        return Arrays.equals(node.state, SOLUTION);
    }

    private int[] getTrace(Node start, Node finish) {
        ArrayList<Integer> resultList = new ArrayList<>();
        Node current = finish;
        while (!current.equals(start)) {
            resultList.add(current.movedNum);
            current = current.parent;
        }
        Collections.reverse(resultList);
        return resultList.stream().mapToInt(i -> i).toArray();
    }

    private class Node {

        private Node parent;
        private int[] state;
        private int movedNum;
        private int heuristic;
        private int distance;

        public Node() {
        }

        public Node(Node parent, int[] state, int movedNum, int heuristic, int distance) {
            this.parent = parent;
            this.state = state;
            this.movedNum = movedNum;
            this.heuristic = heuristic;
            this.distance = distance;
        }

        public int getCost() {
            return heuristic + distance;
        }

        public Node setState(int[] state) {
            this.state = state;
            return this;
        }

        public Node setHeuristic(int heuristic) {
            this.heuristic = heuristic;
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Arrays.equals(state, node.state);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(state);
        }
    }
}
