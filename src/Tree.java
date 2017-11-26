public class Tree {
    private Node root = null;

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.insert(8);
        tree.insert(3);
        tree.insert(10);
        tree.insert(1);
        tree.insert(6);
        tree.insert(4);
        tree.insert(7);
        tree.insert(14);
        tree.insert(13);
        tree.inOrder(tree.root);
        System.out.println();
        System.out.println(tree.min(tree.root));
    }

    public void inOrder(Node head) {
        if (head != null) {
            inOrder(head.getLeft());
            System.out.print("->" + head.getData());
            inOrder(head.getRight());
        }
    }

    void insert(int key) {
        root = insertRec(root, key);
    }

    Node insertRec(Node root, int key) {

        if (root == null) {
            root = new Node();
            root.setData(key);
            return root;
        }

        if (key < root.getData())
            root.setLeft(insertRec(root.getLeft(), key));
        else if (key > root.getData())
            root.setRight(insertRec(root.getRight(), key));

        return root;
    }

    public Integer max(Node head) {
        if (head != null)
            while (head.getRight() != null) {
                head = head.getRight();
            }
        return head.getData();
    }

    public Integer min(Node head) {
        if (head != null)
            while (head.getLeft() != null) {
                head = head.getLeft();
            }
        return head.getData();
    }

    public Integer countNodeLevelWise(Node head, Integer counter, Integer desiredLevel) {

        if (head == null) {
            return 0;
        }
        if (counter == desiredLevel) {
            return 1;
        }
        return countNodeLevelWise(head.getLeft(), counter + 1, desiredLevel) + countNodeLevelWise(head.getRight(), counter + 1, desiredLevel);
    }


    public Integer countNode(Node root) {
        if (root == null)
            return 0;
        if (root.getLeft() == null && root.getRight() == null)
            return 1;
        else
            return (countNode(root.getLeft()) + countNode(root.getRight()) + 1);
    }

    public Integer countLeafNode(Node root) {
        if (root == null)
            return 0;
        if (root.getLeft() == null && root.getRight() == null)
            return 1;
        else
            return (countLeafNode(root.getLeft()) + countLeafNode(root.getRight()));
    }

}
