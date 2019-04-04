//package com.weather.bigdata.itmt.mainClass
//
//import com.weather.bigdata.itmt.MyInterface.signalInput1
//
//object go1 {
//  def main(args:Array[String]): Unit ={
//    val className:String=args(0)
//    val signalMsg:String=args(1)
//    val splitFile:String=args(2)
//    PropertiesUtil.log.info("hello Go.")
//    try{
//      PropertiesUtil.log.info("className="+className)
//      val thego:signalInput1=Class.forName(className).asInstanceOf[signalInput1]
//      thego.go(signalMsg,splitFile)
//    }catch{
//      case e:Exception=>{
//        PropertiesUtil.log.error(e)
//      }
//    }
//
//  }
//}
