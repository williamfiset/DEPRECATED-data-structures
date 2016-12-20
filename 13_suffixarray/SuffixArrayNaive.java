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

  public Suffix(char [] text, int index) {
    // if (text.length() >= index) throw new IllegalArgumentException();
    this.len = text.length - index;
    this.index = index;
    this.text = text;
    // System.out.println( Arrays.toString(text) + " " + index + " " + len );
  }

  // Compare the two suffixes inspired by Robert Sedgewick and Kevin Wayne
  @Override public int compareTo(Suffix other) {
    if (this == other) return 0;
    int min_len = Math.min(len, other.len);
    for (int i = 0; i < min_len; i++) {
      if (text[index+i] < other.text[other.index+i]) return -1;
      if (text[index+i] > other.text[other.index+i]) return +1;
    }
    return len - other.len;
  }

  @Override public String toString() {
    // System.out.println(Arrays.toString(text) + " " + len + " "  + index + " " + (len - index - 1));
    return new String(text, index, len);
  }

}

public class SuffixArrayNaive {

  int len;
  char[] text;

  // Contains all the suffixes of the SuffixArray
  Suffix[] suffixes;

  // Contains Longest Common Prefix (LCP) count between adjacent suffixes.
  // LCP[i] = longestCommonPrefixLength( suffixes[i], suffixes[i+1] ). Also, LCP[len-1] = 0
  int LCP [];

  public SuffixArrayNaive(String text) {
    this(text == null ? null : text.toCharArray());
  }

  // O(n^2log(n)) construction. O(nlog(n)) for sorting, but
  // each suffix takes O(n) comparison time
  public SuffixArrayNaive(char[] text) {
    if (text == null) throw new IllegalArgumentException();
    this.text = text;
    len = text.length;
    suffixes = new Suffix[len];
    for (int i = 0; i < len; i++)
      suffixes[i] = new Suffix(text, i);
    Arrays.sort(suffixes);
    kasai();
    // System.out.println(Arrays.toString(suffixes));
  }

  // Constructs the LCP (longest common prefix) array in linear time
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
