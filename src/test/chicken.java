package test;

import java.util.*;

public class chicken {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> list=new ArrayList();
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            list.add(num);
        }
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String str1 = String.valueOf(o1);
                String str2= String.valueOf(o2);
                return (str2+str1).compareTo(str1+str2);
            }
        });
        list.forEach(a -> System.out.print(a));
    }
}
