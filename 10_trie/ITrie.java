

public interface ITrie {

  public boolean insert(String key);

  public boolean insert(String key, int numInserts);

  public boolean delete(String key);

  public boolean delete(String key, int numDeletions);

  public boolean contains(String key);
  
  public int count(String key);

  public void clear();

}

