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


class SuffixArray {

  // Helper class which sorts suffix ranks
  static class SuffixRank implements Comparable <SuffixRank> {

    int firstHalf, secondHalf, originalIndex;

    // Sort Suffix ranks first on the first half then the second half
    @Override public int compareTo(SuffixRank other) {
      int cmp = Integer.compare(firstHalf, other.firstHalf);
      if (cmp == 0) return Integer.compare(secondHalf, other.secondHalf);
      return cmp;
    }

  }

  // Size of the suffix array
  int N; 

  // T is the text
  char[] T;

  // Suffix array. Contains the indexes of sorted suffixes.
  int[] sa;

  // Contains Longest Common Prefix (LCP) count between adjacent suffixes.
  // lcp[i] = longestCommonPrefixLength( suffixes[i], suffixes[i+1] ).
  // Also, LCP[len-1] = 0
  int [] lcp;

  public SuffixArray(String text) {
    this(text == null ? null : text.toCharArray());
  }

  public SuffixArray(char[] text) {
    if (text == null) throw new IllegalArgumentException();
    T = text; N = text.length;
    constructSuffixArray();
    kasai();
  }

  // Complements of
  // https://www.hackerrank.com/challenges/string-similarity/topics/suffix-array
  public void constructSuffixArray() {

    sa = new int[N];

    // Maintain suffix ranks in both a matrix with two rows containing the
    // current and last rank information as well as some sortable rank objects
    int [][] suffixRanks = new int[2][N];
    SuffixRank[] ranks = new SuffixRank[N];

    // Assign a numerical value to each character in the text
    for (int i = 0; i < N; i++) {
      suffixRanks[0][i] = T[i];
      ranks[i] = new SuffixRank();
    }

    // O(logn)
    for(int pos = 1; pos < N; pos *= 2) {

      for(int i = 0; i < N; i++) {
        SuffixRank suffixRank = ranks[i];
        suffixRank.firstHalf  = suffixRanks[0][i];
        suffixRank.secondHalf = i+pos < N ? suffixRanks[0][i+pos] : -1;
        suffixRank.originalIndex = i;
      }
      
      // O(nlogn)
      java.util.Arrays.sort(ranks);

      suffixRanks[1][ranks[0].originalIndex] = 0;
      for (int i = 1, newRank = 0; i < N; i++ ) {
        
        SuffixRank lastSuffixRank = ranks[i-1];
        SuffixRank currSuffixRank = ranks[i];

        if (currSuffixRank.firstHalf  != lastSuffixRank.firstHalf ||
            currSuffixRank.secondHalf != lastSuffixRank.secondHalf)
          newRank++;

        suffixRanks[1][currSuffixRank.originalIndex] = newRank;

      }

      // Place top row (current row) to be the last row
      suffixRanks[0] = suffixRanks[1];

    }

    // Fill suffix array
    for (int i = 0; i < N; i++) {
      sa[i] = ranks[i].originalIndex;
      ranks[i] = null;
    }

    // Cleanup
    suffixRanks[0] = suffixRanks[1] = null;
    suffixRanks = null;
    ranks = null;

  }

  // Constructs the LCP (longest common prefix) array in linear time - O(n)
  // http://www.mi.fu-berlin.de/wiki/pub/ABI/RnaSeqP4/suffix-array.pdf
  private void kasai() {

    lcp = new int[N];
    
    // Compute inverse index values
    int [] inv = new int[N];
    for (int i = 0; i < N; i++)
      inv[sa[i]] = i;
    
    // Current lcp length
    int len = 0;

    for (int i = 0; i < N; i++) {
      if (inv[i] > 0) {

        // Get the index of where the suffix below is
        int k = sa[inv[i]-1];

        // Compute lcp length. For most loops this is O(1)
        while( (i + len < N) && (k + len < N) && T[i+len] == T[k+len] )
          len++;

        lcp[inv[i]-1] = len;
        if (len > 0) len--;

      }
    }

  }

  // Runs on O(mlog(n)) where m is the length of the substring
  // and n is the length of the text.
  // NOTE: This is the naive implementation. There exists an
  // implementation which runs in O(m + log(n)) time
  public boolean contains(String substr) {

    if (substr == null) return false;
    if (substr.equals("")) return true;

    String suffix_str;
    int lo = 0, hi = N - 1;
    int substr_len = substr.length();

    while( lo <= hi ) {

      int mid = (lo + hi) / 2;
      int suffix_index = sa[mid];
      int suffix_len = N - suffix_index;

      // Extract part of the suffix we need to compare
      if (suffix_len <= substr_len) suffix_str = new String(T, suffix_index, suffix_len);
      else suffix_str = new String(T, suffix_index, substr_len);
       
      int cmp = suffix_str.compareTo(substr);

      // Found a match
      if ( cmp == 0 ) {
        // To find the first occurrence linear scan up/down
        // from here or keep doing binary search
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

    int index = 0;
    int max_len = 0;

    for (int i = 0; i < N; i++) {
      if (lcp[i] > max_len) {
        max_len = lcp[i];
        index = sa[i];
      }
    }

    return max_len <= 1 ? null : new String(T, index, max_len);

  }

  // Find the Longest Common Substring (LCS) between two strings (generalizes to more strings if needed)
  // NOTE: Make sure the separator is a unique character that is not found in s1 or s2
  public static String lcs(String s1, String s2, char separator) {
    
    int longestLen = 0, index = -1;
    int L1 = s1.length(), L2 = s2.length();
    SuffixArray suffix_array = new SuffixArray(s1+separator+s2);
    int n = L1 + L2 + 1;

    for (int i = 1; i < n; i++) {
      
      int suffix1_len = n - suffix_array.sa[i-1];
      int suffix2_len = n - suffix_array.sa[i];

      // Make sure the two adjacent suffixes are from the two different strings s1 & s2
      // Update the location of the lcs if a better one is found
      if ( (suffix1_len) >= L2 || (suffix2_len) >= L2 ||
           (suffix1_len) <= L2 || (suffix2_len) <= L2 ) {
        int lcp_length = suffix_array.lcp[i-1];
        if (lcp_length > longestLen) {
          longestLen = lcp_length;
          index = suffix_array.sa[i-1];
        }
      }

    }

    if (index == -1) return null;
    return new String(suffix_array.T, index, longestLen);

  }

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append( java.util.Arrays.toString(sa) + "\n" );
    for(int i = 0; i < N; i++) {
      int index  = sa[i];
      int length = N - index;
      sb.append(new String(T, index, length) + "\n");
    }
    return sb.toString();
  }

}

