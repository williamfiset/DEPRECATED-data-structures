package com.williamfiset.datastructures.suffixarray;

public abstract class SuffixArray {
  
  protected static final int DEFAULT_ALPHABET_SHIFT = 0;
  protected static final int DEFAULT_ALPHABET_SIZE = 256;
  
  // Length of the suffix array
  public final int N;
  
  protected int shift = DEFAULT_ALPHABET_SHIFT;
  
  protected int alphabetSize = DEFAULT_ALPHABET_SIZE;

  // T is the text
  public int[] T;

  // The sorted suffix array values.
  public int[] sa;
  
  // Longest Common Prefix array
  public int [] lcp;

  // Designated constructor
  public SuffixArray(int[] text, int shift, int alphabetSize) {
    
    if (text == null || alphabetSize <= 0) 
      throw new IllegalArgumentException();
    
    this.T = text;
    this.N = text.length;
    
    this.shift = shift;
    this.alphabetSize = alphabetSize;
    
    // Build suffix array
    construct();
    
    // Build LCP array
    kasai();
    
  }
  
  protected static int[] toIntArray(String s) {
    if (s == null) return null;
    int[] text = new int[s.length()];
    for(int i = 0; i < s.length(); i++)
      text[i] = s.charAt(i);
    return text;
  }
  
  // The suffix array construction algorithm is left undefined 
  // as there are multiple ways to do this.
  protected abstract void construct();

  // Use Kasai algorithm to build LCP array
  // http://www.mi.fu-berlin.de/wiki/pub/ABI/RnaSeqP4/suffix-array.pdf
  private void kasai() {
    lcp = new int[N];
    int [] inv = new int[N];
    for (int i = 0; i < N; i++) inv[sa[i]] = i;
    for (int i = 0, len = 0; i < N; i++) {
      if (inv[i] > 0) {
        int k = sa[inv[i]-1];
        while((i+len < N) && (k+len < N) && T[i+len] == T[k+len]) len++;
        lcp[inv[i]] = len;
        if (len > 0) len--;
      }
    }
  }

  @Override public String toString() {

    StringBuilder sb = new StringBuilder();
    sb.append("-----i-----SA-----LCP---Suffix\n");

    for(int i = 0; i < N; i++) {
      int suffixLen = N - sa[i];
      char[] suffixArray = new char[suffixLen];
      for (int j = sa[i], k = 0; j < N; j++, k++)
        suffixArray[k] = (char)(T[j] - shift);
      String suffix = new String(suffixArray);
      String formattedStr = String.format("% 7d % 7d % 7d %s\n", i, sa[i], lcp[i], suffix);
      sb.append(formattedStr);
    }

    return sb.toString();

  }
  
}