/**
 * Suffix array construction implementation.
 *
 * Time Complexity: O(nlog(n))
 *
 * @author William Fiset, william.alexandre.fiset@gmail.com
 */

class SuffixArrayFast {

  static final int DEFAULT_ALPHABET_SIZE = 256;
  static final int DEFAULT_ALPHABET_SHIFT = 0;

  int alphabetSize = DEFAULT_ALPHABET_SIZE, N, shift;
  int[] T, lcp, sa, sa2, rank, tmp, c;

  public SuffixArrayFast(String text) {
    this(toIntArray(text), DEFAULT_ALPHABET_SHIFT, DEFAULT_ALPHABET_SIZE);
  }

  public SuffixArrayFast(int[] text) {
    this(text, DEFAULT_ALPHABET_SHIFT, DEFAULT_ALPHABET_SIZE);
  }

  // TODO(williamfiset): Get rid of these constructors in favor of
  // automatically detecting the alphabet size shift required
  public SuffixArrayFast(String text, int shift) {
    this(toIntArray(text), shift, DEFAULT_ALPHABET_SHIFT);
  }
  public SuffixArrayFast(int[] text, int shift) {
    this(text, shift, DEFAULT_ALPHABET_SIZE);
  }

  // Designated constructor
  public SuffixArrayFast(int[] text, int shift, int alphabetSize) {
    if (text == null || alphabetSize <= 0) throw new IllegalArgumentException();
    this.alphabetSize = alphabetSize;
    T = text;
    N = text.length;
    sa = new int[N];
    sa2 = new int[N];
    rank = new int[N];
    c = new int[Math.max(alphabetSize, N)];
    construct();
    kasai();
  }

  private static int[] toIntArray(String s) {
    if (s == null) return null;
    int[] text = new int[s.length()];
    for(int i=0;i<s.length();i++)text[i] = s.charAt(i);
    return text;
  }

  private void construct() {
    int i, p, r;
    for (i=0; i<N; ++i) c[rank[i] = T[i]]++;
    for (i=1; i<alphabetSize; ++i) c[i] += c[i-1];
    for (i=N-1; i>=0; --i) sa[--c[T[i]]] = i;
    for (p=1; p<N; p <<= 1) {
      for (r=0, i=N-p; i<N; ++i) sa2[r++] = i;
      for (i=0; i<N; ++i) if (sa[i] >= p) sa2[r++] = sa[i] - p;
      java.util.Arrays.fill(c, 0, alphabetSize, 0);
      for (i=0; i<N; ++i) c[rank[i]]++;
      for (i=1; i<alphabetSize; ++i) c[i] += c[i-1];
      for (i=N-1; i>=0; --i) sa[--c[rank[sa2[i]]]] = sa2[i];
      for (sa2[sa[0]] = r = 0, i=1; i<N; ++i) {
          if (!(rank[sa[i-1]] == rank[sa[i]] &&
              sa[i-1]+p < N && sa[i]+p < N &&
              rank[sa[i-1]+p] == rank[sa[i]+p])) r++;
          sa2[sa[i]] = r;
      } tmp = rank; rank = sa2; sa2 = tmp;
      if (r == N-1) break; alphabetSize = r + 1;
    }
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
    System.out.printf("-----i-----SA-----LCP---Suffix\n");

    for(int i = 0; i < N; i++) {
      int suffixLen = N - sa[i];
      char[] suffixArray = new char[suffixLen];
      for (int j = sa[i], k = 0; j < N; j++, k++)
        suffixArray[k] = (char)(T[j] - shift);
      String suffix = new String(suffixArray);
      System.out.printf("% 7d % 7d % 7d %s\n", i, sa[i], lcp[i], suffix);
    }

    return sb.toString();

  }

  public static void main(String[] args) {
    SuffixArrayFast sa = new SuffixArrayFast("ABBABAABAA");
    System.out.println(sa);
  }

}
