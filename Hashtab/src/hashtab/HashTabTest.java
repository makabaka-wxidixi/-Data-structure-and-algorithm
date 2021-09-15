package hashtab;

import java.util.Scanner;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-09-15 13:19
 */
public class HashTabTest {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(3);
        Scanner scanner = new Scanner(System.in);
        int id;
        boolean flag = true;
        String name, key, newName;
        while (flag) {
            System.out.println("add     添加数据");
            System.out.println("delete  删除数据");
            System.out.println("update  修改数据");
            System.out.println("check   查找数据");
            System.out.println("list    打印数据");
            System.out.println("exit    退    出");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("请输入要添加的员工的id");
                    id = scanner.nextInt();
                    System.out.println("请输入员工的姓名");
                    name = scanner.next();
                    hashTab.add(new employee(id, name));
                    break;
                case "delete":
                    System.out.println("请输入要删除的员工的id");
                    id = scanner.nextInt();
                    System.out.println("删除的员工是" + hashTab.delete(id).toString());
                    break;
                case "update":
                    System.out.println("请输入要修改的员工的id");
                    id = scanner.nextInt();
                    System.out.println("请输入要修改的员工的姓名");
                    newName = scanner.next();
                    hashTab.update(id, newName);
                    break;
                case "check":
                    System.out.println("请输入要查找的员工的id");
                    id = scanner.nextInt();
                    System.out.println(hashTab.check(id));
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "exit":
                    flag = false;
                    System.out.println("退出成功");
                    break;
                default:
                    System.out.println("输入错误，请重新输入");
                    break;
            }
        }
    }
}

class HashTab {
    private static int size;
    employeeList[] hashArr;

    public HashTab(int size) {
        this.size = size;//数组长度
        hashArr = new employeeList[size];
        for (int i = 0; i < size; i++) {
            hashArr[i] = new employeeList();
        }
    }

    public void add(employee newNode) {
        int index = hashVal(newNode.getId());//用于确定要放的数组
        hashArr[index].pushEmployee(newNode);
    }

    public employee delete(int id) {
        int index = hashVal(id);
        return hashArr[index].popEmployee(id);
    }

    public employee check(int id) {
        int index = hashVal(id);
        return hashArr[index].checkEmployee(id);
    }

    public void update(int id, String newName) {
        int index = hashVal(id);
        hashArr[index].updateEmployee(id, newName);
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            hashArr[i].list(i);
            System.out.println();
        }
    }

    //返回哈希值
    private int hashVal(int id) {
        return (id-1) % size;
    }
}

class employeeList {
    //头结点
    employee head = new employee(-1, "-1");

    public void pushEmployee(employee newNode) {
        if (isEmpty()) {
            newNode.next = null;
            head.next = newNode;
            return;
        }
        employee temp = head;
        while (true) {
            if (temp.next == null) {
                temp.next = newNode;
                return;
            }
            //该步骤确定链表是有序的
            if (temp.next.getId() > newNode.getId()) {
                newNode.next = temp.next;
                temp.next = newNode;
                return;
            }
            if (temp.next.getId() == newNode.getId()) {
                System.out.println("id已经存在");
                return;
            }
            temp = temp.next;
        }
    }

    //输入编号，获取指定雇员
    public employee popEmployee(int id) {
        if (isEmpty()) {
            try {
                throw new RuntimeException("找不到指定员工");
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        employee temp = head;
        while (true) {
            if (temp.next == null) {
                try {
                    throw new RuntimeException("找不到指定员工");
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
            if (temp.next.getId() == id) {
                employee i = temp.next;
                temp.next = temp.next.next;
                return i;
            }
            temp = temp.next;
        }
    }

    public employee checkEmployee(int id) {
        if (isEmpty()) {
            try {
                throw new RuntimeException("找不到指定员工");
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        employee temp = head;
        while (true) {
            if (temp.next == null) {
                return null;
            }
            if (temp.next.getId() == id) {
                return temp.next;
            }
            temp = temp.next;
        }
    }

    public void updateEmployee(int id, String newName) {
        employee temp = checkEmployee(id);
        if (temp == null) {//找不到数据
            try {
                throw new RuntimeException("找不到指定员工");
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        } else {
            temp.setName(newName);
            System.out.println("修改完毕");
        }
    }

    public void list(int i) {
        if (isEmpty()) {
            System.out.print("第" + (i + 1) + "条链表：");
            return;
        }
        employee temp = head.next;
        System.out.print("第" + (i + 1) + "条链表：");
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp != null) {
                System.out.print("——>" + temp.toString());
            }
            temp = temp.next;
        }
    }

    //判断链表是否为空
    public boolean isEmpty() {
        if (head.next == null) {
            return true;
        }
        return false;
    }
}

//雇员结点
class employee {
    private int id;
    private String name;
    public employee next;

    public employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "(id=" + id + ", name=" + name + ")";
    }
}
