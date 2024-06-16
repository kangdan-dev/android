// MyAIDLInterface.aidl
package com.example.sc_outer;

// Declare any non-default types here with import statements

interface MyAIDLInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

    int getMaxDuration();
    void start();
    void stop();


}