import suffix_tree.SuffixTree;

public class Main {

  public static void main(String[] args) {
    SuffixTree suffixTree = new SuffixTree(
        "гриб город гроза год горб дом добро дело деталь добыча дача дама зверь зов забор змей звон");
    suffixTree.build();
    suffixTree.printNodes(suffixTree.getNodes(), 0);
    System.out.println(suffixTree.search("дом"));
  }
}
