package com.weather.bigdata.it.spark.platform.signal

import java.util.Date

import com.weather.bigdata.it.utils.formatUtil.DateFormatUtil

object putJsonStream {
  def main(args: Array[String]): Unit = {
    val dataType:String=args(0)
    val ApplicationTime:Date=DateFormatUtil.YYYYMMDDHHMMSS0(args(1))
    val rootPath:String=PropertiesUtil.getstreamfilepath(dataType,ApplicationTime)

    val GenerationFileName:String=""
    val timeStamp:Date=new Date
    val attribute:String=""
    JsonStream.putJsonStream(rootPath,"test",ApplicationTime,"",new Date,"")
  }
}
