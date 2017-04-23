/**
 * The StringSet is a probabilistic string set data structure implemented using a bloom filter
 * and positional rolling hashing techniques. This data structure is very fast (it can
 * outperform Java's HashSet DS by many orders of magnitude on large data sets).
 * Despite being probabilistic, this DS is very safe to use because the probability of 
 * a false positive can be set to as low as you wish it to be.
 *
 * @author William Alexandre Fiset, william.alexandre.fiset@gmail.com
 **/

public class StringSet {

  // Our alphabet size is 95 because there are only 95 printable ASCII characters 
  // which are in the range between [32, 127). We also need to add +1 to our alphabet
  // size because we're going to redefine the first ASCII character (the space character)
  // to be 1 instead of 0 to avoid collisions where the string ' ' hashes to the 
  // same value as '   ' since 0*95^0 = 0*95^0 + 0*95^1 + 0*95^2
  private static int ALPHABET_SZ = 95 + 1;  
  private static int[] ALPHABET = new int[127];

  private int NUM_HASHES;
  private long POWERS[][];
  private int[] MODS, MOD_INVERSES;
  private long [] rollingHashes;
  private BloomFilter bloomFilter;

  // More primes: 10^4+9, 10^4+13, 10^4+19, 10^5+7, 10^5+9, 10^5+37, 10^6+3, 10^6+19, 10^6+43, 
  // 10^7+3, 10^7+33, 10^7+37, 10^8+19, 10^8+79, 10^8+103, 10^9+7, 10^9+9, 10^9+23
  private static int[] DEFAULT_PRIMES = { 10_000_003, 10_000_033, 10_000_037 };

  // Assign a mapping from the printable ASCII characters to the natural numbers
  static {
    for (int i = 32, n = 1; i < ALPHABET.length; i++, n++) {
      ALPHABET[i] = n;
    }
  }

  public StringSet(int maxLen) {
    this(DEFAULT_PRIMES, maxLen);
  }

  // mods - The mod values to use for the bloom filter, they should probably be prime numbers
  // maxLen - The maximum length string we will need to deal with
  public StringSet(int[] mods, int maxLen) {
    
    MODS = mods.clone();
    NUM_HASHES = mods.length;
    MOD_INVERSES = new int[NUM_HASHES];
    POWERS = new long[NUM_HASHES][maxLen];
    rollingHashes = new long[NUM_HASHES];
    bloomFilter = new BloomFilter(mods);

    java.math.BigInteger bigAlpha = new java.math.BigInteger(String.valueOf(ALPHABET_SZ));

    // Assuming all mods are primes each mod value will have a modular inverse
    for (int i = 0; i < NUM_HASHES; i++) {
      java.math.BigInteger mod = new java.math.BigInteger(String.valueOf(MODS[i]));
      MOD_INVERSES[i] = bigAlpha.modInverse(mod).intValue();
    }

    // Precompute powers of the alphabet size mod all the mod values
    for(int i = 0; i < NUM_HASHES; i++) {
      POWERS[i][0] = 1L;
      for(int j = 1; j < maxLen; j++) {
        POWERS[i][j] = (POWERS[i][j-1]*ALPHABET_SZ) % mods[i];
      }
    }

  }

  // This method adds a string to the bloom filter set. If you're adding a lot
  // of similar strings of the same length use the overloaded version
  // of this method to take advantage of rolling hashes, or if you're
  // simply adding all substrings call 'addAllSubstrings' to also take
  // advantage of rolling hashing technique.
  public void add(String str) {

    java.util.Arrays.fill(rollingHashes, 0L);

    for (int i = 0; i < str.length(); i++) {
      for (int k = 0; k < NUM_HASHES; k++) {
        int rightChar = ALPHABET[str.charAt(i)-' '];
        rollingHashes[k] = addRight(rollingHashes[k], rightChar, k);
      }
    }

    bloomFilter.add(rollingHashes);

  }

  // Add all sequences of length 'sz' to the bloom filter
  public void add(String str, int sz) {

    add(str);

    for (int i = sz; i < str.length() - sz; i++) {
      for (int k = 0; k < NUM_HASHES; k++) {

        int rightChar = ALPHABET[str.charAt(i)-' '];
        int leftChar  = ALPHABET[str.charAt(i-sz)-' '];

        // Add the right character
        rollingHashes[k] = addRight(rollingHashes[k], rightChar, k);

        // Remove the leftmost character
        rollingHashes[k] = removeLeft(rollingHashes[k], leftChar, k, sz);
        
      }
      bloomFilter.add(rollingHashes);
    }

  }

  // Adds all the substrings of 'data' into the 
  // bloom filter using positional hashing
  public void addAllSubstrings(String str) {

    int N = str.length();
    int[] values = new int[N];

    for(int i = 0; i < N; i++)
      values[i] = ALPHABET[str.charAt(i)-' '];

    for (int i = 0; i < N; i++) {

      // Reset all rolling hashes
      java.util.Arrays.fill(rollingHashes, 0L);

      for (int j = i; j < N; j++) {

        // Compute the next rolling hash value for each hash 
        // function with a different modulus value
        for (int k = 0; k < NUM_HASHES; k++) {
          rollingHashes[k] = addRight(rollingHashes[k], values[j], k);
        }
        
        // Add this substring to the bloom filter
        bloomFilter.add(rollingHashes);

      }

    }
  }

  // This function adds a character to the end of the rolling hash
  public long addRight(long rollingHash, int lastValue, int modIndex) {
    rollingHash = rollingHash * ALPHABET_SZ + lastValue;
    return rollingHash % MODS[modIndex];
  }
  
  // This function adds a character to the beginning of the rolling hash
  public long addLeft(long rollingHash, int firstValue, int modIndex, int len) {
    rollingHash = rollingHash * POWERS[modIndex][len-1] + firstValue;
    return rollingHash % MODS[modIndex];
  }

  // Given a rolling hash x_n*A^n + x_n-1*A^(n-1) + ... + x_2*A^2 + x_1*A^1 + x_0*A^0
  // where x_i is a string character value and 'A' is the alphabet size we
  // want to remove the first term 'x_n*A^n' from our rolling hash.
  //
  // firstValue - This is x_n, the first character of this string
  public long removeLeft(long rollingHash, int firstValue, int modIndex, int len) {
    rollingHash = rollingHash - firstValue * POWERS[modIndex][len-1];
    return (rollingHash + MODS[modIndex]) % MODS[modIndex];
  }

  public long removeRight(long rollingHash, int lastValue, int modIndex) {
    rollingHash = ((rollingHash-lastValue)+MODS[modIndex]) % MODS[modIndex];
    return (rollingHash * MOD_INVERSES[modIndex]) % MODS[modIndex];
  }

  // Given the hash of a string this method returns whether or not
  // that string is found within the bloom filter.
  public boolean contains(long[] hashes) {
    return bloomFilter.contains(hashes);
  }

  public boolean contains(String str) {

    // Dynamically compute this string's hash value
    java.util.Arrays.fill(rollingHashes, 0L);
    for (int i = 0; i < str.length(); i++) {
      for (int k = 0; k < NUM_HASHES; k++) {
        int rightChar = ALPHABET[str.charAt(i)-' '];
        rollingHashes[k] = addRight(rollingHashes[k], rightChar, k);
      }
    }

    return bloomFilter.contains(rollingHashes);

  }

  @Override public String toString() {
    return bloomFilter.toString();
  }

}










