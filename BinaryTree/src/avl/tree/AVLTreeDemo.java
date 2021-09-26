package avl.tree;

import java.lang.Math;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-09-26 11:30
 */
@SuppressWarnings("all")
public class AVLTreeDemo {
    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
//        avlTree.add(new Node(5));
//        avlTree.add(new Node(4));
//        avlTree.add(new Node(7));
//        avlTree.add(new Node(6));
//        avlTree.add(new Node(9));
//        avlTree.add(new Node(8));
//        avlTree.preOrder();
//        System.out.println("左旋转之前");
//        System.out.println("左子树高度：" + avlTree.leftHeight());
//        System.out.println("右子树高度：" + avlTree.rightHeight());
//        System.out.println("树的高度：" + avlTree.height());
//        System.out.println("左旋转之后");
//        avlTree.leftRotate();
//        System.out.println("左子树高度：" + avlTree.leftHeight());
//        System.out.println("右子树高度：" + avlTree.rightHeight());
//        System.out.println("树的高度：" + avlTree.height());

//        avlTree.add(new Node(5));
//        avlTree.add(new Node(3));
//        avlTree.add(new Node(2));
//        avlTree.add(new Node(1));
//        avlTree.add(new Node(4));
//        avlTree.add(new Node(6));
//        avlTree.preOrder();
//        System.out.println("右旋转之前");
//        System.out.println("左子树高度：" + avlTree.leftHeight());
//        System.out.println("右子树高度：" + avlTree.rightHeight());
//        System.out.println("树的高度：" + avlTree.height());
//        System.out.println("右旋转之后");
//        avlTree.rightRotate();
//        System.out.println("左子树高度：" + avlTree.leftHeight());
//        System.out.println("右子树高度：" + avlTree.rightHeight());
//        System.out.println("树的高度：" + avlTree.height());

        avlTree.add(new Node(7));
        avlTree.add(new Node(11));
        avlTree.add(new Node(2));
        avlTree.add(new Node(9));
        avlTree.add(new Node(12));
        avlTree.add(new Node(8));
        avlTree.preOrder();
    }
}

class AVLTree {
    Node root = null;

    Node getRoot() {
        return root;
    }


    /**
     * @return 返回右子树的高度
     */
    int rightHeight() {
        if (root == null) {
            System.out.println("树为空");
            return 0;
        } else if (root.right == null) {
            return 0;
        } else {
            return root.right.height();
        }
    }

    /**
     * @return 返回左子树的高度
     */
    int leftHeight() {
        if (root == null) {
            System.out.println("树为空");
            return 0;
        } else if (root.left == null) {
            return 0;
        } else {
            return root.left.height();
        }
    }

    /**
     * @return 返回树的高度
     */
    int height() {
        if (root == null) {
            return 0;
        } else {
            return root.height();
        }
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
     * 以当前节点为根节点进行右旋转
     */
    void rightRotate() {
        //创建一个新节点，新节点的值为根节点的值
        Node newNode = new Node(this.value);
        //新节点右子树指向根节点右子树
        newNode.right = this.right;
        //新节点左子树指向根节点左子树的右子树
        newNode.left = this.left.right;
        //根节点重新赋值
        this.value = this.left.value;
        //根节点左子树指向根节点左子树的左子树
        this.left = this.left.left;
        //根节点右子树指向新创建的结点
        this.right = newNode;
    }

    /**
     * 以当前节点为根节点进行左旋转
     */
    void leftRotate() {
        //创建一个新节点，新节点的值为根节点的值
        Node newNode = new Node(this.value);
        //新节点左子树指向左子树
        newNode.left = this.left;
        //新节点的右子树为右子树的左子树
        newNode.right = this.right.left;
        //对根节点重新赋值
        this.value = this.right.value;
        //根节点左子树指向新创建的结点
        this.left = newNode;
        //根节点右子树指向右子树的右子树
        this.right = this.right.right;
    }

    /**
     * @return 返回右子树的高度，右子树为null时返回0
     */
    int rightHeight() {
        if (this.right == null) {
            return 0;
        } else {
            return this.right.height();
        }
    }

    /**
     * @return 返回左子树的高度，左子树为空时返回0
     */
    int leftHeight() {
        if (this.left == null) {
            return 0;
        } else {
            return this.left.height();
        }
    }

    /**
     * @return 返回以当前节点为根节点的树的高度
     */
    int height() {
        return Math.max(this.left == null ? 0 : this.left.height(), this.right == null ? 0 : this.right.height()) + 1;
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
     * 寻找指定内容的结点
     *
     * @param value 要找的结点内容
     * @return 返回要找的节点
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
        //判断是否需要左旋转
        if (this.rightHeight() - this.leftHeight() > 1) {
            //如果右子树的左子树>右子树的右子树
            if (this.right.leftHeight() > this.right.rightHeight()) {
                this.right.rightRotate();
            }
            this.leftRotate();
            return;
        }
        //判断是否需要右旋转
        if (this.leftHeight() - this.rightHeight() > 1) {
            if (this.left.rightHeight() > this.left.leftHeight()) {
                this.left.leftRotate();
            }
            this.rightRotate();
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
