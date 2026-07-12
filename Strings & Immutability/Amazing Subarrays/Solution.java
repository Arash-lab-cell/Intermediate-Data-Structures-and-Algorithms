public class Solution {
public int solve(String A) {
int n = A.length();
int count = 0;
for(int i = 0; i < n; i++){
char ch = A.charAt(i);
if(ch == 'a' ||
ch == 'e' ||
ch == 'i' ||
ch == 'o' ||
ch == 'u' ||
ch == 'A' ||
ch == 'E' ||
ch == 'I' ||
ch == 'O' ||
ch == 'U'){
int m = n-i; //total substring from A in ABEC is 4 i.e. no. of characters from A till C including A and C
count = (count + m) % 10003;
}
}
return count;
}
}
