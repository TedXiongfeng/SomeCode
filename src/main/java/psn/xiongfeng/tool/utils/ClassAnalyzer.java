package psn.xiongfeng.tool.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ClassAnalyzer {

    private final String tab = "    ";

    public String toString(String className) {
        Class<?> cl = null;
        try {
            cl = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return this.toString(cl);
    }

    public String toString(Class cl) {
        StringBuilder builder = new StringBuilder();
        Class supercl = cl.getSuperclass();
        int classInt = cl.getModifiers();
        String classModifier = Modifier.toString(classInt);
        if (classModifier.length() > 0) {
            builder.append(classModifier).append(" ");
        }
        builder.append("class ").append(cl.getName());
        if (supercl != null && supercl != Object.class) {
            builder.append(" extends ").append(supercl.getName());
        }
        StringBuilder consBuilder = printConstructors(cl);
        StringBuilder methodsBuilder = printMethods(cl);
        StringBuilder fieldsBuilder = printFields(cl);
        builder.append(" {\n").append(fieldsBuilder).append(methodsBuilder).append(consBuilder).append("}");
        return builder.toString();
    }

    private StringBuilder printConstructors(Class cl) {
        StringBuilder builder = new StringBuilder();
        Constructor[] constructors = cl.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            String name = constructor.getName();
            builder.append(tab);
            int constructorInt = constructor.getModifiers();
            String consModifier = Modifier.toString(constructorInt);
            if (consModifier.length() > 0) {
                builder.append(consModifier).append(" ");
            }
            builder.append(name).append(" (");
            Class[] paramTypes = constructor.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) {
                    builder.append(", ");
                }
                builder.append(paramTypes[i].getName());
            }
            builder.append(");\n");
        }
        return builder;
    }

    private StringBuilder printMethods(Class cl) {
        StringBuilder builder = new StringBuilder();
        Method[] methods = cl.getDeclaredMethods();
        for (Method method : methods) {
            Class<?> returnType = method.getReturnType();
            String typeName = returnType.getName();
            if (typeName.startsWith("[")){//数组会显示为[开头
                typeName = typeName.substring(2);
                typeName = typeName.replace(";", "[]");//将;结尾替换为[]结尾
            }
            String name = method.getName();
            builder.append(tab);
            int methodInt = method.getModifiers();
            String methodModifier = Modifier.toString(methodInt);
            if (methodModifier.length() > 0) {
                builder.append(methodModifier).append(" ");
            }
            builder.append(typeName).append(" ").append(name).append(" (");
            Class<?>[] paramTypes = method.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) {
                    builder.append(", ");
                }
                builder.append(paramTypes[i].getName());
            }
            builder.append(");\n");
        }
        return builder;
    }

    private StringBuilder printFields(Class cl) {
        StringBuilder builder = new StringBuilder();
        Field[] fields = cl.getDeclaredFields();
        for (Field field : fields) {
            Class<?> type = field.getType();
            String typeName = type.getName();
            if (typeName.startsWith("[")){
                typeName = typeName.substring(2);
                typeName = typeName.replace(";", "[]");
            }
            String name = field.getName();
            builder.append("    ");
            int fieldInt = field.getModifiers();
            String fieldModifier = Modifier.toString(fieldInt);
            if (fieldModifier.length() > 0) {
                builder.append(fieldModifier).append(" ").append(typeName).append(" ");
            }
            builder.append(name).append("\n");
        }
        return builder;
    }
}
