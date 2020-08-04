package psn.xiongfeng.tool.utils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class ObjectAnalyzer {
    private ArrayList<Object> visited = new ArrayList<>();

    //将对象的详细信息处理成字符串返回
    private String toString(Object obj) {
        if (obj == null) {
            return "null";
        }
        //防止数组，list等存放自身的引用
        if (visited.contains(obj)) {
            return "...";
        }
        visited.add(obj);
        Class<?> cl = obj.getClass();
        if (cl == String.class) {
            return (String) obj;
        }
        if (cl.isArray()) {
            StringBuilder result = new StringBuilder(cl.getComponentType().toString());
            result.append("[]{");
            for (int i = 0; i < Array.getLength(obj); i++) {
                if (i > 0) {
                    result.append(",");
                }
                Object val = Array.get(obj, i);
                if (cl.getComponentType().isPrimitive()) {
                    result.append(val);
                } else {
                    result.append(toString(val));//递归调用
                }

            }
            return result.append("}").toString();
        }

        String clName = cl.getName();
        StringBuilder result = new StringBuilder(clName);
        do {
            result.append("[");
            Field[] fields = cl.getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);
            for (Field field : fields) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    if (!result.toString().endsWith("[")) {
                        result.append(",");
                    }
                    result.append(field.getName()).append("=");
                    try {
                        Class<?> t = field.getType();
                        Object val = field.get(obj);
                        if (t.isPrimitive()) {
                            result.append(val.toString());
                        } else {
                            result.append(toString(val));//如果是对象引用则递归
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            result.append("]");
            cl = cl.getSuperclass();//获取父类信息
        } while (cl != null && cl != Object.class);//不把Object算在父类中
        return result.toString();
    }
}
