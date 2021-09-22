package huffman.tree.example;

import java.util.*;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-09-22 22:21
 */
@SuppressWarnings({"all"})
public class huffmanCodeDemo {
    public static void main(String[] args) {
        String message = "asso is just a garbage hero";
        byte[] bytes = message.getBytes();
        //将message中的数据封装到Node中
        ArrayList nodes = getNodes(bytes);
        //创建霍夫曼树
        Node root = creatuffmanTree(nodes);
        preOrder(root);

    }

    static Node creatuffmanTree(ArrayList nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            //得到最小的两个结点
            Node leftNode = (Node) nodes.get(0);
            Node rightNode = (Node) nodes.get(1);
            //得到根节点
            Node parent = new Node(0, leftNode.weight + rightNode.weight);
            nodes.add(parent);
            //子节点连接到根节点上
            parent.left = leftNode;
            parent.right = rightNode;
            //将两个子节点删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
        }
        return (Node) nodes.get(0);
    }

    /**
     * @param arr 传入一个字节数组
     * @return 将数组中的字符以及出现的次数都封装到Node中去
     */
    static ArrayList getNodes(byte[] arr) {
        ArrayList arrayList = new ArrayList();
        HashMap<Byte, Integer> counts = new HashMap();
        for (byte ele : arr) {
            Integer integer = counts.get(ele);
            if (integer == null) {//说明map中没有这个数据
                counts.put(ele, 1);
            } else {
                counts.put(ele, integer + 1);
            }
        }
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            arrayList.add(new Node(entry.getKey(), entry.getValue()));
        }
        return arrayList;
    }

    //前序遍历
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
    int weight;
    Node left;
    Node right;


    public Node(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    //前序遍历
    public void preOrder() {
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
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}
