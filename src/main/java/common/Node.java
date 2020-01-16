package common;

import java.util.ArrayList;

public class Node<T> {

    String id;
    T data;

    Node<T> parent;
    ArrayList<Node<T>> children = new ArrayList<>();

    public Node(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;

        parent.addChild(this);
    }

    public ArrayList<Node<T>> getChildren() {
        return children;
    }

    public void addChild(Node<T> child) {
        children.add(child);
    }

    public void removeChild(Node<T> child) {
        children.remove(child);
    }

    public boolean isRoot() {
        return this.parent == null;
    }
}
