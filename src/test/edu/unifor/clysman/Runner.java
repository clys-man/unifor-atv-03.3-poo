package edu.unifor.clysman;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.Objects;

public class Runner {
    private static final String TEST_FOLDER = "edu/unifor/clysman/";
    private static final String TEST_CLASS = "Test.class";

    public static void main(String[] args) {
        URL root = Thread.currentThread().getContextClassLoader().getResource(TEST_FOLDER);
        assert root != null;

        File[] files = new File(root.getFile()).listFiles();
        assert files != null;

        for (File file : files) {
            if (file.isDirectory()) {
                String folder = file.getName();
                File[] subFiles = Objects.requireNonNull(file.listFiles((dir, name) -> name.endsWith(TEST_CLASS)));

                for (File subFile : subFiles) {
                    runTestFile(subFile, folder);
                }
            }

            if (file.getName().endsWith(TEST_CLASS)) {
                runTestFile(file);
            }
        }
    }

    public static void runTestFile(File file, String folder) {
        String packageName = TEST_FOLDER.replace("/", ".");
        String className = file.getName().replace(".class", "");
        String classNameWithPackage = folder.isBlank()
                ? packageName + className
                : packageName + folder + "." + className;

        try {
            Class<?> clazz = Class.forName(classNameWithPackage);
            if (Modifier.isAbstract(clazz.getModifiers()) || Modifier.isInterface(clazz.getModifiers())) {
                return;
            }

            Object instance = clazz.newInstance();
            Method method = clazz.getMethod("run");
            method.invoke(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void runTestFile(File file) {
        runTestFile(file, "");
    }
}
