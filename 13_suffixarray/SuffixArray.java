/*
Good read: 
http://www.cs.yale.edu/homes/aspnes/pinewiki/SuffixArrays.html

Generally speaking, suffix arrays are used to do multiple queries 
efficiently on one piece of data rather than to do one operation 
then move on to another piece of text.

Some things suffix trees are great for are:
1) Searching if a substring occurs in the text
2) Finding all occurrences of a substring in the larger text
3) Longest repeated substring
4) Most frequently occurring substrings
5) Longest duplicate substrings
6) 

*/

class Suffix implements Comparable <Suffix> {

  // Ranks used for sorting suffixes during SuffixArray creation
  int rank1, rank2;

  // Starting position of suffix in text
  final int index, len;
  final char [] text;

  public Suffix(char [] text, int index) {

    if (index >= text.length) throw new IllegalArgumentException();
    this.len = text.length - index;
    this.index = index;
    this.text = text;
    
    rank1 = text[index];
    rank2 = (index+1 < text.length) ? text[index+1] : -1;

  }

  @Override public int compareTo(Suffix other) {
    int cmp = Integer.compare(rank1, other.rank1);
    if (cmp == 0) return Integer.compare(rank2, other.rank2);
    return cmp;
  }

  @Override public String toString() {
    return new String(text, index, len);
  }

}

public class SuffixArray {

  int len;
  char[] text;

  // Contains all the suffixes of the SuffixArray
  Suffix[] suffixes;

  // Contains Longest Common Prefix (LCP) count between adjacent suffixes.
  // LCP[i] = longestCommonPrefixLength( suffixes[i], suffixes[i+1] ). Also, LCP[len-1] = 0
  int LCP [];

  public SuffixArray(String text) {
    this(text == null ? null : text.toCharArray());
  }

  public SuffixArray(char[] text) {
    if (text == null) throw new IllegalArgumentException();
    this.text = text; len = text.length;
    suffixes = new Suffix[len];
    for (int i = 0; i < len; i++)
      suffixes[i] = new Suffix(text, i);
    constructSuffixArray();
    kasai();
  }

  // Inspired by implementation of:
  // http://www.geeksforgeeks.org/suffix-array-set-2-a-nlognlogn-algorithm/
  // Runs in O(nlog(n)log(n)). Although the theoretical complexity can be reduced 
  // to O(nlog(n)) with Radix sort some say this made the algorithm slower in practice.
  // 
  // NOTE: This code can probably be simplified a lot..
  //
  private void constructSuffixArray() {

    // Tracks the position of the shuffled suffixes 
    // in their partially sorted state.
    int [] suffix_pos = new int[len];

    // Initially sort the suffixes by their first two characters
    java.util.Arrays.sort(suffixes);

    // O(logn)
    for(int pos = 2; pos < len; pos *= 2) {

      Suffix firstSuffix = suffixes[0];
      int prev_rank = firstSuffix.rank1;
      int new_rank = suffix_pos[firstSuffix.index] = firstSuffix.rank1 = 0;

      // Update rank1
      for (int i = 1; i < len; i++) {

        Suffix suffix = suffixes[i];
        Suffix prev_suffix = suffixes[i-1];
        suffix_pos[ suffix.index ] = i;

        if ( (suffix.rank1 == prev_rank) &&
             (suffix.rank2 == prev_suffix.rank2) ) {
          prev_rank = suffix.rank1;
          suffix.rank1 = new_rank;
        } else {
          prev_rank = suffix.rank1;
          suffix.rank1 = ++new_rank;
        }

      }

      // Update rank2
      for (int i = 0; i < len; i++) {
        Suffix suffix = suffixes[i];
        int nextIndex = suffix.index + pos;
        if (nextIndex < len) {
          Suffix nextSuffix = suffixes[suffix_pos[nextIndex]];
          suffix.rank2 = nextSuffix.rank1;
        } else suffix.rank2 = -1;
      }

      // O(nlogn)
      java.util.Arrays.sort(suffixes);

    }

  }

  // Constructs the LCP (longest common prefix) array in linear time - O(n)
  // http://www.mi.fu-berlin.de/wiki/pub/ABI/RnaSeqP4/suffix-array.pdf
  private void kasai() {

    LCP = new int[len];
    
    // Compute inverse index values
    int [] inv = new int[len];
    for (int i = 0; i < len; i++)
      inv[suffixes[i].index] = i;
    
    int lcp_len = 0;

    for (int i = 0; i < len; i++) {
      if (inv[i] > 0) {

        // Get the index of where the suffix below is
        int k = suffixes[inv[i] - 1].index;

        // Compute lcp length. For most loops this is O(1)
        while( (i + lcp_len < len) && (k + lcp_len < len) &&
               text[i+lcp_len] == text[k+lcp_len] )
          lcp_len++;

        LCP[inv[i]-1] = lcp_len;
        if (lcp_len > 0) lcp_len--;

      }
    }

  }

  public int[] getSuffixPositions() {
    int [] ar = new int[len];
    for (int i = 0; i < len; i++)
      ar[i] = suffixes[i].index;
    return ar;
  }

  // Runs on O(mlog(n)) where m is the length of the substring
  // and n is the length of the text.
  // NOTE: This is the naive implementation. There exists an
  // implementation which runs in O(m + log(n)) time
  public boolean contains(String substr) {

    if (substr == null) return false;

    String suffix_str;
    int lo = 0, hi = len - 1;
    int substr_len = substr.length();

    while( lo <= hi ) {

      int mid = (lo + hi) / 2;
      Suffix suffix = suffixes[mid];

      // Extract part of the suffix we need to compare
      if (suffix.len <= substr_len) suffix_str = suffix.toString();
      else suffix_str = new String(text, suffix.index, substr_len);
       
      int cmp = suffix_str.compareTo(substr);

      // Found a match
      if ( cmp == 0 ) {
        // To find the first occurrence linear scan down
        // or keep doing binary search
        return true;
      
      // Substring is found above
      } else if (cmp < 0) {
        lo = mid + 1;

      // Substring is found below
      } else {
        hi = mid - 1;
      }

    }

    return false;

  }

  // Finds the LRS (Longest Repeated Substring) that occurs in a string.
  // Traditionally we are only interested in sub strings that appear at
  // least twice, so this method returns null if this is the case.
  public String lrs() {

    int max_len = 0;
    Suffix suffix = null;

    for (int i = 0; i < len; i++) {
      if (LCP[i] > max_len) {
        max_len = LCP[i];
        suffix = suffixes[i];
      }
    }

    return suffix == null ? null : suffix.toString().substring(0, max_len);

  }

  // Find the Longest Common Substring (LCS) between two strings (generalizes to more strings if needed)
  // NOTE: Make sure the separator is a unique character that is not found in s1 or s2
  public static String lcs(String s1, String s2, char separator) {
    
    int longestLen = 0, index = -1;
    int L1 = s1.length(), L2 = s2.length();
    SuffixArray sa = new SuffixArray(s1+separator+s2);

    for (int i = 1; i < sa.len; i++) {
      
      Suffix suffix1 = sa.suffixes[i-1];
      Suffix suffix2 = sa.suffixes[i];

      // System.out.printf("%d %d %s %s %d %d\n", L1, L2, suffix1.toString(), suffix2.toString(), suffix1.len, suffix2.len );

      // Make sure the two adjacent suffixes are from the two different strings s1 & s2
      // Update the location of the lcs if a better one is found
      if ( (suffix1.len) >= L2 || (suffix2.len) >= L2 ||
           (suffix1.len) <= L2 || (suffix2.len) <= L2 ) {
        int lcp_length = sa.LCP[i-1];
        if (lcp_length > longestLen) {
          longestLen = lcp_length;
          index = suffix1.index;
        }
      }

    }

    if (index == -1) return null;
    return new String(sa.text, index, longestLen);

  }

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Suffix suffix : suffixes)
      sb.append( suffix.toString() + "\n");
    return sb.toString();
  }

  public static void main(String[] args) {
    
    String s = "aabaab";
    System.out.println( new SuffixArray(s) );
    System.out.println( java.util.Arrays.toString( (new SuffixArray(s)).LCP ) );

    s = "aaaaa";
    System.out.println( new SuffixArray(s) );
    System.out.println( java.util.Arrays.toString( (new SuffixArray(s)).LCP ) );

    s = "AaAaA";
    System.out.println( new SuffixArray(s) );
    System.out.println( java.util.Arrays.toString( (new SuffixArray(s)).LCP ) );

  }


}

