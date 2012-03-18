/*
 * File: HelloInnerJar.java
 * 
 * Copyright (C) 2011 JDotSoft. All Rights Reserved.
 * 
 * $Id: HelloInnerJar.java,v 1.4 2011/10/17 16:56:03 mg Exp $
 */
package com.jdotsoft.jarloader.test;

public class HelloInnerJar {

    public void print() {
        System.out.println("HelloInnerJar: I'm loaded by " + getClass().getClassLoader());
    } // print()
    
} // class HelloInnerJar
