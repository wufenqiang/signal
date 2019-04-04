//package com.weather.bigdata.itmt.mainClass
//
//import java.net.URLClassLoader
//
//private object felixloader {
//  def invoke(df: Dataset[Row]):Unit = {
//    val f = new File("F:\\360CloudUI\\textprocess.jar")
//    val loader = new URLClassLoader(Array(f.toURI().toURL()), getClass.getClassLoader)
//    val clazz = loader.loadClass("cc.eabour.spark.TextProcess")
//    val process = clazz.getDeclaredMethod("process", classOf[String], classOf[Dataset[_]])
//    process.invoke(null, "text", df)
//  }
//}
