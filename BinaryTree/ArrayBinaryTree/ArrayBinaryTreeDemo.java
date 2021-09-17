package ArrayBinaryTree;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-09-17 12:58
 *
 * 顺序存储二叉树
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        Tree tree = new Tree(arr);
        tree.preOrder();
        tree.midOrder();
        tree.posOrder();
    }
}

class Tree {
    int[] arr = null;

    Tree(int[] arr) {
        this.arr = arr;
    }
    //前序遍历
    void preOrder() {
        if (arr != null && arr.length > 0) {
            preOrder(0);
            System.out.println();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    void midOrder() {
        if (arr != null && arr.length > 0) {
            midOrder(0);
            System.out.println();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    void posOrder() {
        if (arr != null && arr.length > 0) {
            posOrder(0);
            System.out.println();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序遍历顺序存储二叉树
    void preOrder(int index) {
        System.out.print(arr[index]+"\t");
        //遍历左子节点
        if ((2 * index + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        //遍历右子节点
        if ((2 * index + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    //中序遍历顺序存储二叉树
    void midOrder(int index) {
        if ((2 * index + 1) < arr.length) {
            midOrder(2 * index + 1);
        }
        System.out.print(arr[index]+"\t");
        if ((2 * index + 2) < arr.length) {
            midOrder(2 * index + 2);
        }
    }

    //后序遍历顺序存储二叉树
    void posOrder(int index) {
        if ((2 * index + 1) < arr.length) {
            posOrder(2 * index + 1);
        }
        if ((2 * index + 2) < arr.length) {
            posOrder(2 * index + 2);
        }
        System.out.print(arr[index]+"\t");
    }
}

class TreeNode {
    int id;
    TreeNode left;
    TreeNode right;

}


