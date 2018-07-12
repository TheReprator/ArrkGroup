package reprator.arrk.controllers.constants;

import android.support.annotation.IntDef;
import android.util.SparseArray;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;

import reprator.arrk.R;

public class NumericEnums {

    public static final int NO_INTERNET                                 =         R.string.retrofit_no_interent;
    public static final int PARSING_EXCEPTION                           =         R.string.retrofit_parsing_exception;
    public static final int SOCKET_TIMED_OUT                            =         R.string.retrofit_socket_timeOut;
    public static final int UNKNOWN_ERROR_OCCURRED                      =         R.string.retrofit_unknownError;


    @IntDef({NO_INTERNET, PARSING_EXCEPTION, SOCKET_TIMED_OUT, UNKNOWN_ERROR_OCCURRED,})
    @Retention(RetentionPolicy.SOURCE)
    public @interface NetworkError {
    }

}
