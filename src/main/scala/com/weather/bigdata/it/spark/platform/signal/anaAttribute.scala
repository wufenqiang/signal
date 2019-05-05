package com.weather.bigdata.it.spark.platform.signal

import com.alibaba.fastjson.{JSON, JSONObject}

object anaAttribute {
  private val timeStepsKey:String=Constant.timeStepsKey
  private val splitFileKey:String=Constant.splitFileKey

  def getTimeSteps(attribute:JSONObject): Array[Double] ={
    if(attribute.containsKey(this.timeStepsKey)){
      attribute.getString(this.timeStepsKey).split(",").map(s=>s.toDouble)
    }else{
      Array.empty[Double]
    }
  }
  def getTimeSteps(attribute:String):Array[Double]=this.getTimeSteps(JSON.parseObject(attribute))
  def returnTimeSteps(attribute:JSONObject,timeSteps:Array[Double]): JSONObject ={
    val timeStepsStr:String=timeSteps.map(d=>d.toString).reduce((x,y)=>(x+","+y))
    attribute.put(this.timeStepsKey, timeStepsStr)
    attribute
  }
  def putTimeSteps(attribute:JSONObject,timeSteps:Array[Double]): Unit ={
    val timeStepsStr:String=timeSteps.map(d=>d.toString).reduce((x,y)=>(x+","+y))
    attribute.put(this.timeStepsKey, timeStepsStr)
  }

  def getSplitFile(attribute:JSONObject): String ={
    if(attribute.containsKey(this.splitFileKey)){
      attribute.getString(this.splitFileKey)
    }else{
      ""
    }
  }
  def returnSplitFile(attribute:JSONObject,jsonFile:String): JSONObject ={
    attribute.put(this.splitFileKey,jsonFile)
    attribute
  }
  def putSplitFile(attribute:JSONObject,jsonFile:String): Unit ={
    attribute.put(this.splitFileKey,jsonFile)
  }
}
