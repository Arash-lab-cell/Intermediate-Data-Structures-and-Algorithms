public class Solution {
    public int solve(char[] A) {
        int n = A.length;
        for(int i = 0; i < n; i++){
            if(!Character.isLetterOrDigit(A[i])){
                return 0;
            }
        }
        return 1;
    }
}
