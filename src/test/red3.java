package test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author:lkn
 * @Description:
 * @Date:Created in 2018/10/13 18:08
 * @Modified By:
 */
public class red3 {
    public static void main(String[] args) {
        /* for (int j = 0; j< 10; j++) {*/
        int peopleCount = 20;
        BigDecimal totalMoney = new BigDecimal(50);
        BigDecimal min = new BigDecimal(1);
        BigDecimal max = new BigDecimal(30);
        BigDecimal avg = totalMoney.divide(new BigDecimal(peopleCount), 1, BigDecimal.ROUND_HALF_EVEN);
        List<BigDecimal> list = new ArrayList<>();
        if (min.compareTo(avg) >= 0 || max.compareTo(avg) <= 0) {
            list = getRandomMoney(peopleCount, totalMoney, new BigDecimal(0.1), avg.multiply(new BigDecimal(2)));
        } else {
            list = getRandomMoney(peopleCount, totalMoney, min, max);
        }
        System.out.println("个数：" + list.size() + "============================");
        BigDecimal total = new BigDecimal(0);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + "\t");
            total = total.add(list.get(i));
        }
        System.out.println();
        System.out.println("总数：" + total + "================================");
    }
    /*   }*/

    public static List<BigDecimal> getRandomMoney(int peopleCount, BigDecimal totalMoney, BigDecimal min, BigDecimal max) {
        double avg = totalMoney.divide(new BigDecimal(peopleCount), 1, BigDecimal.ROUND_HALF_EVEN).doubleValue();
        List<BigDecimal> list = new ArrayList<>();
        double min2 = min.doubleValue();
        double max2 = max.doubleValue();
        list.add(max);
        totalMoney = totalMoney.subtract(max);
        for (int i = 1; i < peopleCount - 1; i++) {
            BigDecimal randommoney = new BigDecimal(Math.random() * (max2 - min2) + min2).setScale(1, BigDecimal.ROUND_DOWN);
            double a = totalMoney.subtract(randommoney).doubleValue();
            double b = (peopleCount - i - 1) * avg;
            if (a < b) {
                randommoney = new BigDecimal(Math.random() * (avg - min2) + min2).setScale(1, BigDecimal.ROUND_DOWN);
            }
            if (a > b) {
                randommoney = new BigDecimal(Math.random() * (max2 - avg) + avg).setScale(1, BigDecimal.ROUND_DOWN);
            }
            totalMoney = totalMoney.subtract(randommoney);
            list.add(randommoney);

            if (i == peopleCount - 2) {
                if (totalMoney.doubleValue() >= max2) {
                    System.out.println("最后一个大于最大值======" + totalMoney);
                    BigDecimal minprice = Collections.min(list);
                    int minindex = list.indexOf(minprice);
                    BigDecimal cha = totalMoney.subtract(max).add(new BigDecimal(0.1)).setScale(1, BigDecimal.ROUND_DOWN);
                    System.out.println("差值============" + cha);
                    totalMoney = totalMoney.subtract(cha);
                    list.set(minindex, minprice.add(cha));
                }
                if (totalMoney.doubleValue() < min2) {
                    System.out.println("最后一个小于最小值======" + totalMoney);
                    BigDecimal maxprice = Collections.max(list);
                    int maxindex = list.indexOf(maxprice);
                    BigDecimal cha = min.subtract(totalMoney);
                    totalMoney = totalMoney.add(cha);
                    list.set(maxindex, maxprice.subtract(cha));
                }
                list.add(totalMoney);
            }
        }
        return list;
    }
}
