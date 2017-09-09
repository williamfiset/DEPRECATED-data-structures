/**
 * Medium speed suffix array implementation.
 * Time Complexity: O(nlog^2(n))
 *
 * @author William Fiset, william.alexandre.fiset@gmail.com
 **/

import java.util.*;

class SuffixArrayMed {

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

  public SuffixArrayMed(String text) {
    this(toIntArray(text));
  }

  private static int[] toIntArray(String s) {
    int[] text = new int[s.length()];
    for(int i=0;i<s.length();i++)text[i] = s.charAt(i);
    return text;
  }

  public SuffixArrayMed(int[] text) {
    if (text == null) throw new IllegalArgumentException();
    T = text.clone();
    N = text.length;
    construct();
    kasai();
  }

  // Construct a suffix array in O(nlog^2(n))
  public void construct() {

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

    // O(log(n))
    for(int pos = 1; pos < N; pos *= 2) {

      for(int i = 0; i < N; i++) {
        SuffixRankTuple suffixRank = ranks[i];
        suffixRank.firstHalf  = suffixRanks[0][i];
        suffixRank.secondHalf = i+pos < N ? suffixRanks[0][i+pos] : -1;
        suffixRank.originalIndex = i;
      }

      // O(nlog(n))
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
      String suffix = new String(T, sa[i], suffixLen);
      sb.append(String.format("% 7d % 7d % 7d %s\n", i, sa[i], lcp[i], suffix));
    }
    return sb.toString();
  }

  public static void main(String[] args) {

    // String[] strs = { "AAGAAGC", "AGAAGT", "CGAAGC" };
    // String[] strs = { "abca", "bcad", "daca" };
    // String[] strs = { "abca", "bcad", "daca" };
    // String[] strs = { "AABC", "BCDC", "BCDE", "CDED" };
    // String[] strs = { "abcdefg", "bcdefgh", "cdefghi" };
    // String[] strs = { "xxx", "yyy", "zzz" };
    // TreeSet <String> lcss = SuffixArrayMed.lcs(strs, 2);
    // System.out.println(lcss);

    // SuffixArrayMed sa = new SuffixArrayMed("abracadabra");
    // System.out.println(sa);
    // System.out.println(java.util.Arrays.toString(sa.sa));
    // System.out.println(java.util.Arrays.toString(sa.lcp));

    SuffixArrayMed sa = new SuffixArrayMed("ABBABAABAA");
    // SuffixArrayMed sa = new SuffixArrayMed("GAGAGAGAGAGAG");
    System.out.println(sa);


  }

}
