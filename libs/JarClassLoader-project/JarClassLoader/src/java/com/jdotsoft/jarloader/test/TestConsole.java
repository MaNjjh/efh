/*
 * File: TestConsole.java
 * 
 * Copyright (C) 2011 JDotSoft. All Rights Reserved.
 * 
 * $Id: TestConsole.java,v 1.7 2011/10/25 15:14:36 mg Exp $
 */
package com.jdotsoft.jarloader.test;

public class TestConsole {

    private int countPassed = 0;
    private int countFailed = 0;
    
    public void test(String[] args) {
        System.out.println("************************************");
        System.out.println("***    Testing JarClassLoader    ***");
        System.out.println("************************************");
        System.out.println("=== INFO section ===");
        
        TestConsole app = new TestConsole();
        app.print(args);
        
        HelloTopJar j1 = new HelloTopJar();
        j1.print();
        
        HelloInnerJar j2 = new HelloInnerJar();
        j2.print();
        
        System.out.println("\n=== TEST section ===");
        testLoadResourceFromRootJAR();
        testLoadResourceFromInnerJAR();
        testLoadNativeLibrary();
        testNativeCall();
        Class<?> cl = testClassForName();
        testClassNewInstance(cl);
        testPackage();
        testJCLLoading();
        
        System.out.printf("\n*** END-OF-TEST ***" +
        		"\nTotal PASSED: %d" +
        		"\nTotal FAILED: %d", 
                countPassed, countFailed);
    }

    /**
     * Testing JarClassLoader.findResource() method.
     */
    private void testLoadResourceFromRootJAR() {
        System.out.println("\nTEST: Loading resource from root JAR");
        PrintFile p1 = new PrintFile("resources/TopText.txt");
        p1.setsExpectedContent("This is text\nfrom TopText.txt");
        countTestResults(p1.print());
    }

    /**
     * Testing JarClassLoader.findResource() method.
     */
    private void testLoadResourceFromInnerJAR() {
        System.out.println("\nTEST: Loading resource from inner JAR");
        PrintFile p2 = new PrintFile("resources/InnerText.txt");
        p2.setsExpectedContent("This is another text\nfrom InnerText.txt");
        countTestResults(p2.print());
    }

    /**
     * Testing JarClassLoader.findLibrary() method.
     */
    private void testLoadNativeLibrary() {
        System.out.println("\nTEST: Loading native library");
        countTestResults(NativeCode.loadLibrary());
    }
    
    /**
     * Testing JarClassLoader.findLibrary() method.
     */
    private void testNativeCall() {
        System.out.println("\nTEST: Native call");
        NativeCode nc = new NativeCode();
        countTestResults(nc.print());
    }
    
    private Class<?> testClassForName() {
        System.out.println("\nTEST: Class.forName()");
        Class<?> cl = null;
        boolean result = false;
        
        try {
            cl = Class.forName("com.jdotsoft.jarloader.test.ClassForName");
            System.out.println("Loaded: " + cl);
            result = true;
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }
        countTestResults(result);
        return cl;
    }

    private void testClassNewInstance(Class<?> cl) {
        System.out.println("\nTEST: Class newInstance()");
        boolean result = false;
        try {
            if (cl != null) {
                cl.newInstance();
                result = true;
            }
        } catch (Exception e) {
            // InstantiationException
            // IllegalAccessException
            System.out.println(e.toString());
        }
        countTestResults(result);
    }    

    /**
     * Testing JarClassLoader.definePackage() method.
     */
    private void testPackage() {
        System.out.println("\nTEST: Package");
        Package pkg = this.getClass().getPackage();
        System.out.println("Package: " + pkg);
        countTestResults("com.jdotsoft.jarloader.test".equals(pkg.getName()));
    }

    /**
     * Testing JarClassLoader.loadClass() method, Step-0.
     */
    private void testJCLLoading() {
        System.out.println("\nTEST: JarClassLoader loading");
        Class<?> c1 = getClass().getClassLoader().getClass();
        Class<?> c2 = com.jdotsoft.jarloader.JarClassLoader.class;
        System.out.println("getClassLoader().getClass() - " + c1);
        System.out.println("JarClassLoader.class        - " + c2);
        if ("com.jdotsoft.jarloader.JarClassLoader".equals(c1.getName())) {
            countTestResults(c1.equals(c2));
        } else {
            System.out.println("IGNORED - test is not loaded from a JAR");
        }
    }
    
    private void countTestResults(boolean result) {
        if (result) {
            System.out.println("PASSED");
            countPassed++;
        } else {
            System.out.println("FAILED");
            countFailed++;
        }
    }
    
    public static void main(String[] args) {
        TestConsole app = new TestConsole();
        app.test(args);
    } // main()
    
    private void print(String[] args) {
        String PREFIX = getClass().getSimpleName() + ": ";
        System.out.print(PREFIX + "Arguments - ");
        if (args == null) {
            System.out.print("null");
        } else if (args.length == 0) { 
            System.out.print("none");
        } else {
            for (int i = 0;  i < args.length;  i++) {
                System.out.printf("(%d)'%s' ", i+1, args[i]);
            }
        }
        System.out.println();
        System.out.println(PREFIX + "I'm loaded by " + getClass().getClassLoader());
    } // print()
   
} // class TestConsole
