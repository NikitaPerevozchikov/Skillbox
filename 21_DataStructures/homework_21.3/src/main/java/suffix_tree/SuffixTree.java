package suffix_tree;

import java.util.ArrayList;
import java.util.List;

public class SuffixTree {

  private String text;
  private ArrayList<Node> nodes;
  private Node root;

  public SuffixTree(String text) {
    this.text = text;
    nodes = new ArrayList<>();
  }

  public ArrayList<Node> getNodes() {
    return nodes;
  }

  public void build() {
    int index = sumSpaceInStart(text.substring(text.length()));
    for (String word : text.trim().split("\\s+")) {
      addNodes(nodes, word, index);
      index = index + sumSpaceInStart(text.substring(word.length())) + word.length();
    }
  }

  private void addNodes(List<Node> nodes, String word, int index) {
    for (int i = 0; i < nodes.size(); i++) {
      String same = getLongestCommonPrefix(nodes.get(i).getFragment(), word);
      if (same.length() > 0) {
        if (same.equals(word)) {
          nodes.get(i).getPosition().add(index);
          return;
        }
        if (!nodes.get(i).getFragment().equals(same)) {
          Node nextNode = new Node(nodes.get(i).getFragment().substring(same.length()));
          nextNode.getNextNodes().addAll(nodes.get(i).getNextNodes());
          nextNode.getPosition().addAll(nodes.get(i).getPosition());
          nodes.set(i, new Node(same));
          nodes.get(i).getNextNodes().add(nextNode);
        }
        Node node = new Node(word.substring(same.length()));
        node.getPosition().add(index);
        addNodes(nodes.get(i).getNextNodes(), word.substring(same.length()), index);
        return;
      }
    }
    Node node = new Node(word);
    node.getPosition().add(index);
    nodes.add(node);
  }

  private int sumSpaceInStart(String text) {
    int sum = 0;
    for (int i = 0; i < text.length(); i++) {
      if (text.charAt(i) == ' ') {
        sum++;
      } else {
        break;
      }
    }
    return sum;
  }

  private String getLongestCommonPrefix(String str1, String str2) {
    int compareLength = Math.min(str1.length(), str2.length());
    for (int i = 0; i < compareLength; i++) {
      if (str1.charAt(i) != str2.charAt(i)) {
        return str1.substring(0, i);
      }
    }
    return str1.substring(0, compareLength);
  }

  public List<Integer> search(String query) {
    ArrayList<Node> nodes = this.nodes;
    boolean isGoOn = true;
    while (isGoOn) {
      isGoOn = false;
      for (Node node : nodes ) {
        String same = getLongestCommonPrefix(node.getFragment(), query);
        if (same.length() > 0) {
          if (query.equals(node.getFragment())) {
            return node.getPosition();
          }
          nodes = node.getNextNodes();
          query = query.substring(same.length());
          isGoOn = true;
          break;
        }
      }
    }
    return new ArrayList<>();
  }

  public void printNodes(List<Node> nodes, int space) {
    for (Node node : nodes) {
      System.out.print(printSpace(space) + node.getFragment());
      if (!node.getPosition().isEmpty()) {
        System.out.print(": ");
        for (int in = 0; in < node.getPosition().size(); in++) {
          System.out.print(node.getPosition().get(in));
          if (in != node.getPosition().size() - 1) {
            System.out.print("; ");
          }
        }
      }
      System.out.println("");
      if (!node.getNextNodes().isEmpty()) {
        printNodes(node.getNextNodes(), space + 1);
      }
    }
  }

  private String printSpace(int count) {
    StringBuilder space = new StringBuilder();
    space.append("    ".repeat(Math.max(0, count)));
    if (count != 0) {
      space.append("+ ");
    }
    return space.toString();
  }
}
