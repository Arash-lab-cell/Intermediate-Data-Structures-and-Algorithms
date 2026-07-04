public class Solution {
    public long[] rangeSum(int[] A, int[][] B) {
        int n = A.length;
        long [] ps = new long [n];
        ps[0] = A[0];
        for(int i = 1; i < A.length; i++){
            ps[i] = ps[i-1] + A[i];
        }
        long [] sum = new long [B.length];
        for(int i = 0; i < B.length; i++){
            int l = B[i][0];
            int r = B[i][1];
            if(l == 0){
                sum [i] = ps[r];
            }else{
                sum [i] = ps[r] - ps[l-1];
            }
        }
        return sum;
    }
}
