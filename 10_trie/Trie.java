
import java.util.Map;
import java.util.HashMap;

public class Trie implements ITrie {

  private Node root = new Node('\0');

  private static class Node {

    char ch;
    int count = 0;
    boolean isWordEnding = false;
    Map<Character, Node> children = new HashMap<>();

    public Node(char ch) { this.ch = ch; }

    public void addChild(Node node, char c) {
      children.put(c, node);
    }

  }

  // Returns true if the string being inserted
  // contains a prefix already in the trie  
  public boolean insert(String key) {

    if (key == null)
      throw new IllegalArgumentException("Null not permitted in trie");

    Node node = root;
    boolean created_new_node = false;
    boolean is_prefix = false;

    // Process each character one at a time
    for (int i = 0; i < key.length(); ++i) {

      char ch = key.charAt(i);
      Node nextNode = node.children.get(ch);

      // The next character in this string does not yet exist in trie
      if (nextNode == null) {

        nextNode = new Node(ch);
        node.addChild(nextNode, ch);
        created_new_node = true;

      // Next character exists in trie.
      } else {
        if (nextNode.isWordEnding)
          is_prefix = true;
      }

      node = nextNode;
      node.count++;

    }

    // The root itself is not a word ending. It is simply a placeholder.
    if (node != root)
      node.isWordEnding = true;

    return is_prefix || !created_new_node;

  }

  // Returns true if this string is contained inside the trie
  public boolean contains(String key) {
    return count(key) != 0;
  }

  // Returns the count of a particular prefix
  public int count(String key) {

    if (key == null)
      throw new IllegalArgumentException("Null not permitted");

    Node node = root;

    // Dig down into trie until we reach the bottom or stop 
    // early because the string we're looking for doesn't exist
    for(int i = 0; i < key.length(); i++) {
      char ch = key.charAt(i);
      if (node == null) return 0;
      node = node.children.get(ch);
    }

    if (node != null) 
      return node.count;
    return 0;

  }

  // Clear the trie
  public void clear() {

    root.children = null;
    root = new Node('\0');

  }

}







