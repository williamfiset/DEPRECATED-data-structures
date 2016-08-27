

public interface ITrie {

  // Should return true if this string
  public boolean insert(String s);

  // Should return true if the string being inserted
  // is a new prefix in the trie
  public boolean contains(String s);

  // Should return the cont of a particular prefix
  public int count(String prefix);

  // Should clear the trie
  public void clear();

}

