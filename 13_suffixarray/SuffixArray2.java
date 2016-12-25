/*

#include bits/stdc++.h  
using namespace std;

// suffixRank is table hold the rank of each string on each iteration  
// suffixRank[i][j] denotes rank of jth suffix at ith iteration  

int suffixRank[20][int(1E6)];

// Example "abaab"  
// Suffix Array for this (2, 3, 0, 4, 1)  
// Create a tuple to store rank for each suffix  

struct myTuple {  
    int originalIndex;   // stores original index of suffix  
    int firstHalf;       // store rank for first half of suffix  
    int secondHalf;      // store rank for second half of suffix  
};


// function to compare two suffix in O(1)  
// first it checks whether first half chars of 'a' are equal to first half chars of b  
// if they compare second half  
// else compare decide on rank of first half  

int cmp(myTuple a, myTuple b) {  
    if(a.firstHalf == b.firstHalf) return a.secondHalf < b.secondHalf;  
    else return a.firstHalf < b.firstHalf;  
}

int main() {

    // Take input string
    // initialize size of string as N

    string s; cin >> s;
    int N = s.size();

    // Initialize suffix ranking on the basis of only single character
    // for single character ranks will be 'a' = 0, 'b' = 1, 'c' = 2 ... 'z' = 25

    for(int i = 0; i < N; ++i)
        suffixRank[0][i] = s[i] - 'a';

    // Create a tuple array for each suffix

    myTuple L[N];

    // Iterate log(n) times i.e. till when all the suffixes are sorted
    // 'stp' keeps the track of number of iteration
    // 'cnt' store length of suffix which is going to be compared

    // On each iteration we initialize tuple for each suffix array
    // with values computed from previous iteration

    for(int cnt = 1, stp = 1; cnt < N; cnt *= 2, ++stp) {

        for(int i = 0; i < N; ++i) {
            L[i].firstHalf = suffixRank[stp - 1][i];
            L[i].secondHalf = i + cnt < N ? suffixRank[stp - 1][i + cnt] : -1;
            L[i].originalIndex = i;
        }

        // On the basis of tuples obtained sort the tuple array

        sort(L, L + N, cmp);

        // Initialize rank for rank 0 suffix after sorting to its original index
        // in suffixRank array

        suffixRank[stp][L[0].originalIndex] = 0;

        for(int i = 1, currRank = 0; i < N; ++i) {

            // compare ith ranked suffix ( after sorting ) to (i - 1)th ranked suffix
            // if they are equal till now assign same rank to ith as that of (i - 1)th
            // else rank for ith will be currRank ( i.e. rank of (i - 1)th ) plus 1, i.e ( currRank + 1 )

            if(L[i - 1].firstHalf != L[i].firstHalf || L[i - 1].secondHalf != L[i].secondHalf)
                ++currRank;

            suffixRank[stp][L[i].originalIndex] = currRank;
        }

    }

    // Print suffix array

    for(int i = 0; i < N; ++i) cout << L[i].originalIndex << endl;

    return 0;
} 

*/


public class SuffixArray2 {

  static class SuffixRank implements Comparable <SuffixRank> {

    int firstHalf, secondHalf, originalIndex;

    // Sort Suffix ranks first on the first Half then the second half
    @Override public int compareTo(SuffixRank other) {
      // if (firstHalf == other.firstHalf) return (secondHalf < other.secondHalf) ? -1 : +1;
      // return (firstHalf < other.firstHalf) ? -1 : +1;
      // int cmp = Integer.compare(firstHalf, other.firstHalf);
      // if (cmp == 0) return Integer.compare(secondHalf, other.secondHalf);
      // return cmp;
      if (firstHalf == other.firstHalf)
        return secondHalf - other.secondHalf;
      return firstHalf - other.firstHalf;
    }
  }

  // Size of the suffix array
  int N; 

  // T is the text
  char[] T;

  // Suffix array
  int[] sa;

  // Longest common prefix array
  int [] lcp;

  public SuffixArray2(String text) {
    this(text == null ? null : text.toCharArray());
  }

  public SuffixArray2(char[] text) {
    if (text == null) throw new IllegalArgumentException();
    T = text; N = text.length;
    constructSuffixArray();
    // kasai();
  }

  // Complements of
  // https://www.hackerrank.com/challenges/string-similarity/topics/suffix-array
  public void constructSuffixArray() {

    sa = new int[N];

    // Maintain suffix ranks in both a matrix with two rows containing the
    // current and last rank information as well as some sortable rank objects
    int [][] suffixRanks = new int[2][N];
    SuffixRank[] ranks = new SuffixRank[N];

    // Assign a numerical value to each character
    for (int i = 0; i < N; i++) {
      suffixRanks[0][i] = (T[i] - 'a');
      ranks[i] = new SuffixRank();
    }

    // O(logn)
    for(int pos = 1; pos < N; pos *= 2) {

      for(int i = 0; i < N; i++) {
        SuffixRank suffixRank = ranks[i];
        suffixRank.firstHalf  = suffixRanks[0][i];
        suffixRank.secondHalf = i+pos < N ? suffixRanks[0][i+pos] : -1;
        suffixRank.originalIndex = i;
      }
      
      java.util.Arrays.sort(ranks);

      suffixRanks[1][ranks[0].originalIndex] = 0;
      for (int i = 1, newRank = 0; i < N; i++ ) {
        
        SuffixRank lastSuffixRank = ranks[i-1];
        SuffixRank currSuffixRank = ranks[i];

        if (currSuffixRank.firstHalf  != lastSuffixRank.firstHalf  ||
            currSuffixRank.secondHalf != lastSuffixRank.secondHalf)
          newRank++;

        suffixRanks[1][currSuffixRank.originalIndex] = newRank;

      }

      // Place top row (current row) to be the last row
      suffixRanks[0] = suffixRanks[1];

    }

    for (int i = 0; i < N; i++)
      sa[i] = ranks[i].originalIndex;
    System.out.println(java.util.Arrays.toString(sa));

  }

  public static void main(String[] args) {
    
    new SuffixArray2("abaabbcaabaabae");

  }

}
