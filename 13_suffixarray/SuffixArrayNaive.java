
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
    System.out.println( Arrays.toString(text) + " " + index + " " + len );
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
  int LCP [];
  char[] text;
  Suffix[] suffixes;

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
  }

  // Constructs the LCP (longest common prefix) array in linear time
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
        while( (i + lcp_len < len) &&
               (k + lcp_len < len) && 
               text[i+lcp_len] == text[k+lcp_len] )
          lcp_len++;

        LCP[inv[i]-1] = lcp_len;
        if (lcp_len > 0) lcp_len--;

      }
    }

  }

  // LRS longestRepeatedSubstring

  public static void main(String[] args) {
    
    SuffixArrayNaive sa = new SuffixArrayNaive("banana");
    System.out.println( Arrays.toString(sa.suffixes) );
    System.out.println( Arrays.toString(sa.LCP) );

  }


}
