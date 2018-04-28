/**
 * Naive suffix array implementation.
 *
 * Time Complexity: O(n^2log(n))
 *
 * @author William Fiset, william.alexandre.fiset@gmail.com
 */
package com.williamfiset.datastructures.suffixarray;

public class SuffixArraySlow extends SuffixArray {

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

  // Contains all the suffixes of the SuffixArray
  Suffix[] suffixes;

  public SuffixArraySlow(String text) {
    super(toIntArray(text), DEFAULT_ALPHABET_SHIFT, DEFAULT_ALPHABET_SIZE);
  }

  public SuffixArraySlow(int[] text) {
    super(text, DEFAULT_ALPHABET_SHIFT, DEFAULT_ALPHABET_SIZE);
  }

  // TODO(williamfiset): Get rid of these constructors in favor of
  // automatically detecting the alphabet size shift required
  public SuffixArraySlow(String text, int shift) {
    super(toIntArray(text), shift, DEFAULT_ALPHABET_SHIFT);
  }
  public SuffixArraySlow(int[] text, int shift) {
    super(text, shift, DEFAULT_ALPHABET_SIZE);
  }

  // Designated constructor
  public SuffixArraySlow(int[] text, int shift, int alphabetSize) {
    super(text, shift, alphabetSize);
  }

  // Suffix array construction. This acutally takes O(n^2log(n))
  // time since sorting takes on average O(nlog(n)) and each String
  // comparision takes O(n)
  @Override protected void construct() {

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

  public static void main(String[] args) {
    SuffixArraySlow sa = new SuffixArraySlow("ABBABAABAA");
    System.out.println(sa);
  }

}
