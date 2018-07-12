package reprator.arrk.utility;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.List;
import java.util.regex.Pattern;

public class Validation
{

    public static <T> boolean isNull(T data) {
        return (null == data);
    }

    public static boolean isEmptyorNull(List collection)
    {
        return (isNull(collection) || collection.isEmpty());
    }

    public static boolean isEmptyorNull(String collection)
    {
        return (!isNull(collection)  && (0 != collection.trim().length()));
    }

    public static boolean isEmpty(String textView) {
        return TextUtils.isEmpty(textView);
    }

}
