package test;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class calendar {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestr ="2019-01-15 20:17:13";
        Date usetime = df.parse(timestr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(usetime);
        calendar.add(Calendar.DAY_OF_MONTH,10);
        Date endtime = calendar.getTime();
        Date nowtime = new Date();
        System.out.println(endtime);
        System.out.println(nowtime);

        int i = endtime.compareTo(nowtime);
        boolean before = nowtime.before(endtime);
        System.out.println(i);
        System.out.println(before);

    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime 当前时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     * @author jqlin
     */
    public static boolean isEffectiveDate(String nowTime, String startTime, String endTime) {
        System.out.println(nowTime+"----------"+startTime+"----------"+endTime);
        if (nowTime == startTime
                || nowTime == endTime) {
            return true;
        }
       if (nowTime.compareTo(startTime)>0&&nowTime.compareTo(endTime)<0){
           return true;
       }
       else{
           return false;
       }
    }
   /* public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }*/
}