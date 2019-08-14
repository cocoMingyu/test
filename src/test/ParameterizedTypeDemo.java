package test;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @ Author kn
 * @ Description : 参数化类型,通过反射获取参数类型
 * getGenericType()  Map.Entry<Long,Short>  返回<> 内的参数类型long，short
 * getRawType()       Map.Entry<Long,Short> 返回<>外的参数类型Map.entry
 * getOwnerType()     Map.Entry<Long,Short> 返回顶层类型，即父类 接口Map
 * @ Date 2019/8/13 19:14
 */
@Component
@Service
public class ParameterizedTypeDemo {
    class ParameterizedBean{
        List<Map<String,String>> list1;
        List list2;
        Map<String,Long> map1;
        Map map2;
        Map.Entry<Long,Short> map3;
    }

    public static void main(String[] args) {
        ParameterizedTypeDemo demo = new ParameterizedTypeDemo();
        demo.test2();
    }

    public void test1(){
        Class<ParameterizedBean> clazz = ParameterizedBean.class;
        Annotation[] annotations = clazz.getAnnotations();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName()+":"+(field.getGenericType() instanceof ParameterizedType));
        }
    }

    public void test2(){
        Class<ParameterizedBean> clazz = ParameterizedBean.class;
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getGenericType() instanceof ParameterizedType){
                ParameterizedType genericType = (ParameterizedType) field.getGenericType();
                Type rawType = genericType.getRawType();
                Type ownerType = genericType.getOwnerType();
                System.out.println(field.getName()+" "+"rawtype:"+rawType+" "+"ownerType:"+ownerType);
                Type[] typeArguments = genericType.getActualTypeArguments();
                for (Type typeArgument : typeArguments) {
                    System.out.println(field.getName()+":"+typeArgument.getTypeName());
                }
            }
        }
    }
}
