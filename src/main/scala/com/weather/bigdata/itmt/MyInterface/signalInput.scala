package com.weather.bigdata.itmt.MyInterface

trait signalInput {
  def strategy (signalJson: String, splitFile: String): Boolean
  def truedone(signalJson: String, splitFile: String):Unit
  def falsedone(signalJson: String, splitFile: String):Unit
//  final def main (args: Array[String]): Unit={
//    val signalJson=args(0)
//    val splitFile=args(1)
//    val flag=this.strategy(signalJson,splitFile)
//    if(flag){
//      this.truedone(signalJson,splitFile)
//    }else{
//      this.falsedone(signalJson,splitFile)
//    }
//  }
}
