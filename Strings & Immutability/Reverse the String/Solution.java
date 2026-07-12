public class Solution {
    public String solve(String A) {
        String[] arr = A.trim().split(" ");
        int l = 0;
        int r = arr.length-1;
        while(l < r){
            String temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length; i++){
            if(i != arr.length-1){
                sb.append(arr[i] + " ");
            }else{
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }
}
