package lixiaoxiao.bwie.com.newstitlelixiaoxiao.networkinfo.httputils;

/**
 * 1. 类的用途
 * 2. @author : do  you  heat
 * 3. @date 2017/3/9 20:28
 */
public class HttpUtils {

 static     String  URL="http://c.m.163.com/nc/article/headline/";

 static    String  VIDEO_URL="http://c.3g.163.com/nc/video/list/";

    static   String[]  VIDEO_ID=new String[]{
            "V9LG4B3A0/n/",
            "V9LG4CHOR/n/" ,
            "V9LG4E6VR/n/",
            "00850FRB/n/"
    };
   static String[]  URL_ID=new  String[]{
            "T1348648037603/",
            "T1348648517839/",
            "T1348649580692/",
            "T1348648756099/",
            "T1348648650048/",
            "T1350383429665/",
            "T1348650593803/",
            "T1348650839000/",
            "T1348654204705/",
            "T1348648141035/",
            "T1397116135282/",
            "T1348654151579/",
    };
    public static  String  getUrl(int  i,String  page){
        return  URL+URL_ID[i]+page+"-20.html";
    }
    public static  String  getVideoUrl(int  i,String  page){
        return VIDEO_URL+VIDEO_ID[i]+page+"-10.html";
    }
}
