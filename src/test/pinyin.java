package test;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.text.Collator;
import java.util.*;

/**
 * @Author:lkn
 * @Description:
 * @Date:Created in 2018/11/9 15:29
 * @Modified By:
 */
public class pinyin {
    public static void main(String[] args) {
        String[] arrays = new String[]{"gyu", "sdf", "zf", "大同", "收到", "地方", "三等分", "的人", "反对高铁", "泛代数", "上的投入", "和国家"};
        List<String> stringList = Arrays.asList(arrays);
        List<String> sort = sort(stringList);
        sort.forEach(a -> System.out.println(a));
    }
    public static List<String> sort(List<String> list) {
       List<String> sortList=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            String alphabet = str.substring(0, 1);
            /*判断首字符是否为中文，如果是中文便将首字符拼音的首字母和&符号加在字符串前面*/
            if (alphabet.matches("[\\u4e00-\\u9fa5]+")) {
                str = getAlphabet(str) + "&" + str;
                list.set(i,str);
            }
        }
        /*设置排序语言环境*/
        Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);
        Collections.sort(list, com);
        /*遍历数组，去除标识符&及首字母*/
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            if (str.contains("&") && str.indexOf("&") == 1) {
                String set = str.substring(2);
                sortList.add(set);
            }else {
                sortList.add(str);
            }
        }
        return sortList;
    }

    public static String getAlphabet(String str) {
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        // 输出拼音全部小写
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        // 不带声调
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        String pinyin = null;
        try {
            pinyin = (String) PinyinHelper.toHanyuPinyinStringArray(str.charAt(0), defaultFormat)[0];
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return pinyin.substring(0, 1);
    }

}


