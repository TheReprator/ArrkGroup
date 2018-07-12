package reprator.arrk.utility;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;

import java.io.File;

public class PlainJavaFunction
{
    /***************************************
     * Get Class Name
     ********************************************/

    public static String getClassName(Class classa) {
       /* String c=classa.getSimpleName();
        LogUtil.e("ClassName:-",c+"");*/
        return classa.getSimpleName();
    }

    public static String getClassName(Fragment classa) {
        return getClassName(classa.getClass());
    }


    /***************************************
     * Mime type of file
     ********************************************/
    public static String getMimeType(String url) {
        String type = null;
        String fileTypeMime = MimeTypeMap.getFileExtensionFromUrl(url);
        if (fileTypeMime != null)
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileTypeMime);
        return type;
    }

    public static String getMimeType(File file) {
        return getMimeType(Uri.fromFile(file).toString());
    }

    /***************************************
     * get extension of file
     ********************************************/
    public static String getFileType(String url) {
        return MimeTypeMap.getFileExtensionFromUrl(url);
    }

    /***************************************
     * File Name from a URL
     ********************************************/
    public static String getFileName(String url) {
        return URLUtil.guessFileName(url, null, null);
    }

    public static String removeAllNonNumericCharacters(String string)
    {
        return string.replaceAll("[^\\d.]", "");
    }
}

//https://github.com/AllenCoder/SuperUtils/blob/master/apputils/src/main/java/com/allen/apputils/RegexUtils.java
//https://github.com/AllenCoder/SuperUtils/blob/master/apputils/src/main/java/com/allen/apputils/NumberUtil.java