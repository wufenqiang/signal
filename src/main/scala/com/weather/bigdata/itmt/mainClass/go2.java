//package com.weather.bigdata.itmt.mainClass;
//
//
//import com.weather.bigdata.itmt.MyInterface.signalInput1;
//
//public class go2 {
//    public static void main(String[] args){
//        String className0=args[0];
//        String signalMsg=args[1];
//        String splitFile=args[2];
//
//        signalInput1 thego= null;
//        try {
//            thego = (signalInput1)Class.forName(className0).newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        thego.go(signalMsg,splitFile);
//    }
//}
