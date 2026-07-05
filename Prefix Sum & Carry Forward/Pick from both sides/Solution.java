public class Solution {
    public int solve(int[] A, int B) {
        int n = A.length;
        int[] ps = new int[n];
        ps[0] = A[0];
        for(int i = 1; i < n; i++){
            ps[i] = ps[i-1] + A[i];
        }
        int[] ss = new int[n];
        ss[0] = A[n-1];
        for(int i = (n-2), m = 1; i >= 0; i--, m++){
            ss[m] = ss[m-1] + A[i];
        }
        int ans = 0;
        if(ps[B-1] > ss[B-1]){
            ans = ps[B-1];
        }else{
            ans = ss[B-1];
        }
        for(int i = 1; i <= B-1; i++){
            int sum = ps[i-1] + ss[B-i-1];
            if(sum > ans){
                ans = sum;
            }
        }
        return ans;
    }
}
