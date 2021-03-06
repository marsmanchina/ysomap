package ysomap.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author wh1t3P1g
 * @since 2020/2/20
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Require {

    String name() default "null";
    String type() default "String";
    String detail() default "null";
    String[] bullets() default {};

    class Utils {
        public static HashMap<String,String[]> getRequiresFromFields(Class<?> clazz) {
            HashMap<String,String[]> ret = new LinkedHashMap<>();
            Field[] fields = clazz.getDeclaredFields();
            for(Field field: fields){
                if(field.isAnnotationPresent(Require.class)){
                    Require anno = field.getAnnotation(Require.class);
                    ret.put(anno.name(), new String[]{anno.type(), anno.detail()});
                }
            }
            return ret;
        }

        public static String[] getRequiresFromClass(Class<?> clazz){
            if(clazz.isAnnotationPresent(Require.class)){
                Require anno = clazz.getAnnotation(Require.class);
                return anno.bullets();
            }else{
                return new String[0];
            }
        }
    }
}
