import java.util.Arrays;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-10-05 14:42
 */
public class KMPDemo {
    public static void main(String[] args) {
        String str = "BBC ABCDAB ABCDABCDABDE";
        String subStr = "BCDABD";
        int[] ints = kmpTable(subStr);
        int index = KMPSearch(str, subStr, ints);
        System.out.println(index);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * @param str1 主字符串
     * @param str2 子字符串
     * @param next 部分匹配表
     * @return 返回子字符串在主字符串的位置
     */
    public static int KMPSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            /*
            对j = next[j - 1]的解读
            j此时指向的字符已经和主字符串不匹配了，那么j-1就是前一个字符。
            如果j前面的字符串存在前后缀，那么next[j-1]就是前后缀的长度，由于下标是从0开始的，那么next[j-1]刚好就是前缀字符串的
            下一个字符，由于前后缀是相等的，后缀已经匹配不上了，那么就尝试用前缀来匹配，将j指针指向前缀的后面。
            如果j前面的字符串不存在前后缀，那么就直接将j指针指向子字符串头部
             */
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            /*
            str2遍历完就说明找到了，直接return
             */
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * 用于得到目标字符串的部分匹配表
     *
     * @param dest 目标字符串
     * @return 返回目标字符串的部分匹配表（int[]）
     */
    public static int[] kmpTable(String dest) {
        int[] next = new int[dest.length()];
        //字符串第一个字符的前后缀都为[]，所以是部分匹配值就是0
        next[0] = 0;
        //那么遍历指针index就从下标为1的位置开始
        for (int index = 1, auxiIndex = 0; index < dest.length(); index++) {
            /*
            对auxiIndex = next[auxiIndex - 1];的解读：

            如果匹配成功的的大前后缀中还有小前后缀。例如：ABCABDABCABC
            大前后缀是ABCAB ABCAB
            每个大前缀中的小前后缀是AB

            那么可以将字符串抽象化：大前缀（小前缀+...+小后缀）+D...+大后缀（小前缀+...+小后缀）+C...
            因为大前缀=大后缀，那么大前缀中的小前后缀和大后缀中的小前后缀也是相等的

            当指针一个指向大后缀后面的C，一个指向大前缀后面的D时，很明显是不匹配的
            按理说此时匹配指针应该返回到字符串开头，再重新进行匹配。但是我们会发现当指针指向大后缀的C时，实际整体是有一个前后缀ABC
            所以我们不能将指针回到字符串开头。那我们应该回到那里？
            当程序执行到大后缀后面的C时，发现和大前缀后面的D不匹配。幸运的是我们的大前后缀中还有小前后缀。当用大前缀中的小后缀
            匹配不成功时，是否可以用大前缀中的小前缀来匹配？我们尝试一把，我们将指针指向大前缀中的小前缀后的C，此时刚好匹配成功
            上面是特殊情况，当字符串是ABCABD时，是否可行呢？
            是可行的！由于前后缀中不包含别的前后缀，那么当匹配不成功时就直接返回字符串首部，那么D对应的就是0

            总结：
                情况一：当前后缀中包含的还有小前后缀，那么指针就返回大前缀中的小前缀的下一个字符
                情况二：当前后缀中不包含小前后缀，那么就直接返回字符串首部
             */
            while (auxiIndex > 0 && dest.charAt(index) != dest.charAt(auxiIndex)) {
                auxiIndex = next[auxiIndex - 1];
            }
            /*
            辅助指针（auxIndex）一开始指向字符串头部，遍历指针（index）一开始指向字符串下标为1的位置
            从头到尾进行遍历字符串，当遍历指针和辅助指针相等时，那么就auxIndex增加，由于for循环，后序index也会增加
            新一轮循环的时候，两个指针都是新的位置
             */
            if (dest.charAt(index) == dest.charAt(auxiIndex)) {
                auxiIndex++;
            }
            //得到对应下标字符的部分匹配值
            next[index] = auxiIndex;
        }
        return next;
    }

}
