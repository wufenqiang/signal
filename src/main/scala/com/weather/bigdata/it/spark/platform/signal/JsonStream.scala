package com.weather.bigdata.it.spark.platform.signal

import java.util.Date

import com.alibaba.fastjson.{JSONObject, JSON => Json}
import com.weather.bigdata.it.utils.formatUtil.DateFormatUtil
import com.weather.bigdata.it.utils.hdfsUtil.{HDFSOperation1, HDFSReadWriteUtil}
import com.weather.bigdata.it.utils.operation.JsonOperation

/**
  * @author wufenqiang Creat on 2018/4/1
  */
object JsonStream {
  private val dataType_Key=Constant.dataType_Key
  private val ApplicationTime_Key=Constant.ApplicationTime_Key
  private val GenerationFileName_Key=Constant.GenerationFileName_Key
  private val timeStamp_Key=Constant.timeStamp_Key
  private val attribute_Key=Constant.attribute_Key

  private val changeRFcoverKey=Constant.changeRFcoverKey
  private val changeRFcoverAllKey=Constant.changeRFcoverAllKey

  def getJsonStream(dataType:String,ApplicationTime:Date,GenerationFileName:String,timeStamp:Date=new Date,attribute:String):String={
    val jo:JSONObject = new JSONObject(true)
    val timeStampStr = DateFormatUtil.YYYYMMDDHHMMSSStr1(timeStamp)
    val AppTimeStr = DateFormatUtil.YYYYMMDDHHMMSSStr0(ApplicationTime)
    //    val attributeStr=attributeJson.toString
    jo.put(this.dataType_Key,dataType)
    jo.put(this.ApplicationTime_Key,AppTimeStr)
    jo.put(this.GenerationFileName_Key,GenerationFileName)
    jo.put(this.timeStamp_Key,timeStampStr)
    jo.put(this.attribute_Key,attribute)
    jo.toString
  }
  def putJsonStream(rootPath:String,dataType:String,ApplicationTime:Date,GenerationFileName:String,timeStamp:Date=new Date,attribute:String): Boolean ={
    val timeStampStr0 = DateFormatUtil.YYYYMMDDHHMMSSStr0(timeStamp)
    val applicationStr = DateFormatUtil.YYYYMMDDHHStr0(ApplicationTime)

    val fileName0=dataType+"_"+applicationStr+"_"+timeStampStr0+".txt"

    val josonStr=this.getJsonStream(dataType,ApplicationTime,GenerationFileName,timeStamp,attribute)
    if(!HDFSOperation1.exists(rootPath)){
      HDFSOperation1.mkdirs(rootPath)
    }
    val file=rootPath+fileName0

    val flag=HDFSReadWriteUtil.writeTXT(file,josonStr,false)
    if(flag){
      val msg="putJsonStream:"+rootPath+fileName0+";josonStr="+josonStr
      println(msg)
    }
    flag
  }
  def analysisJsonStream(myJson:JSONObject):(String,Date,String,Date,String)={
    val dataType=myJson.getString(this.dataType_Key)
    val ApplicationTimeStr=myJson.getString(this.ApplicationTime_Key)
    val GenerationFileName=myJson.getString(this.GenerationFileName_Key)
    val timeStampStr=myJson.getString(this.timeStamp_Key)
    val attributeJson:String=myJson.getString(this.attribute_Key)

    val ApplicationTime = DateFormatUtil.YYYYMMDDHHMMSS0(ApplicationTimeStr)
    val timeStamp = DateFormatUtil.YYYYMMDDHHMMSS1(timeStampStr)
    (dataType,ApplicationTime,GenerationFileName,timeStamp,attributeJson)
  }
  def analysisJsonStream(myJsonstr:String):(String,Date,String,Date,String)={
    val myjoson:JSONObject= JsonOperation.Str2JSONObject(myJsonstr)
    this.analysisJsonStream(myjoson)
  }
  def analysisJsonFile(signalFile:String):Array[(String,Date,String,Date,String)] ={
    val signalMsgs=HDFSReadWriteUtil.readTXT(signalFile)
    signalMsgs.map(signalMsg=>this.analysisJsonStream(signalMsg))
  }

  def analysisDataType(myJson:JSONObject): String ={
    this.analysisJsonStream(myJson)._1
  }
  def analysisDataType(myJsonstr:String): String ={
    this.analysisJsonStream(myJsonstr)._1
  }

  def getChangeRFattribute(attribute:String): (Boolean,Boolean) ={
    val attributejson=Json.parseObject(attribute)
    val changeRFCover = {
      if (attributejson.containsKey(this.changeRFcoverKey)) {
        attributejson.getString(this.changeRFcoverKey).toBoolean
      } else {
        PropertiesUtil.changeRFCover
      }
    }
    val changeRFCoverAll = {
      if (attributejson.containsKey(this.changeRFcoverAllKey)) {
        attributejson.getString(this.changeRFcoverAllKey).toBoolean
      } else {
        PropertiesUtil.changeRFCoverAll
      }
    }
    (changeRFCover,changeRFCoverAll)
  }

  def getfilepath(dataType:String,date:Date): String = PropertiesUtil.getstreamfilepath(dataType,date)



  def main(args: Array[String]): Unit = {

  }

}
