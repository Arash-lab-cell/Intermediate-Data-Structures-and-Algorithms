public class Solution {
    public long solve(int[] A, int B) {
        long n = A.length;
        long sum = 0;
        for(int i = 0; i < B; i++){
            sum = sum + A[i];
        }
        long ans = sum;
        for(int i = 1, j = B; j < n; i++,j++){
            sum = sum - A[i-1] + A[j];
            ans = Math.max(sum, ans);
        }
        return ans;
    }
}
