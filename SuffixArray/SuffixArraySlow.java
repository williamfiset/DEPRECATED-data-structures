/**
 * Naive suffix array implementation.
 *
 * Time Complexity: O(n^2log(n))
 *
 * @author William Fiset, william.alexandre.fiset@gmail.com
 */

public class SuffixArraySlow {

  static class Suffix implements Comparable <Suffix> {

    // Starting position of suffix in text
    final int index, len;
    final int[] text;

    public Suffix(int[] text, int index) {
      this.len = text.length - index;
      this.index = index;
      this.text = text;
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
      return new String(text, index, len);
    }

  }

  static final int DEFAULT_ALPHABET_SIZE = 256;
  static final int DEFAULT_ALPHABET_SHIFT = 0;

  // Test length
  final int N;

  // Contains all the suffixes of the SuffixArray
  Suffix[] suffixes;

  // T   - Original text.
  // sa  - Stores sorted suffixes.
  // lcp - Contains Longest Common Prefix (LCP) count between adjacent suffixes.
  int[] T, lcp, sa;

  int shift = DEFAULT_ALPHABET_SHIFT;
  int alphabetSize = DEFAULT_ALPHABET_SIZE;

  public SuffixArraySlow(String text) {
    this(toIntArray(text), DEFAULT_ALPHABET_SHIFT, DEFAULT_ALPHABET_SIZE);
  }

  public SuffixArraySlow(int[] text) {
    this(text, DEFAULT_ALPHABET_SHIFT, DEFAULT_ALPHABET_SIZE);
  }

  // TODO(williamfiset): Get rid of these constructors in favor of
  // automatically detecting the alphabet size shift required
  public SuffixArraySlow(String text, int shift) {
    this(toIntArray(text), shift, DEFAULT_ALPHABET_SHIFT);
  }
  public SuffixArraySlow(int[] text, int shift) {
    this(text, shift, DEFAULT_ALPHABET_SIZE);
  }

  // Designated constructor
  public SuffixArraySlow(int[] text, int shift, int alphabetSize) {

    if (text == null || alphabetSize <= 0) throw new IllegalArgumentException();

    this.alphabetSize = alphabetSize;
    T = text;
    N = text.length;

    construct();
    kasai();

  }

  private static int[] toIntArray(String s) {
    if (s == null) return null;
    int[] text = new int[s.length()];
    for(int i=0;i<s.length();i++)text[i] = s.charAt(i);
    return text;
  }

  // Suffix array construction. This acutally takes O(n^2log(n))
  // time since sorting takes on average O(nlog(n)) and each String
  // comparision takes O(n)
  private void construct() {

    sa = new int[N];
    suffixes = new Suffix[N];

    for (int i = 0; i < N; i++)
      suffixes[i] = new Suffix(T, i);

    java.util.Arrays.sort(suffixes);

    for (int i = 0; i < N; i++) {
      Suffix suffix = suffixes[i];
      sa[i] = suffix.index;
      suffixes[i] = null;
    }

    suffixes = null;

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
      char[] suffixArray = new char[suffixLen];
      for (int j = sa[i], k = 0; j < N; j++, k++)
        suffixArray[k] = (char)(T[j] - shift);
      String suffix = new String(suffixArray);
      sb.append(String.format("% 7d % 7d % 7d %s\n", i, sa[i], lcp[i], suffix));
    }

    return sb.toString();

  }

  public static void main(String[] args) {
    SuffixArraySlow sa = new SuffixArraySlow("HEHEHEHHHE");
    System.out.println(sa);
  }

}
