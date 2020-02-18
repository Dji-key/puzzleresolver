package ru.quickresto;

public class Node {

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

    public int getMovedNum() {
        return movedNum;
    }

    public Node setMovedNum(int movedNum) {
        this.movedNum = movedNum;
        return this;
    }

    public int[] getState() {
        return state;
    }

    public Node setState(int[] state) {
        this.state = state;
        return this;
    }

    public Node getParent() {
        return parent;
    }

    public Node setParent(Node parent) {
        this.parent = parent;
        return this;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public Node setHeuristic(int heuristic) {
        this.heuristic = heuristic;
        return this;
    }

    public int getDistance() {
        return distance;
    }

    public Node setDistance(int distance) {
        this.distance = distance;
        return this;
    }
}
