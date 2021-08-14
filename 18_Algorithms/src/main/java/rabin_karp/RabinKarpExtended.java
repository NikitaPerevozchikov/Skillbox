package rabin_karp;

import java.util.ArrayList;
import java.util.HashMap;

public class RabinKarpExtended {

  public HashMap<Character, Integer> abc;
  private String text;

  public RabinKarpExtended(String text) {
    this.text = text;
    createIndex();
  }

  public ArrayList<Integer> search(String query) {
    ArrayList<Integer> result = new ArrayList<>();
    Integer x = generationHash(query, 0, query.length());
    if (x < 0) {
      return result;
    }
    for (int i = 0; i < text.length() - query.length(); i++) {
      Integer r = generationHash(text, i, i + query.length());
      if (r.equals(x)) {
        addInArray(result, i, i + query.length());
        break;
      }
    }
    return result;
  }

  private void createIndex() {
    abc = new HashMap<>();
    for (int i = 0; i < text.length(); i++) {
      if (!abc.containsKey(text.charAt(i))) {
        abc.put(text.charAt(i), i + 1);
      }
    }
  }

  private int generationHash(String s, int indexStart, int indexFinish) {
    int hash = 0;
    for (int i = indexStart, j = 0; i < indexFinish; i++, j++) {
      char c = s.charAt(i);
      if (!abc.containsKey(c)) {
        return -1;
      }
      hash += (int) Math.pow(abc.get(s.charAt(i)) * 11, j);
    }
    return hash;
  }

  private void addInArray(ArrayList<Integer> array, int indexStart, int indexFinish) {
    for (int i = indexStart; i < indexFinish; i++) {
      array.add(i);
    }
  }
}
