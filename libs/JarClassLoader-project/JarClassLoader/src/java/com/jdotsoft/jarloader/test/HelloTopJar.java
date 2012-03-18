/*
 * File: HelloTopJar.java
 * 
 * Copyright (C) 2011 JDotSoft. All Rights Reserved.
 * 
 * $Id: HelloTopJar.java,v 1.4 2011/10/17 16:56:03 mg Exp $
 */
package com.jdotsoft.jarloader.test;

public class HelloTopJar {

    public void print() {
        System.out.println("HelloTopJar: I'm loaded by " + getClass().getClassLoader());
    } // print()
    
} // class HelloTopJar
