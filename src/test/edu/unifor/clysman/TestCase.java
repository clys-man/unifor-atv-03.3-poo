package edu.unifor.clysman;

import java.lang.reflect.Method;

abstract public class TestCase {
    private static final String RED_COLOR = "[31m";
    private static final String GREEN_COLOR = "[32m";
    private static final String MESSAGE = (char)27 + "%color" +  "%className: %methodName %message" + (char)27 + "[0m";

    public <T> void assertEquals(T a, T b) {
        if (a.equals(b)) {
            sendAssertMessage(false);
            return;
        }

        sendAssertMessage(true);
    }

    private void sendAssertMessage(boolean error) {
        String message = getAssertMessage(error);
        System.out.println(message);
    }

    private String getAssertMessage(boolean error) {
        StackTraceElement method = Thread.currentThread().getStackTrace()[4];
        String className = method.getClassName();
        String methodName = method.getMethodName();
        String errorMessage = error ? "not passed" : "passed";
        String color = error ? RED_COLOR : GREEN_COLOR;

        return MESSAGE
                .replace("%color", color)
                .replace("%className", className)
                .replace("%methodName", methodName)
                .replace("%message", errorMessage);
    }

    public void run() {
        Class<TestCase> c = (Class<TestCase>) this.getClass();
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("test")) {
                try {
                    method.invoke(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
