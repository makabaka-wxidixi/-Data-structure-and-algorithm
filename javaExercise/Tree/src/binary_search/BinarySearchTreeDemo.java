package binary_search;

import java.util.Stack;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-10-13 22:16
 */
public class BinarySearchTreeDemo {
    public static void main(String[] args) {
        // 涉及二叉搜索树的插入、查找、删除、递归遍历（前中后序）、非递归遍历（前中后序）
    }
}

class Node {
    public int value;
    public Node leftChild;
    public Node rightChild;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return " -> " + value;
    }
}

//二叉搜索树的增删查该操作
class Tree {
    private Node root;

    /**
     * 插入结点
     *
     * @param value 要插入结点的值
     */
    public void insert(int value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
        } else {
            Node parent, current = root;
            boolean isLeftChild;//判断是父节点的左子节点还是右子节点
            while (true) {
                parent = current;
                if (value < current.value) {
                    isLeftChild = true;
                    current = current.leftChild;
                } else if (value > current.value) {//当插入的结点大于当前节点的值就插入到右子树
                    isLeftChild = false;
                    current = current.rightChild;
                } else {//要插入的结点的值等于当前节点的值
                    newNode.rightChild = current.rightChild;
                    current.rightChild = newNode;
                    return;
                }
                if (current == null) {//如果要插入的位置为null
                    if (isLeftChild) {
                        parent.leftChild = newNode;
                    } else {
                        parent.rightChild = newNode;
                    }
                    return;
                }
            }
        }
    }

    /**
     * 判断是否包含指定值
     *
     * @param value 要查找的值
     * @return
     */
    private boolean contain(int value) {
        Node res = contains(value, root);
        if (res == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 打印指定结点
     *
     * @param value
     */
    public void printTargetNode(int value) {
        find(value, root);
    }

    /**
     * 打印目标节点
     *
     * @param value
     * @param local 开始查找的结点
     */
    private void find(int value, Node local) {
        if (local == null) {
            return;
        }
        Node current = contains(value, local);//判断是否存在指定结点
        if (current == null) {//如果找不到结点就直接返回，递归结束条件
            return;
        }
        System.out.println(current);
        find(value, current.rightChild);
    }

    /**
     * 从某个结点开始查找指定节点，用于判断或者查找重复的结点
     *
     * @param value
     * @param local 从哪个结点开始查找
     * @return
     */
    private Node contains(int value, Node local) {
        if (local == null) {
            return null;
        } else {
            Node current = local;
            while (value != current.value) {
                if (value < current.value) {
                    current = current.leftChild;
                } else {
                    current = current.rightChild;
                }
                if (current == null) {
                    return null;
                }
            }
            return current;
        }
    }

    /**
     * 删除目标节点
     *
     * @param value
     */

    public void delete(int value) {
        if (root == null) {
            return;
        }
        if (!contain(value)) {//如果不存在目标值，那么就返回。也是递归的结束条件
            return;
        }
        Node parent = root, current = root;//要删除的结点和其父节点
        boolean isLeftChild = true;//判断要删除的结点是不是左子节点
        //循环找到要删除的结点及其父节点
        while (current.value != value) {
            parent = current;
            if (value < current.value) {
                current = current.leftChild;
                isLeftChild = true;
            } else {
                current = current.rightChild;
                isLeftChild = false;
            }
        }
        if (current.leftChild == null && current.rightChild == null) {//如果要删除的节点是叶子结点
            if (current == root) {
                root = null;
            } else {
                if (isLeftChild) {
                    parent.leftChild = null;
                } else {
                    parent.rightChild = null;
                }
            }
        } else if (current.leftChild != null && current.rightChild != null) {//要删除的结点有两个子节点
            Node minRight = current.rightChild;
            Node minParent = current;
            while (minRight.leftChild != null) {//找到右子树最小值
                minParent = minRight;
                minRight = minRight.leftChild;
            }
            if (minRight == current.rightChild) {//如果要删除的节点的右子节点就是右子树最小值
                if (current == root) {
                    minRight.leftChild = current.leftChild;
                    root = minRight;
                } else {
                    if (isLeftChild) {//要删除的结点是左子节点
                        minRight.leftChild = current.leftChild;
                        parent.leftChild = minRight;
                    } else {//要删除的结点是右子节点
                        minRight.leftChild = current.leftChild;
                        parent.rightChild = minRight;
                    }
                }
            } else {//要删除的结点的右子节点不是右子树的最小值
                if (current == root) {//要删除的是根节点
                    minRight.leftChild = root.leftChild;
                    minRight.rightChild = root.rightChild;
                    root = minRight;
                    minParent.leftChild = null;
                } else {//要删除的不是根节点
                    if (isLeftChild) {//要删除的节点是左子节点
                        minRight.leftChild = current.leftChild;
                        minRight.rightChild = current.rightChild;
                        parent.leftChild = minRight;
                        minParent.leftChild = null;
                    } else {//要删除的结点是右子节点
                        minRight.leftChild = current.leftChild;
                        minRight.rightChild = current.rightChild;
                        parent.rightChild = minRight;
                        minParent.leftChild = null;
                    }
                }
            }
        } else {//要删除的结点有一个结点
            if (current.leftChild == null) {//要删除的节点只有一个右子节点
                if (current == root) {
                    root = root.rightChild;
                } else {
                    if (isLeftChild) {
                        parent.leftChild = current.rightChild;
                    } else {
                        parent.rightChild = current.rightChild;
                    }
                }
            } else if (current.rightChild == null) {//要删除的节点只有一个左子节点
                if (current == root) {
                    root = root.leftChild;
                } else {
                    if (isLeftChild) {
                        parent.leftChild = current.leftChild;
                    } else {
                        parent.rightChild = current.leftChild;
                    }
                }
            }
        }
        delete(value);//递归删除相同数据
    }

    /**
     * 递归后序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node current) {
        if (current == null) {
            return;
        } else {
            System.out.print(current);
            preOrder(current.leftChild);
            preOrder(current.rightChild);
        }
    }

    /**
     * 递归中序遍历
     */

    public void midOrder() {
        midOrder(root);
    }

    private void midOrder(Node current) {
        if (current == null) {
            return;
        } else {
            midOrder(current.leftChild);
            System.out.print(current);
            midOrder(current.rightChild);
        }
    }

    /**
     * 递归后序遍历
     */
    public void rearOrder() {
        rearOrder(root);
    }

    private void rearOrder(Node current) {
        if (current == null) {
            return;
        } else {
            rearOrder(current.leftChild);
            rearOrder(current.rightChild);
            System.out.print(current);
        }
    }


    //非递归前序遍历
    public void preOrder1ByIteration() {
        Node current = root;
        Stack<Node> stack = new Stack<>();
        while (true) {
            System.out.print(current);//访问根节点
            if (current.rightChild != null) {//如果当前结点有右子节点就入栈
                stack.push(current);
            }
            current = current.leftChild;
            if (current == null) {
                if (stack.isEmpty()) {//此时就已经将所有结点都访问完毕，退出循环
                    return;
                } else {//左子节点为空，那么就尝试去遍历右子节点
                    current = stack.pop().rightChild;
                }
            }
        }
    }

    //非递归中序遍历
    public void midOrderByIteration() {
        Node current = root;
        Stack<Node> stack = new Stack<>();
        while (!(current == null && stack.isEmpty())) {
            while (current != null) {//先将每一个结点都入栈
                stack.push(current);
                current = current.leftChild;
            }
            current = stack.pop();//从栈顶薅取元素
            System.out.print(current);
            current = current.rightChild;//访问当前节点的右子节点
        }
    }

    //非递归后序遍历
    public void rearOrderByIteration() {
        Node current = root;
        Stack<Node> stack = new Stack<>();
        Node pre = null;
        while (!(current == null && stack.isEmpty())) {
            while (current != null) {//将当前结点及其左子节点入栈
                stack.push(current);
                current = current.leftChild;
            }
            current = stack.peek();//窥探栈顶元素
            if (current.rightChild == null || current.rightChild == pre) {//如果右子节点为null或者右子节点已经访问过
                current = stack.pop();//栈顶元素出栈
                System.out.print(current);//访问栈顶元素
                pre = current;//记录当前节点已经访问过了
                current = null;
            } else {
                current = current.rightChild;
            }
        }
    }
}
