
import java.util.*;

public class SuffixArrayFast {

  // ALPHABET_SZ is the default alphabet size, this may need to be much
  // larger if you're using the LCS method with multiple sentinels
  int ALPHABET_SZ = 256, N;
  int[] T, lcp, sa, sa2, rank, tmp, c;

  public SuffixArrayFast(String str) {
    this(toIntArray(str));
  }

  private static int[] toIntArray(String s) {
    int[] text = new int[s.length()];
    for(int i=0;i<s.length();i++)text[i] = s.charAt(i);
    return text;
  }

  // Designated constructor
  public SuffixArrayFast(int[] text) {
    T = text;
    N = text.length;
    sa = new int[N];
    sa2 = new int[N];
    rank = new int[N];
    c = new int[Math.max(ALPHABET_SZ, N)];
    construct();
    kasai();
  }

  private void construct() {
    int i, p, r;
    for (i=0; i<N; ++i) c[rank[i] = T[i]]++;
    for (i=1; i<ALPHABET_SZ; ++i) c[i] += c[i-1];
    for (i=N-1; i>=0; --i) sa[--c[T[i]]] = i;
    for (p=1; p<N; p <<= 1) {
      for (r=0, i=N-p; i<N; ++i) sa2[r++] = i;
      for (i=0; i<N; ++i) if (sa[i] >= p) sa2[r++] = sa[i] - p;
      Arrays.fill(c, 0, ALPHABET_SZ, 0);
      for (i=0; i<N; ++i) c[rank[i]]++;
      for (i=1; i<ALPHABET_SZ; ++i) c[i] += c[i-1];
      for (i=N-1; i>=0; --i) sa[--c[rank[sa2[i]]]] = sa2[i];
      for (sa2[sa[0]] = r = 0, i=1; i<N; ++i) {
          if (!(rank[sa[i-1]] == rank[sa[i]] &&
              sa[i-1]+p < N && sa[i]+p < N &&
              rank[sa[i-1]+p] == rank[sa[i]+p])) r++;
          sa2[sa[i]] = r;
      } tmp = rank; rank = sa2; sa2 = tmp;
      if (r == N-1) break; ALPHABET_SZ = r + 1;
    }
  }

  // Use Kasai algorithm to build LCP array
  private void kasai() {
    lcp = new int[N];
    int [] inv = new int[N];
    for (int i = 0; i < N; i++) inv[sa[i]] = i;
    for (int i = 0, len = 0; i < N; i++) {
      if (inv[i] > 0) {
        int k = sa[inv[i]-1];
        while( (i + len < N) && (k + len < N) && T[i+len] == T[k+len] ) len++;
        lcp[inv[i]-1] = len;
        if (len > 0) len--;
      }
    }
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
        return true;
      } else if (cmp < 0) {
        lo = mid + 1;
      } else { hi = mid - 1; }
    }
    return false;
  }

  /**
   * Finds the Longest Common Substring (LCS) between a group of strings.
   * The current implementation takes O(nlog(n)) bounded by the suffix array construction.
   * @param strs - The strings you wish to find the longest common substring between
   * @param K - The minimum number of strings to find the LCS between. K must be at least 2.
   **/
  public static TreeSet <String> lcs(String [] strs, final int K) {

    if (K <= 1) throw new IllegalArgumentException("K must be greater than or equal to 2!");

    TreeSet <String> lcss = new TreeSet<>();
    if (strs == null || strs.length <= 1) return lcss;

    // L is the concatenated length of all the strings and the sentinels
    int L = 0;

    final int NUM_SENTINELS = strs.length, N = strs.length;
    for(int i = 0; i < N; i++) L += strs[i].length() + 1;

    int[] indexMap = new int[L];
    int LOWEST_ASCII = Integer.MAX_VALUE;

    // Find the lowest ASCII value within the strings.
    // Also construct the index map to know which original
    // string a given suffix belongs to.
    for (int i = 0, k = 0; i < strs.length; i++) {

      String str = strs[i];

      for (int j = 0; j < str.length(); j++) {
        int asciiVal = str.charAt(j);
        if (asciiVal < LOWEST_ASCII) LOWEST_ASCII = asciiVal;
        indexMap[k++] = i;
      }

      // Record that the sentinel belongs to string i
      indexMap[k++] = i;

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

    SuffixArrayFast sa = new SuffixArrayFast(T);
    Deque <Integer> deque = new ArrayDeque<>();

    // Assign each string a color and maintain the color count within the window
    Map <Integer, Integer> windowColorCount = new HashMap<>();
    Set <Integer> windowColors = new HashSet<>();

    // Start the sliding window at the number of sentinels because those
    // all get sorted first and we want to ignore them
    int lo = NUM_SENTINELS, hi = NUM_SENTINELS, bestLCSLength = 0;

    // Add the first color
    int firstColor = indexMap[sa.sa[hi]];
    windowColors.add(firstColor);
    windowColorCount.put(firstColor, 1);

    // Maintain a sliding window between lo and hi
    while(hi < L) {

      int uniqueColors = windowColors.size();

      // Attempt to update the LCS
      if (uniqueColors >= K) {

        int windowLCP = sa.lcp[deque.peekFirst()];

        if (windowLCP > 0 && bestLCSLength < windowLCP) {
          bestLCSLength = windowLCP;
          lcss.clear();
        }

        if (windowLCP > 0 && bestLCSLength == windowLCP) {

          // Construct the current LCS within the window interval
          int pos = sa.sa[lo];
          char[] lcs = new char[windowLCP];
          for (int i = 0; i < windowLCP; i++) lcs[i] = (char)(T[pos+i] - SHIFT);

          lcss.add(new String(lcs));

          // If you wish to find the original strings to which this longest
          // common substring belongs to the indexes of those strings can be
          // found in the windowColors set, so just use those indexes on the 'strs' array

        }

        // Update the colors in our window
        int lastColor = indexMap[sa.sa[lo]];
        Integer colorCount = windowColorCount.get(lastColor);
        if (colorCount == 1) windowColors.remove(lastColor);
        windowColorCount.put(lastColor, colorCount - 1);

        // Remove the head if it's outside the new range: [lo+1, hi)
        while (!deque.isEmpty() && deque.peekFirst() <= lo)
          deque.removeFirst();

        // Decrease the window size
        lo++;

      // Increase the window size because we don't have enough colors
      } else if(++hi < L) {

        int nextColor = indexMap[sa.sa[hi]];

        // Update the colors in our window
        windowColors.add(nextColor);
        Integer colorCount = windowColorCount.get(nextColor);
        if (colorCount == null) colorCount = 0;
        windowColorCount.put(nextColor, colorCount + 1);

        // Remove all the worse values in the back of the deque
        while(!deque.isEmpty() && sa.lcp[deque.peekLast()] > sa.lcp[hi-1])
          deque.removeLast();
        deque.addLast(hi-1);

      }

    }

    return lcss;

  }

  public void display() {
    System.out.printf("-----i-----SA-----LCP---Suffix\n");
    for(int i = 0; i < N; i++) {
      int suffixLen = N - sa[i];
      String suffix = new String(T, sa[i], suffixLen);
      System.out.printf("% 7d % 7d % 7d %s\n", i, sa[i],lcp[i], suffix );
    }
  }

  // Example usage
  public static void main(String[] args) {

    SuffixArrayFast sa = new SuffixArrayFast("ababcabaa");
    sa.display();

    // Output:
    // --i-------SA-----LCP--Suffix
    //   0       8       1   a
    //   1       7       1   aa
    //   2       5       3   abaa
    //   3       0       2   ababcabaa
    //   4       2       0   abcabaa
    //   5       6       2   baa
    //   6       1       1   babcabaa
    //   7       3       0   bcabaa
    //   8       4       0   cabaa

    String[] strs = { "abcde", "habcab", "ghabcdf" };
    TreeSet <String> set = SuffixArrayFast.lcs(strs, 2);
    System.out.println(set);

    // Output:
    // [abcd, habc]

  }

}
