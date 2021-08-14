package suffix_tree;

import java.util.ArrayList;
import java.util.List;

public class Node {

  private String fragment;
  private ArrayList<Node> nextNodes;
  private List<Integer> position;

  public Node(String fragment) {
    this.fragment = fragment;
    nextNodes = new ArrayList<>();
    position = new ArrayList<>();
  }

  public String getFragment() {
    return fragment;
  }

  public List<Integer> getPosition() {
    return position;
  }

  public ArrayList<Node> getNextNodes() {
    return nextNodes;
  }
}