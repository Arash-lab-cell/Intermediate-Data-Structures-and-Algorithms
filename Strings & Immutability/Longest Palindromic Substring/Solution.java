public class Solution {
    public int expand(String A,int l,int r){
         while(l >= 0 && r < A.length() && A.charAt(l) == A.charAt(r)){
                l--;
                r++;
            }
        return r-l-1;
    }
    public String longestPalindrome(String A) {
        int n = A.length();
        int s = 0;
        int e = 1;
        int ans = 1;
        for(int i = 1; i < n; i++){
            int l = i-1;
            int r = i+1;
            int len = expand(A, l, r);
            if(len > ans){
                s = i - (len-1)/2;
                e = s+len;
                ans = len;
            }
        }
        for(int i = 0; i < n; i++){
            int l = i;
            int r = i+1;
            int len = expand(A, l, r);
            if(len > ans){
                s = i + 1 - (len)/2;
                e = s+len;
                ans = len;
            }
        }
        return A.substring(s, e);
    }
}
