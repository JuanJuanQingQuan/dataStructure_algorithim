import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class KMP {
    public static HashMap<Integer, Integer> next = new HashMap<>();
    public static int KMP_caluate(String S, String W) {
        int i = 0,j = 0;
        //前两项固定
        next.put(0,-1);
        next.put(1,0);

        //计算失配数组
        matchTable(W);
        System.out.println(next.toString());

        //开始匹配
        while (i < S.length() && j < W.length()) {
            char s = S.charAt(i);
            char w = W.charAt(j);
            if (s == w) {//匹配成功，下一位
                i++;
                j++;
            } else {
                j = next.get(j);//失败，获得失配位置
                if (j == -1) {//为-1，说明没有该字符匹配失败且无前缀后缀公共部分，S的下一位重新与W进行比较
                    i++;
                    j = 0;
                }
            }
        }
        return i - j;
    }

    public static void matchTable(String W) {
        for (int i = 2; i < W.length(); i++) {
            int len = 0;
            next.put(i, len);
            String subW = W.substring(0,i);
            for(int j = 1; j < i;j++) {
                String prefix = subW.substring(0,j);
                String suffix = subW.substring(i - j, i);
                if (prefix.equals(suffix)) {
                    len = j;
                    next.put(i,len);
                }
            }
        }
    }
    public static void main(String[] args) {
        String S = "acabaabaabcaccaabc", W ="abaabcac";
        int index = KMP_caluate(S,W);
        System.out.println(index);
    }
}
