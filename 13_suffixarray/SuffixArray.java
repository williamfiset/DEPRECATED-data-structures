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

import java.util.Arrays;

class Suffix implements Comparable <Suffix> {

  // Starting position of suffix in text
  final int index, len;
  final char [] text;
  RankPair rank;

  public Suffix(char [] text, int index) {

    if (index >= text.length) throw new IllegalArgumentException();
    this.len = text.length - index;
    this.index = index;
    this.text = text;
    
    rank = new RankPair(text, index);
  }

  @Override public int compareTo(Suffix other) {
    return rank.compareTo(other.rank);
  }

  @Override public String toString() {
    return new String(text, index, len);
  }

}

class RankPair implements Comparable <RankPair> {

  int rank1, rank2;

  public RankPair() { }

  // Assign an initial rank to this suffix
  public RankPair(char[] text, int index) {
    rank1 = text[index];
    if (index+1 < text.length)
      rank2 = text[index+1];
    else rank2 = -1;
  }

  // Update rankPair here?

  // Sorts by the first character, then by the second
  @Override public int compareTo(RankPair other) {
    int cmp = Integer.compare(rank1, other.rank1);
    if (cmp == 0) return Integer.compare(rank2, other.rank2);
    return cmp;
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
  // to O(nlog(n)) with Radix sort some say this made the algorithm slower in practice
  private void constructSuffixArray() {

    // Tracks the position of the shuffled suffixes 
    // in their partially sorted state.
    int [] suffix_pos = new int[len];

    // Initially sort the suffixes by their first two characters
    Arrays.sort(suffixes);

    for(int pos = 4; (pos/2) < len; pos *= 2) {

      int new_rank = 0;
      int prev_rank = suffixes[0].rank.rank1;
      suffixes[0].rank.rank1 = 0;
      suffix_pos[suffixes[0].index] = 0;

      // Update rank1
      for (int i = 1; i < len; i++) {

        Suffix suffix = suffixes[i];
        Suffix prev_suffix = suffixes[i-1];
        suffix_pos[ suffix.index ] = i;

        if ( (suffix.rank.rank1 == prev_rank) &&
             (suffix.rank.rank2 == prev_suffix.rank.rank2) ) {
          prev_rank = suffix.rank.rank1;
          suffix.rank.rank1 = new_rank;
        } else {
          prev_rank = suffix.rank.rank1;
          suffix.rank.rank1 = ++new_rank;
        }

      }

      // Update rank2
      for (int i = 0; i < len; i++) {
        Suffix suffix = suffixes[i];
        int nextIndex = suffix.index + pos / 2;
        if (nextIndex < len) {
          Suffix nextSuffix = suffixes[suffix_pos[nextIndex]];
          suffix.rank.rank2 = nextSuffix.rank.rank1;
        } else suffix.rank.rank2 = -1;
      }

      Arrays.sort(suffixes);

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



  // Finds the LRS (Longest Repeated Substring) that occurs in a string
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

  public static void main(String[] args) {
    
    // SuffixArrayNaive sa = new SuffixArrayNaive("abc");
    // System.out.println( Arrays.toString(sa.suffixes) );
    // System.out.println( Arrays.toString(sa.LCP) );
    // System.out.println(sa.lrs());


  }


}

// To build faster SA refer to http://zork.net/~st/jottings/sais.html
