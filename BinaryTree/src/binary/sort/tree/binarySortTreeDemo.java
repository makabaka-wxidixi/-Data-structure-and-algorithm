package binary.sort.tree;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-09-24 13:01
 * <p>
 * 二叉排序数有一个问题，就是当加入的数据是按照升序进行排列的，或者降序排列的，那么就会形成一条单链表
 */
public class binarySortTreeDemo {
    public static void main(String[] args) {
        BinarySortTree binarySortTree = new BinarySortTree();
        binarySortTree.add(new Node(4));
        binarySortTree.add(new Node(6));
        binarySortTree.add(new Node(2));
        binarySortTree.add(new Node(3));
        binarySortTree.add(new Node(7));
        binarySortTree.add(new Node(1));
        binarySortTree.add(new Node(8));
        binarySortTree.add(new Node(5));
        System.out.println("前序遍历");
        binarySortTree.preOrder();

        Node root = binarySortTree.getRoot();
        System.out.println("根节点是：" + root);

        Node node = binarySortTree.searchNode(6);
        System.out.println("要查找的结点：" + node);

        Node node1 = binarySortTree.searchParentNode(8);
        System.out.println("要查找的结点的父节点：" + node1);
        binarySortTree.deleteNode(4);
        System.out.println("删除指定结点之后的遍历");
        binarySortTree.preOrder();
        System.out.println("新的根节点" + binarySortTree.getRoot());
    }
}

class BinarySortTree {
    Node root = null;

    Node getRoot() {
        return root;
    }

    /**
     * 找到子树的最大值
     *
     * @param node 根节点
     * @return 返回树的最大值
     */
    int getMinNode(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        //此时node指向的就是最大值
        int ret = node.value;
        deleteNode(node.value);
        return ret;
    }

    /**
     * 删除指定结点
     *
     * @param value 要删除的结点的内容
     */
    void deleteNode(int value) {
        if (root == null) {
            System.out.println("树为空，无法删除结点");
            return;
        } else {
            Node targetNode = searchNode(value);
            //判断树中是否有要删除的结点
            if (targetNode == null) {
                System.out.println("要删除的结点不存在");
                return;
            }
            //如果树只有一个根节点
            if (root.left == null && root.right == null) {
                if (root.value == value) {
                    root = null;
                }
                return;
            }
            Node parent = searchParentNode(value);
            //如果删除的是叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                if (targetNode.value == parent.left.value) {//要删除的是父节点的左子节点
                    parent.left = null;
                } else {//要删除的是父节点的右子节点
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                //结点有两个子节点
                //方法一：找左子树中的最大值
                //方法二：找右子树中的最小值
                int minNode = getMinNode(targetNode.left);
                targetNode.value = minNode;
            } else {//删除的结点只有一个孩子结点
                if (targetNode.left != null) {//要删除的结点有左子节点
                    if (parent != null) {
                        if (parent.left == targetNode) {//要删除的结点是父节点的左子节点
                            parent.left = targetNode.left;
                        } else {//要删除的结点是父节点的右子节点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {//删除的结点有右子节点
                    if (parent != null) {
                        if (parent.left == targetNode) {//要删除的结点是父节点的左子节点
                            parent.left = targetNode.right;
                        } else {//要删除的结点是父节点的右子节点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }

    /**
     * 查找给定结点的父节点
     *
     * @param value 要查找的结点的内容
     * @return 找到返回目标节点的父节点，找不到就返回null
     */
    Node searchParentNode(int value) {
        if (root == null) {
            System.out.println("树为空，查找失败");
            return null;
        } else {
            return root.searchParentNode(value);
        }
    }

    /**
     * 查找目标节点
     *
     * @param value 要查找的节点内容
     * @return 找到就返回结点；找不到就返回null
     */
    Node searchNode(int value) {
        if (root == null) {
            System.out.println("树为空");
            return null;
        } else {
            return root.searchNode(value);
        }
    }

    /**
     * 往树中添加结点
     *
     * @param node 要加入的结点
     */
    void add(Node node) {
        if (node == null) {
            System.out.println("传入的节点为空");
        } else if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 前序遍历
     */
    void preOrder() {
        if (root == null) {
            System.out.println("树为空，无法遍历");
        } else {
            root.preOrder();
        }
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node() {
    }

    public Node(int value) {
        this.value = value;
    }

    /**
     * 查找目标结点的父节点
     *
     * @param value 要查找的结点的内容
     * @return 找到返回结点，找不到就返回null
     */
    Node searchParentNode(int value) {
        if (this.left != null && this.left.value == value || this.right != null && this.right.value == value) {
            return this;
        } else {
            /*
            对没有等号的说明
                情况一：如果要查找的内容等于this结点，并且this结点左右节点都有，那么就会在上面的if中直接返回
                情况二；如果要查找的内容等于this结点，并且this结点没有左右子节点，那么就会进入到下面的else直接返回null
                情况三：如果要查找的内容大于this结点，并且this结点存在右子节点，那么就会进入到下面的选择结构
                情况四：如果要查找的内容大于this结点，并且this结点没有右子节点，那么就会进入到下面的else直接返回null
                情况五：如果要查找的内容小于this结点，并且this结点存在左子节点，那么就会进入到下面的选择结构
                情况六：如果要查找的内容小于this结点，并且this结点没有左子节点，那么就会进入到下面的else直接返回null
                总结：下面的选择结构中不用  =
             */
            if (this.value < value && this.right != null) {
                return this.right.searchParentNode(value);
            } else if (this.value > value && this.left != null) {
                return this.left.searchParentNode(value);
            } else {
                return null;
            }
        }
    }

    /**
     * 查找目标结点
     *
     * @param value 要查找的结点的内容
     * @return 返回要查找的节点，找不到就返回null
     */
    Node searchNode(int value) {
        if (this.value == value) {
            return this;
        } else if (this.left != null && this.value > value) {//如果左子节点不为null且目标值小于该节点值，就向左递归
            return this.left.searchNode(value);
        } else if (this.right != null && this.value < value) {//如果右子节点不为null且目标值大于该节点值，就向右递归
            return this.right.searchNode(value);
        } else {
            return null;
        }
    }

    /**
     * 传入的节点和当前节点进行比较，如果大于当前节点，向右递归查找；如果小于当前结点，向左递归查找；当左子节点或者右子节点为空时
     * 就直接加入到左子节点或者右子节点
     *
     * @param node 传入要加入的结点
     */
    void add(Node node) {
        if (this.value > node.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    /**
     * 前序遍历
     */
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
}
