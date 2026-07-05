public class Solution {
    public int[] solve(int[] A, int[][] B) {
        int n = A.length;
        int[] ps_even = new int[n];
        int count = 0;
        for(int i = 0; i < n; i++){
            if(A[i]%2 == 0){
                count++;
            }
            ps_even[i] = count;
        }
        int[] ans = new int[B.length];
        for(int i = 0; i < B.length; i++){
            int l = B[i][0];
            int r = B[i][1];
            if(l == 0){
                ans[i] = ps_even[r];
            }else{
                ans[i] = ps_even[r] - ps_even[l-1];
            }
        }
        return ans;
    }
}
