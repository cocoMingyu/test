package test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

/**
 * @Author:lkn
 * @Description:
 * @Date:Created in 2018/11/21 12:14
 * @Modified By:
 */
public class zhengze {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        String title1 = "[上海市]已签收,感谢使用顺丰,期待再次为您服务";
        String title2 = "[上海市]快件交给刘警伟，正在派送途中（联系电话：13482325967）";
        String title3 = "[上海市]快件到达 【上海闵行虹桥营业部】";
        String title4 = "[上海市]快件已发车";
        String title5 = "[上海市]快件在【上海虹桥集散中心2】已装车,准备发往 【上海闵行虹桥营业部】";
        String title6 = "[上海市]快件到达 【上海虹桥集散中心2】";
        String title7 = "[盐城市]快件在【盐城盐陆集散点】已装车,准备发往 【上海虹桥集散中心2】";
        String title8 = "[沈阳市/铁岭市/抚顺市]快件在【沈阳古城集散中心】已装车,准备发往 【盐城盐陆集散点】";
        list.add(title1);list.add(title2);list.add(title3);list.add(title4);list.add(title5);list.add(title6);list.add(title7);list.add(title8);
        String string = parseString(title2);
        System.out.println(string);
    }

    public static String parseString(String s) {
        Pattern pattern = compile(".*(上海市).*");
        Matcher matcher = pattern.matcher(s);
        if (matcher.matches()) {
            if (s.contains("虹桥营业部")) {
                s=s.replace("虹桥营业部", "派件点");
            }
            if (s.contains("快件交给")) {
                StringBuffer sb = new StringBuffer(s);
                int namestart = s.indexOf("给");
                s = sb.replace(namestart+1, namestart+4, "派件员").toString();
            }
            if (s.contains("联系电话")){
                StringBuffer sb = new StringBuffer();
                int index = s.lastIndexOf("话");
                s = s.substring(0, index+1);
                s=sb.append(s).append(":021-67303306)").toString();
            }
        }
        return s;
    }
}
