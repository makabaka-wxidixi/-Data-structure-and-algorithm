package binaryTree;

import com.sun.source.tree.Tree;

import java.util.Scanner;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-09-16 14:39
 */
public class binaryTreeOrderDemo {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        //手动创建二叉树，用于验证三种遍历方式的有效性
        TreeNode node1 = new TreeNode(1, "诺克");
        TreeNode node2 = new TreeNode(2, "盖伦");
        TreeNode node3 = new TreeNode(3, "亚索");
        TreeNode node4 = new TreeNode(4, "塞恩");
        TreeNode node5 = new TreeNode(5, "青钢影");
        tree.setRoot(node1);
        node1.left = node2;
        node1.right = node3;
        node3.right = node4;
        node3.left = node5;
        tree.preOrder();
        System.out.println("=======================");
        tree.midOrder();
        System.out.println("=======================");
        tree.posOrder();
        System.out.println("=======================");
        System.out.println(tree.midOrderSearch(5));
        System.out.println("=====================");
        System.out.println(tree.preOrderSearch(5));
        System.out.println("=====================");
        System.out.println(tree.posOrderSearch(5));
        System.out.println("=====================");
    }
}

class BinaryTree {
    private TreeNode root;

    public void setRoot(TreeNode root) {
        this.root = root;
    }


    public void preOrder() {
        if (this.root == null) {
            System.out.println("当前二叉树为空，无法遍历-------1");
        } else {
            this.root.preOrder();
        }
    }

    public void midOrder() {
        if (this.root == null) {
            System.out.println("当前二叉树为空，无法遍历------2");
        } else {
            this.root.midOrder();
        }
    }

    public void posOrder() {
        if (this.root == null) {
            System.out.println("当前二叉树为空，无法遍历------3");
        } else {
            this.root.posOrder();
        }
    }

    //前序搜索
    public TreeNode preOrderSearch(int id) {
        if (!isEmpty()) {
            return this.root.preOrderSearch(id);
        } else {
            System.out.println("二叉树为空");
            return null;
        }
    }

    //中序遍历
    public TreeNode midOrderSearch(int id) {
        if (!isEmpty()) {
            return this.root.midOrderSearch(id);
        } else {
            System.out.println("二叉树为空");
            return null;
        }
    }

    //后序遍历
    public TreeNode posOrderSearch(int id) {
        if (!isEmpty()) {
            return this.root.posOrderSearch(id);
        } else {
            System.out.println("二叉树为空");
            return null;
        }
    }

    public boolean isEmpty() {
        if (root == null) {
            return true;
        }
        return false;
    }
}

class TreeNode {
    private int id;
    private String name;
    TreeNode left;
    TreeNode right;

    public TreeNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void preOrder() {
        //打印该结点信息
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void midOrder() {
        if (this.left != null) {
            this.left.midOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.midOrder();
        }
    }

    //后序遍历
    public void posOrder() {
        if (this.left != null) {
            this.left.posOrder();
        }
        if (this.right != null) {
            this.right.posOrder();
        }
        System.out.println(this);
    }

    //前序搜索
    public TreeNode preOrderSearch(int id) {
        System.out.println("前序遍历");
        if (this.id == id) {
            return this;
        }
        TreeNode temp = null;
        if (this.left != null) {
            temp = this.left.preOrderSearch(id);
        }
        if (temp != null) {
            return temp;
        }
        if (this.right != null) {
            temp = this.right.preOrderSearch(id);
        }
        return temp;
    }

    //中序遍历
    public TreeNode midOrderSearch(int id) {
        TreeNode temp = null;
        if (this.left != null) {
            temp = this.left.midOrderSearch(id);
        }
        if (temp != null) {
            return temp;
        }
        System.out.println("中序遍历");
        if (this.id == id) {
            return this;
        }
        if (this.right != null) {
            temp = this.right.midOrderSearch(id);
        }
        return temp;
    }

    //后序遍历
    public TreeNode posOrderSearch(int id) {
        TreeNode temp = null;
        //左不为空，向左递归
        if (this.left != null) {
            temp = this.left.posOrderSearch(id);
        }
        if (temp != null) {
            return temp;
        }
        if (this.right != null) {
            temp = this.right.posOrderSearch(id);
        }
        if (temp != null) {
            return temp;
        }
        System.out.println("后序遍历");
        if (this.id == id) {
            return this;
        }
        return temp;
    }
}

