
import java.util.Map;
import java.util.HashMap;

public class Trie implements ITrie {

  private static class Node {

    char ch;
    int count = 0;
    boolean isLeaf = false;
    Map<Character, Node> children = new HashMap<>();

    public Node(char ch) { this.ch = ch; }

    public void addChild(Node node, char c) {
      children.put(c, node);
    }

    @Override public String toString() {
      return ch + " - " + Integer.toString(count);
    }

  }

  Node root = new Node('\0');

  // Returns true if the string being inserted
  // is already a prefix in the trie  
  public boolean insert(String key) {

    if (key == null)
      throw new IllegalArgumentException("Null not permitted in trie");

    Node node = root;
    boolean created_new_node = false;
    boolean is_prefix = false;

    for (int i = 0; i < key.length(); ++i) {

      char ch = key.charAt(i);
      Node nextNode = node.children.get(ch);

      // The next character in the sequence does not yet exist in trie
      if (nextNode == null) {

        nextNode = new Node(ch);
        node.addChild(nextNode, ch);
        created_new_node = true;

      // next character exists in trie
      } else {
        if (nextNode.isLeaf)
          is_prefix = true;
      }

      node = nextNode;
      node.count++;

    }

    // The root is not a leaf it's just a placeholder
    if (node != root)
      node.isLeaf = true;

    return is_prefix || !created_new_node;

  }

  // Should return true if part of this string 
  // is a prefix of another word already in the trie
  public boolean contains(String key) {

    if (key == null) return false;
      
    Node node = root;
    for(int i = 0; i < key.length(); i++) {

      char ch = key.charAt(i);
      if (node == null) return false;
      node = node.children.get(ch);

    }
    return node != null;

  }

  // Should return the cont of a particular prefix
  public int count(String key) {

    if (key == null)
      throw new IllegalArgumentException("Null not permitted");

    Node node = root;
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







