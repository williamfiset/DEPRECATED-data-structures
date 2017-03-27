/**
 *
 * Generally speaking, suffix arrays are used to do multiple queries 
 * efficiently on one piece of data rather than to do one operation 
 * then move on to another piece of text.
 *
 * Good read: 
 * http://www.cs.yale.edu/homes/aspnes/pinewiki/SuffixArrays.html
 *
 **/

import java.util.*;

class SuffixArray {

  // Helper class which sorts suffix ranks
  static class SuffixRankTuple implements Comparable <SuffixRankTuple> {

    int firstHalf, secondHalf, originalIndex;

    // Sort Suffix ranks first on the first half then the second half
    @Override public int compareTo(SuffixRankTuple other) {
      int cmp = Integer.compare(firstHalf, other.firstHalf);
      if (cmp == 0) return Integer.compare(secondHalf, other.secondHalf);
      return cmp;
    }

    @Override public String toString() {
      return originalIndex + " -> (" + firstHalf + ", " + secondHalf + ")";
    }

  }

  // Size of the suffix array
  int N; 

  // T is the text
  int[] T;

  // Suffix array. Contains the indexes of sorted suffixes.
  int[] sa;

  // Contains Longest Common Prefix (LCP) count between adjacent suffixes.
  // lcp[i] = longestCommonPrefixLength( suffixes[i], suffixes[i+1] ).
  // Also, LCP[len-1] = 0
  int [] lcp;

  public SuffixArray(String text) {
    if (text == null) throw new IllegalArgumentException();
    T = charArToIntAr(text.toCharArray());
    N = text.length();    
    constructSuffixArray();
    kasai();    
  }

  private int[] charArToIntAr(char[] chrs) {
    int[] intAr = new int[chrs.length];
    for(int i = 0; i < chrs.length; i++)
      intAr[i] = chrs[i];
    return intAr;
  }

  public SuffixArray(int[] text) {
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
    SuffixRankTuple[] ranks = new SuffixRankTuple[N];

    // Assign a numerical value to each character in the text
    for (int i = 0; i < N; i++) {
      suffixRanks[0][i] = T[i];
      ranks[i] = new SuffixRankTuple();
    }

    // O(logn)
    for(int pos = 1; pos < N; pos *= 2) {

      for(int i = 0; i < N; i++) {
        SuffixRankTuple suffixRank = ranks[i];
        suffixRank.firstHalf  = suffixRanks[0][i];
        suffixRank.secondHalf = i+pos < N ? suffixRanks[0][i+pos] : -1;
        suffixRank.originalIndex = i;
      }

      // O(nlogn)
      java.util.Arrays.sort(ranks);

      int newRank = 0;
      suffixRanks[1][ranks[0].originalIndex] = 0;

      for (int i = 1; i < N; i++ ) {
        
        SuffixRankTuple lastSuffixRank = ranks[i-1];
        SuffixRankTuple currSuffixRank = ranks[i];

        // If the first half differs from the second half
        if (currSuffixRank.firstHalf  != lastSuffixRank.firstHalf ||
            currSuffixRank.secondHalf != lastSuffixRank.secondHalf)
          newRank++;

        suffixRanks[1][currSuffixRank.originalIndex] = newRank;

      }

      // Place top row (current row) to be the last row
      suffixRanks[0] = suffixRanks[1];

      // Optimization to stop early 
      if (newRank == N-1) break;

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

  // Finds the LRS(s) (Longest Repeated Substring) that occurs in a string.
  // Traditionally we are only interested in substrings that appear at
  // least twice, so this method returns an empty set if this is the case.
  // @return an ordered set of longest repeated substrings
  public TreeSet <String> lrs() {

    int max_len = 0;
    TreeSet <String> lrss = new TreeSet<>();

    for (int i = 0; i < N; i++) {
      if (lcp[i] > 0 && lcp[i] >= max_len) {
        
        // We found a longer LRS
        if ( lcp[i] > max_len )
          lrss.clear();
        
        // Append substring to the list and update max
        max_len = lcp[i];
        lrss.add( new String(T, sa[i], max_len) );

      }
    }

    return lrss;

  }

  // Q: what is the time complexity?
  public static TreeSet <String> lcs(String [] strs, final int K) {

    if (K <= 1) throw new IllegalArgumentException("K must be greater than or equal to 2!");

    TreeSet <String> lcss = new TreeSet<>();
    if (strs == null || strs.length <= 1) return lcss;

    // L is the concatenation length
    int L = 0;
    final int NUM_SENTINELS = strs.length, N = strs.length;
    for(int i = 0; i < N; i++) L += strs[i].length() + 1;

    int[] suffixMap = new int[L];
    int LOWEST_ASCII = Integer.MAX_VALUE;

    // Compute the total length of the concatenated strings 
    // including the sentinel values we're going to insert.
    for (int i = 0, k = 0; i < strs.length; i++) {
      
      String str = strs[i];
      
      for (int j = 0; j < str.length(); j++) {
        int asciiVal = str.charAt(j);
        if (asciiVal < LOWEST_ASCII) LOWEST_ASCII = asciiVal;
        suffixMap[k++] = i;
      }

      // Record that the sentinel belongs to string i
      suffixMap[k++] = i;

    }

    final int SHIFT = LOWEST_ASCII + NUM_SENTINELS + 1;

    int sentinel = 0;
    int[] T = new int[L];

    // Construct the new text with the shifted values and the sentinels
    for(int i = 0, k = 0; i < N; i++) {
      String str = strs[i];
      for (int j = 0; j < str.length(); j++)
        T[k++] = ((int)str.charAt(j)) + SHIFT;
      T[k++] = sentinel++;
    }

    // System.out.println(Arrays.toString(strs));
    // System.out.println(Arrays.toString(suffixMap));
    // System.out.println(Arrays.toString(T));

    SuffixArray sa = new SuffixArray(T);
    CompactSegmentTree segTree = new CompactSegmentTree(sa.lcp);
    // System.out.println(toString(sa, SHIFT));

    // Assign each string a color and maintain the color count within the window
    Map <Integer, Integer> windowColorCount = new HashMap<>();
    Set <Integer> windowColors = new HashSet<>();

    // Start the sliding window at the number of sentinels because those
    // all get sorted first and we want to ignore them
    int lo = NUM_SENTINELS, hi = NUM_SENTINELS, bestLCSLength = 0;

    // Add the first color
    int firstColor = suffixMap[sa.sa[hi]];
    windowColors.add(firstColor);
    windowColorCount.put(firstColor, 1);

    // Maintain a sliding window between lo and hi
    while(hi < L) {

      // System.out.printf("%d %d\n", lo, hi);

      int uniqueColors = windowColors.size();

      // Attempt to update the LCS
      if (uniqueColors >= K) {

        // Find the window LCP using a segment tree
        // in the range of [lo, hi), O(log(n))
        int windowLCP = (int) segTree.query(lo, hi);

        if (bestLCSLength < windowLCP) {
          bestLCSLength = windowLCP;
          lcss.clear();
        }

        if (bestLCSLength == windowLCP) {

          // Construct the current LCS within the window interval
          int pos = sa.sa[lo];
          char[] lcs = new char[windowLCP];
          for (int i = 0; i < windowLCP; i++) lcs[i] = (char)(T[pos+i] - SHIFT);

          lcss.add(new String(lcs));

          // The original strings to which this longest common
          // substring belongs to can be found in the windowColors set
          // Just use those indexes on the 'strs' array

        }

        // Update the colors in our window
        int lastColor = suffixMap[sa.sa[lo]];
        Integer colorCount = windowColorCount.get(lastColor);
        if (colorCount == 1) windowColors.remove(lastColor);
        windowColorCount.put(lastColor, colorCount - 1);

        // Decrease the window size
        lo++;

      // Increase the window size because we don't have enough colors
      } else if(++hi < L) {

        int nextColor = suffixMap[sa.sa[hi]];

        // Update the colors in our window
        windowColors.add(nextColor);
        Integer colorCount = windowColorCount.get(nextColor);
        if (colorCount == null) colorCount = 0;
        windowColorCount.put(nextColor, colorCount + 1);
          
      }

    }

    return lcss;

  }

  private static String toString(SuffixArray sa, int shift) {
    final String SEP = "$";
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < sa.N; i++) {
      int index  = sa.sa[i];
      int length = sa.N - index;
      String suffix = index + " ";
      for (int j = 0; j < length; j++) {
        if (sa.T[index+j]-shift < 32) suffix += SEP;
        else suffix += ((char)(sa.T[index+j]-shift)) + "";
      }
      sb.append(suffix + "\n");
      // sb.append( index + " " + new String(T, index, length) + "\n");
    }
    String lcp_str = "LCP: " + Arrays.toString(sa.lcp) + "\n";
    return lcp_str + sb.toString();
  }

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < N; i++) {
      int index  = sa[i];
      int length = N - index;
      sb.append( index + " " + new String(T, index, length) + "\n");
    }
    String lcp_str = "LCP: " + Arrays.toString(lcp) + "\n";
    return lcp_str + sb.toString();
  }

  public static void main(String[] args) {
    
    // String[] strs = { "AAGAAGC", "AGAAGT", "CGAAGC" };
    // String[] strs = { "abca", "bcad", "daca" };
    // String[] strs = { "abca", "bcad", "daca" };
    String[] strs = { "AABC", "BCDC", "BCDE", "CDED" };
    TreeSet <String> lcss = SuffixArray.lcs(strs, 2);
    System.out.println(lcss);

    // SuffixArray sa = new SuffixArray("abracadabra");
    // System.out.println(sa);
    // System.out.println(java.util.Arrays.toString(sa.sa));
    // System.out.println(java.util.Arrays.toString(sa.lcp));

  }

}


// Used in LCS 
class CompactSegmentTree {

  private int N;

  // Let UNIQUE be a value which does NOT 
  // and will not appear in the segment tree
  private long UNIQUE = 8123572096793136074L;

  // Segment tree values
  private long[] tree;

  public CompactSegmentTree(int size) {
    tree = new long[2*(N = size)];
    java.util.Arrays.fill(tree, UNIQUE);
  }

  public CompactSegmentTree(int[] values) {
    this(values.length);
    for(int i = 0; i < N; i++) modify(i, values[i]);
  }

  public CompactSegmentTree(long[] values) {
    this(values.length);
    for(int i = 0; i < N; i++) modify(i, values[i]);
  }

  // This is the segment tree function we are using for queries.
  // The function must be an associative function, meaning
  // the following property must hold: f(f(a,b),c) = f(a,f(b,c)).
  // Common associative functions used with segment trees
  // include: min, max, sum, product, GCD, and etc...
  private long function(long a, long b) {

    if (a == UNIQUE) return b;
    else if (b == UNIQUE) return a;

    // return a + b; // sum over a range
    // return (a > b) ? a : b; // maximum value over a range
    return (a < b) ? a : b; // minimum value over a range
    // return a * b; // product over a range (watch out for overflow!)

  }

  // Adjust point i by a value, O(log(n))
  public void modify(int i, long value) {
    tree[i + N] = function( tree[i+N],  value);
    for (i += N; i > 1; i >>= 1) {
      tree[i>>1] = function(tree[i], tree[i^1]);
    }
  }
  
  // Query interval [l, r), O(log(n))
  public long query(int l, int r) {
    if (l >= r) return 0L;
    long res = UNIQUE;
    for (l += N, r += N; l < r; l >>= 1, r >>= 1) {
      if ((l&1) != 0) res = function(res, tree[l++]);
      if ((r&1) != 0) res = function(res, tree[--r]);
    }
    return res;
  }

}

