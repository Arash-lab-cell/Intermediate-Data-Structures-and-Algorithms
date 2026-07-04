public class Solution {
    public int[] sumOfEvenIndexedElements(int[] A, int[][] B) {
        int [] ps_even = new int[A.length];
        int [] ans = new int[B.length];
        ps_even[0] = A[0];
        for(int i = 1; i < A.length; i++){
            if(i % 2 == 0){
                ps_even[i] = ps_even[i-1] + A[i];
            }else{
                ps_even[i] = ps_even[i-1];
            }
        }
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
