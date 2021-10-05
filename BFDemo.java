/**
 * @Author mkbk
 * @Description
 * @Create 2021-10-04 19:19
 */
public class BFDemo {
    public static void main(String[] args) {
        String sLong = "abcdefghijklmnopqrst";
        String sShort = "bcf";
        int bf = BF(sLong, sShort);
        System.out.println(bf);
    }

    public static int BF(String str, String subStr) {
        char[] strLong = str.toCharArray();
        char[] strShort = subStr.toCharArray();
        int L = 0, S = 0;
        int longLen = strLong.length, shortLen = strShort.length;
        //防止越界
        while (L < longLen && S < shortLen) {
            if (strLong[L] == strShort[S]) {
                L++;
                S++;
            } else {
                L = L - (S - 1);
                S = 0;
            }
        }
        if (S == shortLen) {
            return L - S;
        } else {
            return -1;
        }
    }
}
