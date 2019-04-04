package com.weather.bigdata.itmt.mainClass

import com.weather.bigdata.itmt.MyInterface.signalInput

object go {
  def main(args:Array[String]): Unit ={
    val className:String=args(0)
    val signalMsg:String=args(1)
    val splitFile:String=args(2)
    PropertiesUtil.log.info("hello Go.signalInput")
    try{
      PropertiesUtil.log.info("className="+className)
      val thego:signalInput=Class.forName(className,true,ClassLoader.getSystemClassLoader).asInstanceOf[signalInput]

      //      URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory())
      //      val url=URI.create("hdfs://dataflow-node-1:9000/ser/program/platform/Gr2b_VIS/region4/Gr2b_VIS-0.0.1-felix.jar").toURL()
      //      val loader=new URLClassLoader(Array(url), getClass.getClassLoader)
      //      val thego:signalInput=loader.loadClass(className).asInstanceOf[signalInput]

      val flag=thego.strategy(signalMsg,splitFile)
      if(flag){
        thego.truedone(signalMsg,splitFile)
      }else{
        thego.falsedone(signalMsg,splitFile)
      }

    }catch{
      case e:Exception=>{
        PropertiesUtil.log.error(e)
        e.printStackTrace()
      }
    }

  }
}
