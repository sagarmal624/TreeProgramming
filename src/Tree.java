import java.util.Scanner;

public class Tree {
    private Node root = null;

    public static void main(String[] args) {
        Tree tree = new Tree();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter no of element in tree");
        int n = scanner.nextInt();
        while (n != 0) {
            tree.insert(scanner.nextInt());
            n--;
        }
        System.out.println("Inorder traversal...");
        tree.inOrder(tree.root);
        System.out.println("\n" + (tree.isSibling(tree.root, 6, 14) ? "Both 6 and 14 are  sibling of each other" : "6 and 14 are Not Siblings"));
        System.out.println("\n" + (tree.isSibling(tree.root, 1, 6) ? "Both 1 and 6 are  sibling of each other" : "1 and 6 are Not Siblings"));

    }

    public boolean isSibling(Node root, Integer a, Integer b) {
        if (root == null)
            return false;
        boolean p = false, q = false, r = false;
        if (root.getLeft() != null && root.getRight() != null) {
            p = ((root.getLeft().getData() == a && root.getRight().getData() == b)
                    ||
                    (root.getLeft().getData() == b && root.getRight().getData() == a));
        }
        if (root.getLeft() != null)
            q = isSibling(root.getLeft(), a, b);
        if (root.getRight() != null)
            r = isSibling(root.getRight(), a, b);
        return (p || q || r);
    }

    public Boolean areMirror(Node t1, Node t2) {
        if (t1 == null && t2 == null)
            return true;
        if (t1 == null || t2 == null)
            return false;
        return t1.getData() == t2.getData() && areMirror(t1.getLeft(), t2.getRight()) && areMirror(t1.getRight(), t2.getLeft());
    }

    public Node createMirrorImage(Node node) {
        Node root;
        if (node == null)
            return null;
        root = new Node();
        root.setData(node.getData());
        root.setLeft(createMirrorImage(node.getRight()));
        root.setRight(createMirrorImage(node.getLeft()));
        return root;
    }

    public Integer countNodeWithInRange(Node node, Integer low, Integer high) {
        if (node == null)
            return 0;
        if (node.getData() >= low && node.getData() <= high) {
            return 1 + countNodeWithInRange(node.getLeft(), low, high) + countNodeWithInRange(node.getRight(), low, high);
        } else if (node.getData() < low)
            return countNodeWithInRange(node.getLeft(), low, high);
        else
            return countNodeWithInRange(node.getRight(), low, high);
    }

    public Integer countLeafNode(Node node) {
        if (node == null)
            return 0;
        if (node.getLeft() == null && node.getRight() == null)
            return 1;
        else
            return countLeafNode(node.getLeft()) + countLeafNode(node.getRight());
    }

    public Integer countNodeLevelWise(Node root, Integer level, Integer counter) {
        counter = 0;
        if (root == null)
            return 0;
        if (counter == level)
            return 1;
        return countNodeLevelWise(root.getLeft(), level, counter++) + countNodeLevelWise(root.getRight(), level, counter++);
    }

    public Integer countNode(Node root) {
        if (root == null)
            return 0;
        if (root.getLeft() == null && root.getRight() == null)
            return 1;
        else
            return countNode(root.getLeft()) + countNode(root.getRight()) + 1;
    }

    private Node deleteNode(Node head, int data) {
        if (head == null) {
            return null;
        }
        if (data == head.getData()) {
            if (head.getLeft() != null && head.getRight() != null) {
                Node minNode = getHightestNodeFromRight(head.getRight());
                head.setData(minNode.getData());
                head.setRight(deleteNode(head.getRight(), minNode.getData()));
            } else if (head.getLeft() == null)
                return head.getRight();
            else
                return head.getLeft();

        } else if (data < head.getData())
            head.setLeft(deleteNode(head.getLeft(), data));
        else
            head.setRight(deleteNode(head.getRight(), data));
        return head;
    }

    public Node getHightestNodeFromRight(Node node) {
        if (node.getLeft() == null) {
            return node;
        } else
            return getHightestNodeFromRight(node.getLeft());
    }

    public void inOrder(Node head) {
        if (head != null) {
            inOrder(head.getLeft());
            System.out.print("->" + head.getData());
            inOrder(head.getRight());
        }
    }

    public void insert(Integer key) {
        root = insertRecursive(root, key);
    }

    public Node insertRecursive(Node head, Integer key) {
        if (head == null) {
            head = new Node();
            head.setData(key);
            return head;
        }
        if (key < head.getData()) {
            head.setLeft(insertRecursive(head.getLeft(), key));
        } else if (key > head.getData())
            head.setRight(insertRecursive(head.getRight(), key));
        return head;
    }

    public Boolean searchByIterativeApproach(Node head, Integer searchKey) {
        while (head != null) {
            if (searchKey == head.getData()) {
                return true;
            }
            if (searchKey < head.getData()) {
                head = head.getLeft();
            } else
                head = head.getRight();

        }
        return false;
    }

    public Node searchByRecursive(Node head, Integer searchKey) {
        if (head == null)
            return null;
        if (searchKey < head.getData())
            return searchByRecursive(head.getLeft(), searchKey);
        else if (searchKey > head.getData())
            return searchByRecursive(head.getRight(), searchKey);
        else
            return head;

    }

    public Integer min(Node head) {
        while (head.getLeft() != null) {
            head = head.getLeft();
        }
        return head.getData();
    }


}
