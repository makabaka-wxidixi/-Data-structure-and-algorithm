package huffman.tree.example;
import java.util.*;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-09-22 22:21
 *
 * 利用霍夫曼树实现数据的压缩
 * 思路：
 *     1.将需要压缩的字符串转化成字节数组
 *     2.遍历数组，将数据以及数据出现的次数都添加到Node中去
 *     3.生成霍夫曼树
 *     4.根据霍夫曼树，得到每个叶子结点的编码（根据霍夫曼树特性，叶子结点就是需要编码的字符）
 *     5.生成对应的数据压缩表
 *     6.将字节数组中的数据对照压缩表转化成对应的字符串
 *     7.将字符串以8个比特位一个单位，放到byte数组中去-----------得到压缩后的数据
 *     8.将压缩表key和value翻转，得到解压表
 *     9.根据解压表将byte数组中的数据解压成String类型
 *     10.将String类型的数据对应解压表得到元数据
 */
@SuppressWarnings({"all"})
public class huffmanCodeDemo {
    public static void main(String[] args) {
        String message = "wo shi ni baba zhen wei da yang ni zhe me da";
        byte[] bytes = message.getBytes();
        byte[] zipCode = huffmanZipCode(bytes);
        //得到压缩过后的字节码数组
        System.out.println(Arrays.toString(zipCode));
        byte[] decod = decode(zipCode);
        System.out.println(Arrays.toString(decod));
        String s = new String(decod);
        System.out.println(s);
    }

    /**
     * 得到霍夫曼解压表
     */
    static HashMap<String, Byte> huffmanDecodes = new HashMap<>();

    static HashMap<String, Byte> getDecodeTable() {
        Set<Map.Entry<Byte, String>> entries = huffmanCodes.entrySet();
        for (Map.Entry<Byte, String> entry : entries) {
            huffmanDecodes.put(entry.getValue(), entry.getKey());
        }
        return huffmanDecodes;
    }

    /**
     * 得到原来的内容
     *
     * @param zipCode 解压之后的字节码
     * @return 得到原来内容的字节码数组
     */
    static byte[] decode(byte[] zipCode) {
        HashMap<String, Byte> huffmanDecodes = getDecodeTable();
        String binaryString = byteToBinaryString(zipCode);
        char[] chars = binaryString.toCharArray();
        //用于存放得到的字符
        ArrayList<Byte> arrayList = new ArrayList();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < binaryString.length(); i++) {
            stringBuilder.append(chars[i]);
            //如果stringBuilder可以在解压表中找到对应的数据
            if (huffmanDecodes.get(stringBuilder.toString()) != null) {
                //将数据放到集合中
                arrayList.add(huffmanDecodes.get(stringBuilder.toString()));
                //刷新stringBuilder
                stringBuilder.setLength(0);
            }
        }
        //将集合中的数据放到byte中去
        int i = 0;
        byte[] bytes1 = new byte[arrayList.size()];
        for (Byte b : arrayList) {
            bytes1[i++] = b;
        }
        return bytes1;
    }

    /**
     * 将字节码数组中的数据转换成二进制字符串
     *
     * @param zipCode 给出压缩之后的编码
     * @return 恢复成压缩前的霍夫曼编码
     */
    static String byteToBinaryString(byte[] zipCode) {
        StringBuilder stringBuilder = new StringBuilder();
        String subString = null;
        for (int i = 0; i < zipCode.length; i++) {
            boolean flag = (i == zipCode.length - 1);//确定是不是最后一个字节
            if (flag) {//最后一个字节
                subString = byteToBinaryStringAuxiliary(false, zipCode[i]);
                stringBuilder.append(subString);
            } else {
                subString = byteToBinaryStringAuxiliary(true, zipCode[i]);
                stringBuilder.append(subString);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 辅助函数，将单个byte转化成String
     *
     * @param flag 判断是否需要补高位，true是补高位，false不用补高位
     * @param b    需要处理的字节
     * @return 返回处理之后的字符串
     */
    static String byteToBinaryStringAuxiliary(boolean flag, byte b) {
        int temp = b;
        if (flag) {
            temp |= 256;//针对正数进行补高位
        }
        String binary = Integer.toBinaryString(temp);//得到的是补码
        if (flag) {
            return binary.substring(binary.length() - 8);
        } else {//如果不需要补位就直接输出字符串
            return binary;
        }
    }

    /**
     * 生成压缩后的字节数组
     *
     * @param bytes 传入message的字节数组
     * @return 返回哈夫曼编码
     */
    static byte[] huffmanZipCode(byte[] bytes) {
        //将message中的数据封装到Node中
        ArrayList nodes = getNodes(bytes);
        //创建霍夫曼树
        Node root = creatuffmanTree(nodes);
        //获取对应的编码表
        HashMap<Byte, String> codeTable = getCodeTable(root);
        //得到要传输的字节数组
        return getHuffmanCodes(bytes, huffmanCodes);
    }

    /**
     * @param message      传入要发送的数据的字节数组
     * @param huffmanCodes 哈夫曼编码表
     * @return 已字节数组的形式返回压缩后的字节
     */
    static byte[] getHuffmanCodes(byte[] message, HashMap<Byte, String> huffmanCodes) {
        //用于拼接字节
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : message) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //得到字节数
        int len = (stringBuilder.length() + 7) / 8;
        byte[] huffmanByteCodes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            //用于接收8个长度的字节
            String subString = "";
            if ((i + 8) >= stringBuilder.length()) {
                subString = stringBuilder.substring(i);
            } else {
                subString = stringBuilder.substring(i, i + 8);
            }
            huffmanByteCodes[index++] = (byte) Integer.parseInt(subString, 2);
        }
        return huffmanByteCodes;
    }

    //用于存放得到的编码表
    static HashMap<Byte, String> huffmanCodes = new HashMap<>();
    //用于拼接编码
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * @param root 传入一个结点
     * @return 返回一个从该节点开始的霍夫曼编码表
     */
    static HashMap<Byte, String> getCodeTable(Node root) {
        if (root == null) {
            System.out.println("根节点为空");
        } else {
            getCodeTableAuxiliary(root, "0", stringBuilder);
            getCodeTableAuxiliary(root, "1", stringBuilder);
        }
        return huffmanCodes;
    }

    /**
     * 递归寻找每个叶子结点的编码（辅助函数）
     *
     * @param node          根节点
     * @param code          对应的编码，向左是0，向右是1
     * @param stringBuilder 用于拼接编码
     */
    private static void getCodeTableAuxiliary(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        //编码存入字符串
        stringBuilder1.append(code);
        if (node.value == 0) {//value不为0，说明不是叶子结点，那么久继续递归组合编码
            getCodeTableAuxiliary(node.left, "0", stringBuilder1);
            getCodeTableAuxiliary(node.right, "1", stringBuilder1);
        } else {//是叶子结点就结束组合，将编码放到huffmanCodes中去
            huffmanCodes.put((byte) node.value, stringBuilder1.toString());
        }
    }

    /**
     * 霍夫曼的创建
     *
     * @param nodes 传入Node集合
     * @return 返回树的根节点
     */
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
        //返回根节点
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
            Integer integer = counts.get(ele);//将key对应的value取出来
            //如果为null，就说明counts中还没有这个数据
            if (integer == null) {
                counts.put(ele, 1);
            } else {
                counts.put(ele, integer + 1);
            }
        }
        //返回加入权值的Node集合
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
