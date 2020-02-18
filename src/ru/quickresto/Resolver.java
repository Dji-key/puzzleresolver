package ru.quickresto;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of {@link PuzzleResolver}
 */
public class Resolver implements PuzzleResolver {

    private final int[] STANDARD = {1, 2, 3, 4, 0, 5, 6, 7};
    private final int[][] GRAPH = {{1, 2}, {0, 2, 3}, {0, 1, 5}, {1, 4, 6}, {3, 5}, {2, 4, 7}, {3, 7}, {5, 6}};

    @Override
    public int[] resolve(int[] start) {
        if (start.length != 8) {
            throw new IllegalArgumentException();
        }

        LinkedList<Node> open = new LinkedList<>();
        LinkedList<Node> closed = new LinkedList<>();

        Node startNode = new Node().setState(start).setHeuristic(calculateHeuristic(start));
        open.add(startNode);
        while (!open.isEmpty()) {
            Node current = getLowestCostNode(open);
        }

        return new int[0];
    }

    private Collection<Node> getNeighbors(Node parent) {
        LinkedList<Node> neighbors = new LinkedList<>();
        int indexOfZero = -1;
        for (int i = 0; i < 8; i++) {
            if (parent.getState()[i] == 0) {
                indexOfZero = i;
            }
        }

        for (int index : GRAPH[indexOfZero]) {
            int[] state = parent.getState().clone();
            state[indexOfZero] = state[index];
            state[index] = 0;
            Node node = new Node(parent, state, state[indexOfZero], calculateHeuristic(state), parent.getDistance() + 1);
            neighbors.add(node);
        }

        return neighbors;
    }

    private int calculateHeuristic(int[] state) {
        int heuristic = 0;
        for (int i = 0; i < state.length; i++) {
            if (state[i] != STANDARD[i]) {
                heuristic++;
            }
        }
        return heuristic;
    }

    private Node getLowestCostNode(List<Node> list) {
        Node lowest = list.get(0);
        for (Node node : list) {
            if (node.getCost() < lowest.getCost()) {
                lowest = node;
            }
        }
        return lowest;
    }
}
