public class Solution {
    public int[][] solve(int[] A) {
        int n = A.length;
        int sub_array = (n*(n+1))/2;
        int[][] sa = new int[sub_array][];
        int index = 0;
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                sa[index] = new int[j-i+1];
                for(int k = i, m = 0; k <= j; k++, m++){
                    sa[index][m] = A[k];
                }
                index++;
            }
        }
        return sa;
    }
}
