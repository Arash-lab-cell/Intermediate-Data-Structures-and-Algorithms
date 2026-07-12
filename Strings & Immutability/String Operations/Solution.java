public class Solution {
    public String solve(String A) {
        StringBuilder sb = new StringBuilder(A);
        sb.append(sb);
        int n = sb.length();
        for(int i = 0; i < n; i++){
            char ch = sb.charAt(i);
            if(ch >= 'A' && ch <= 'Z'){
                sb.deleteCharAt(i);
                i--;
                n = n-1; //because the length also decreases after deleting the char.
            }
        }
        for(int i = 0; i < n; i++){
            char ch = sb.charAt(i);
            if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'){
                sb.setCharAt(i, '#');
            }
        }
        return sb.toString();
    }
}
