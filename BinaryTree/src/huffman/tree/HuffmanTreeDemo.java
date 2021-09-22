package huffman.tree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-09-22 21:26
 * <p>
 * 霍夫曼树（最优二叉树）的创建
 */
public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 4, 6, 23, 12, 53};
        //将数组中的数据封装到Node中
        ArrayList<Node> nodes = getNode(arr);
        Collections.sort(nodes);
        //获取霍夫曼树
        Node root = HuffmanTree(nodes);
        //前序遍历霍夫曼树
        preOrder(root);
    }

    //创建霍夫曼树
    static Node HuffmanTree(ArrayList<Node> nodes) {
        while (nodes.size() > 1) {
            //得到最小的两个值
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //得到根节点
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //得到的根节点加入到队列中
            nodes.add(parent);
            //删除两个子节点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将集合重新排序
            Collections.sort(nodes);
        }
        return nodes.get(0);
    }

    //将数组中的数据封装成Node，放到集合中去
    static ArrayList<Node> getNode(int[] arr) {
        ArrayList<Node> list = new ArrayList();
        for (int ele : arr) {
            list.add(new Node(ele));
        }
        return list;
    }

    //遍历树
    static void preOrder(Node root) {
        if (root == null) {
            System.out.println("树为空，无法遍历");
        } else {
            root.preOrder();
        }
    }
}

class Node implements Comparable<Node> {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
