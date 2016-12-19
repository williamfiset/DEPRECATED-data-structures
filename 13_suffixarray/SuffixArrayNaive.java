
import java.util.Arrays;

class Suffix implements Comparable <Suffix> {

  // Starting position of suffix in text
  int index, len;
  char [] text;

  public Suffix(char [] text, int index) {
    // if (text.length() >= index) throw new IllegalArgumentException();
    len = text.length - index;
    this.text = text;
    this.index = index;
  }

  // Compare the two suffixes
  @Override public int compareTo(Suffix other) {
    int min_len = Math.min(len, other.len);
    for (int i = 0; i < len; i++) {
      if (text[i] > other.text[i]) return +1;
      if (text[i] < other.text[i]) return -1;
    }
    return len - other.len;
  }

  @Override public String toString() {
    return new String(text, index, len - index);
  }

}

// Also create LCP (longest common prefix)

public class SuffixArrayNaive {

  int len;
  Suffix[] suffixes;

  public SuffixArray(String text) {
    this(text == null ? null : text.toCharArray());
  }

  // O(n^2log(n)) construction. O(nlog(n)) for sorting, but
  // each suffix takes O(n) comparison time
  public SuffixArray(char[] text) {
    if (text == null) throw new IllegalArgumentException();
    len = text.length;
    suffixes = new Suffix[len];
    for (int i = 0; i < len; i++)
      suffixes[i] = new Suffix(text, i);
    Arrays.sort(suffixes);
  }

}
