package binary_tree;

public class BinaryTree {

  private Node root;

  public void addNode(String data) {
    if (root == null) {
      root = new Node(data);
      return;
    }
    Node item = root;
    while (true) {
      if (data.compareTo(item.getData()) < 0) {
        if (item.getLeft() == null) {
          item.setLeft(new Node(data));
          return;
        } else {
          item = item.getLeft();
        }
      } else {
        if (item.getRight() == null) {
          item.setRight(new Node(data));
          return;
        } else {
          item = item.getRight();
        }
      }
    }
  }

  public boolean isContains(String data) {
    Node item = root;
    while (true) {
      if (data.compareTo(item.getData()) == 0) {
        return true;
      }
      if (data.compareTo(item.getData()) < 0) {
        if (item.getLeft() == null) {
          return false;
        } else {
          item = item.getLeft();
        }
      } else {
        if (item.getRight() == null) {
          return false;
        } else {
          item = item.getRight();
        }
      }
    }
  }

  public Node getRoot() {
    return root;
  }
}
