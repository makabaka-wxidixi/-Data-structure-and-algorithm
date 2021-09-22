package ThreadedBinaryTree;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-09-17 15:01
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        Tree tree = new Tree(root);
        //手动构建二叉树
        root.left = node1;
        root.right = node4;
        node1.left = node2;
        node1.right = node3;
        node4.left = node5;

        tree.ThreadedTree();
        tree.preOrder();

    }
}

class Tree {
    TreeNode root = null;
    TreeNode pre = null;

    Tree(TreeNode root) {
        this.root = root;
    }

    void ThreadedTree() {
        if (root != null) {
            ThreadedTree(root);
        } else {
            System.out.println("树为空");
        }
    }

    //二叉树前序搜索化
    void ThreadedTree(TreeNode node) {
        //结点为空时无法线索化
        if (node == null) {
            return;
        }
        if (node.left == null) {
            node.left = pre;
            //标记左指针域
            node.typeLeft = 1;
        }
        if (pre != null && pre.right == null) {
            pre.right = node;
            //标记右指针
            pre.typeRight = 1;
        }
        pre = node;
        //向左子树进行遍历
        if (node.typeLeft == 0) {
            ThreadedTree(node.left);
        }
        if (node.typeRight == 0) {
            ThreadedTree(node.right);
        }
    }

    //前序遍历
    void preOrder() {
        TreeNode temp = root;
        if (temp == null) {
            return;
        }
        while (temp != null) {
            while (temp.typeLeft == 0) {
                System.out.println(temp.id);
                //向左遍历，并打印
                temp = temp.left;
            }
            System.out.println(temp.id);
            while (temp.typeRight == 1) {
                System.out.println(temp.id);
                temp = temp.right;
            }
            if (temp.typeLeft == 1 && temp.typeLeft == 0) {
                temp = temp.right;
            }
        }
    }
}

class TreeNode {
    int id;
    TreeNode left;
    TreeNode right;
    //左右结点标记，0代表左右指针域连接的是null或者子节点，1代表连接的是前驱或者后继节点
    int typeLeft = 0;
    int typeRight = 0;

    TreeNode(int id) {
        this.id = id;
    }

}
