package a_b_改善;

public class Node {
    public static void main(String[] args) {

    }

    private NodeInner startNode = new NodeInner();
    private NodeInner endNode = startNode;

    public synchronized void addNode(NodeInner nodeInner) {
        NodeInner lastNode = endNode;
        lastNode.child = nodeInner;

        nodeInner.parent = lastNode;
        endNode = nodeInner;
    }

    public synchronized void removeNode(NodeInner nodeInner) {

        NodeInner whileNode = startNode;
        while (whileNode != null) {
            if (whileNode == nodeInner) {
                NodeInner findNodeChild = whileNode.child;
                NodeInner findNodeParent = whileNode.parent;
                whileNode.parent = null;
                whileNode.child = null;

                break;
            }
            whileNode = startNode.child;
        }
    }


    public class NodeInner {
        NodeInner parent;
        NodeInner child;
    }

}
