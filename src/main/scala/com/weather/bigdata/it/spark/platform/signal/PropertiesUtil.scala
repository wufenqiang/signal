package com.weather.bigdata.it.spark.platform.signal

import java.io.InputStream
import java.util.Date

import com.weather.bigdata.it.utils.formatUtil.DateFormatUtil
import org.apache.log4j.Logger

private object PropertiesUtil {
  private val prop:java.util.Properties={
    val prop = new java.util.Properties()
    val loader = Thread.currentThread.getContextClassLoader()
    val loadfile: InputStream=loader.getResourceAsStream("signal_config.properties")
    prop.load(loadfile)
    prop
  }

  val changeRFCover=this.prop.getProperty("changeRFCover").toBoolean
  val changeRFCoverAll=this.prop.getProperty("changeRFCoverAll").toBoolean

  def getstreamfilepath(dataType:String,date:Date): String ={
    val dateStr = DateFormatUtil.YYYYMMDDStr0(date)
    val RootPath:String=this.prop.getProperty("streamRootPath")
    if(RootPath.endsWith("/")){
      RootPath+dataType+"/"+dateStr+"/"
    }else{
      RootPath+"/"+dataType+"/"+dateStr+"/"
    }
  }
}
