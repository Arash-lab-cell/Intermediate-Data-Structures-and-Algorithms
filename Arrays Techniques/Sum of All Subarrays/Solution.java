public class Solution {
    public long subarraySum(int[] A) {
        long n = A.length;
        long ans = 0;
        for(int i = 0; i < n; i++){
            long freq = (i+1)*(n-i);
            ans = ans + A[i]*freq;
        }
        return ans;
    }
}
