public class Solution {
    public int[][] transpose(int[][] A){
        int n = A.length;
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                int temp = A[i][j];
                A[i][j] = A[j][i];
                A[j][i] = temp;
            }
        }
        return A;
    }
    public void solve(int[][] A) {
        int n = A.length;
        transpose(A);
        for(int i = 0; i < n; i++){
            int l = 0;
            int r = n-1;
            while(l < r){
                int temp = A[i][l];
                A[i][l] = A[i][r];
                A[i][r] = temp;
                l++;
                r--;
            }
        }
    }
}
