import java.util.Arrays;

public class Solution {
    public int solve(String A, String B) {
        int n = A.length();
        int m = B.length();
        if(n != m){
            return 0;
        }
        char[] chA = A.toCharArray();
        Arrays.sort(chA);
        char[] chB = B.toCharArray();
        Arrays.sort(chB);
        for(int i = 0; i < n; i++){
            if(chA[i] != chB[i]){
                return 0;
            }
        }
        return 1;
    }
}
