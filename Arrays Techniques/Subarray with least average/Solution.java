public class Solution {
    public int solve(int[] A, int B) {
        int n = A.length;
        int index = 0;
        int ans = 0;
        for(int i = 0; i < B; i++){
            ans = ans + A[i];
        }
        int sum = ans;
        for(int s = 1, e = B; e < n; s++,e++){
            sum = sum - A[s-1] + A[e];
            if(sum < ans){
                ans = sum;
                index = s;
            }
        }
        return index;
    }
}
