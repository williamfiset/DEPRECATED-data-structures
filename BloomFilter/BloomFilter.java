/**
 * Bloom Filter
 * 
 * @author William Alexandre Fiset, william.alexandre.fiset@gmail.com
 **/

import java.util.*;

public class BloomFilter {
  
  // The number of bitsets. This should be proportional
  // to the number of hash functions for this bloom filter
  private final int N_SETS;

  // Doing '& 0x7F' is the same as modding by 64 but faster
  private final static long MOD64 = 0x7F; // equivalently: 0b1111111;

  // Bitsets
  private final long[][] bitsets;

  // Tracks the size of the bitsets in this bloom filter
  private final int[] SET_SIZES;
  
  public BloomFilter(int[] bitSetSizes) {
    SET_SIZES = bitSetSizes.clone();  
    N_SETS = bitSetSizes.length;
    bitsets = new long[N_SETS][];
    for (int i = 0; i < N_SETS; i++) {
      bitsets[i] = new long[SET_SIZES[i]];
    }
  }
  
  public void add(int setIndex, long hash) {
    hash = hash % SET_SIZES[setIndex];
    int block = (int)(hash / 64);
    bitsets[setIndex][block] |= (1L << (hash & MOD64));
  }

  public void add(long[] hashes) {
    for(int i = 0; i < N_SETS; i++) {
      add(i, hashes[i]);
    }
  }

  public boolean contains(long [] hashes) {
    for(int i = 0; i < hashes.length; i++) {
      hashes[i] = hashes[i] % SET_SIZES[i];
      int block = (int)(hashes[i] / 64);
      long MASK = 1L << (hashes[i] & MOD64);
      if ( (bitsets[i][block] & MASK) != MASK )
        return false;
    }
    return true;
  }

  @Override public String toString() {
    
    int maxSz = 0;
    for(int sz : SET_SIZES) maxSz = Math.max(maxSz, sz);

    char[][] M = new char[N_SETS][maxSz];
    for (char [] ar : M ) Arrays.fill(ar, '0');

    for (int k = 0; k < N_SETS; k++) {
      for (int i = 0; i < SET_SIZES[k]; i++) {
        int block = i / 64;
        int offset = i % 64;
        long mask = 1L << offset;
        if ( (bitsets[k][block] & mask) == mask ) {
          M[k][i] = '1';
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    for (char[] row : M) sb.append(new String(row) + "\n");
    return sb.toString();

  }

  public static void main(String[] args) {

    //               a b c d e f g
    // int[] data = {1,2,3,4,5,6,7};
    // int[] PRIMES = {7, 11, 13};
    int[] data = {1, 3};
    int[] PRIMES = {5, 7};
    int ALPHABET_SZ = 27;
    BloomFilter bf = new BloomFilter(PRIMES);

    PosRollingHashSet hashSetMachine = new PosRollingHashSet(PRIMES, data.length, ALPHABET_SZ);
    hashSetMachine.addAllSubstrings(data);
    System.out.println(hashSetMachine);

  }

  // An implementation of a Set which uses positional rolling hashing 
  // techniques functions combined with the bloom filter rolling hash
  static class PosRollingHashSet {

    private int NUM_HASHES;
    private int ALPHABET_SZ;

    private int[] MODS, MOD_INVERSES;
    private long POWERS[][];
    private BloomFilter bloomFilter;

    // mods - The mod values to use for the bloom filter, they should probably be prime numbers
    // maxLen - The maximum length string we will need to deal with
    // alphabetSize - The size of the alphabet. don't forget to add +1 to
    //                avoid collisions for the first character like "a" mapping to 0
    //                but also "aaa" mappings to zero since 0*26^0 = 0*26^0 + 0*26^1 + 0*26^2
    public PosRollingHashSet(int[] mods, int maxLen, int alphabetSize) {
      
      MODS = mods.clone();
      NUM_HASHES = mods.length;
      MOD_INVERSES = new int[NUM_HASHES];
      ALPHABET_SZ = alphabetSize;
      POWERS = new long[NUM_HASHES][maxLen];
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
          POWERS[i][j] = (POWERS[i][j-1]*alphabetSize) % mods[i];
        }
      }

    }

    // Add all sequences of length 'sz' to the bloom filter
    public void add(int[] data, int sz) {

      

    }

    // Adds all the substrings of 'data' into the 
    // bloom filter using positional hashing
    public void addAllSubstrings(int[] data) {

      long[] rollingHashes = new long[NUM_HASHES];

      for (int i = 0; i < data.length; i++) {

        // Reset all rolling hashes
        java.util.Arrays.fill(rollingHashes, 0L);

        for (int j = i; j < data.length; j++) {

          // Compute the next rolling hash value for each hash 
          // function with a different modulus value
          for (int k = 0; k < MODS.length; k++) {
            rollingHashes[k] = addLast(rollingHashes[k], data[j], k);
          }
          
          // Add this substring to the bloom filter
          bloomFilter.add(rollingHashes);

        }

      }
    }

    // This function adds a character to the end of the rolling hash
    public long addLast(long rollingHash, int lastValue, int modIndex) {
      rollingHash = rollingHash * ALPHABET_SZ + lastValue;
      return rollingHash % MODS[modIndex];
    }
    
    // This function adds a character to the beginning of the rolling hash
    public long addFirst(long rollingHash, int firstValue, int modIndex, int len) {
      rollingHash = rollingHash * POWERS[modIndex][len-1] + firstValue;
      return rollingHash % MODS[modIndex];
    }

    // Given a rolling hash x_n*A^n + x_n-1*A^(n-1) + ... + x_2*A^2 + x_1*A^1 + x_0*A^0
    // where x_i is a string character value and 'A' is the alphabet size we
    // want to remove the first term 'x_n*A^n' from our rolling hash.
    //
    // firstValue - This is x_n, the first character of this string
    public long removeFirst(long rollingHash, int firstValue, int modIndex, int len) {
      rollingHash = rollingHash - firstValue*POWERS[modIndex][len-1];
      return (rollingHash + MODS[modIndex]) % MODS[modIndex];
    }

    public long removeLast(long rollingHash, int lastValue, int modIndex) {
      rollingHash = ((rollingHash-lastValue)+MODS[modIndex]) % MODS[modIndex];
      return (rollingHash * MOD_INVERSES[modIndex]) % MODS[modIndex];
    }

    @Override public String toString() {
      return bloomFilter.toString();
    }

  }

}









