
import java.util.*;

public class SuffixArrayFast {

  // MSIZE is the default alphabet size, this may need
  // to be much larger if you're using the LCS method!
  int MAXLEN, MSIZE = 256, N;
  int [] T, sa, cnt, lcp, x, y, tmp;

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

    N = text.length;
    T = text.clone();
    
    MAXLEN = Math.max(MSIZE, N);
    cnt = new int[MAXLEN];
    sa  = new int[MAXLEN];
    x   = new int[MAXLEN];
    y   = new int[MAXLEN];
    construct();
    kasai();

  }

  private boolean cmp(int[] r, int a, int b, int l) {
    if (r[a] != r[b]) return false;
    if (a+l >= MAXLEN || b+l >= MAXLEN) return false;
    return r[a + l] == r[b + l];
  }

  // Construct suffix array, O(nlogn)
  private void construct() {
    int p, i, j, k;
    Arrays.fill(cnt, 0);
    for (i = 0; i < N; i++) cnt[x[i] = T[i]]++;
    for (i = 1; i < MSIZE; i++) cnt[i] += cnt[i - 1];
    for (i = N - 1; i >= 0; i--) sa[--cnt[x[i]]] = i;
    for (j = p = 1; p < N; j <<= 1, MSIZE = p) {
      for (p = 0, i = N - j; i < N; i++) y[p++] = i;
      for (i = 0; i < N; i++) if (sa[i] >= j) y[p++] = sa[i] - j;
      Arrays.fill(cnt, 0);
      for (i = 0; i < N; i++) cnt[x[y[i]]]++;
      for (i = 1; i < MSIZE; i++) cnt[i] += cnt[i - 1];
      for (i = N - 1; i >= 0; i--) sa[--cnt[x[y[i]]]] = y[i];
      tmp = x; x = y; y = tmp;
      x[sa[0]] = 0;
      for (i = p = 1; i < N; i++)
        x[sa[i]] = cmp(y, sa[i - 1], sa[i], j) ? p - 1 : p++;
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

    String[] strs = { "abcde", "habcab", "ghabcdf" };
    TreeSet <String> set = SuffixArrayFast.lcs(strs, 2);
    System.out.println(set);

  }

}










