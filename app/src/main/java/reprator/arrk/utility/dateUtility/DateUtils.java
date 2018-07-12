package reprator.arrk.utility.dateUtility;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Calendar;
import java.util.Date;

public interface DateUtils {
    String DAY                              =  "EEE";
    String HOUR_24                          =  "HH";
    String HOUR                             =  "hh";
    String SECONDS                          =  "ss";
    String MINUTES                          =  "mm";
    String DAY_IN_MONTH                     =  "dd";
    String MONTH_NUMBER                     =  "MM";
    String YEAR                             =  "yy";
    String YEAR_FULL                        =  YEAR +YEAR;
    String MONTH_3CHAR                      =  "M" + MONTH_NUMBER;
    String AM_PM                            =  "a";

    String FOR_HOUR_MNUTE                   =  HOUR + ":" + MINUTES + " " + AM_PM;
    String FOR_HOUR_MNUTE_SS                =  HOUR + ":" + MINUTES + ":" + SECONDS + " " + AM_PM;
    String FOR_HOUR_MINUTE_SS_24            =  HOUR_24 + ":" + MINUTES + ":" + SECONDS;
    String FOR_MMM_S_DD                     =  MONTH_3CHAR + " " + DAY_IN_MONTH;
    String FOR_DD_S_MM_S_YY                 =  DAY_IN_MONTH + "/" + MONTH_NUMBER + "/" + YEAR;
    String FOR_YYYY_MM_DD                   =  YEAR_FULL + "-" + MONTH_NUMBER + "-" + DAY_IN_MONTH;
    String FOR_MMMM_S_DD_DAY                =  MONTH_NUMBER + MONTH_NUMBER + " " + DAY_IN_MONTH+" - "+ DAY;
    String FOR_MMMM_S_YYYY                  =  MONTH_NUMBER + MONTH_NUMBER + " "+ YEAR_FULL;
    String FOR_MM_S_DD_S_YY                 =  MONTH_NUMBER + "/" + DAY_IN_MONTH + "/" + YEAR;
    String FOR_YYYY_MM_DD_S_HH_MM           =  FOR_YYYY_MM_DD + " " + FOR_HOUR_MNUTE_SS;
    String FOR_MM_S_YYYY                    =  MONTH_NUMBER + "/" + YEAR_FULL;
    String FOR_YYYY_MM_DD_S_HH_MM_24        =  FOR_YYYY_MM_DD + " " + FOR_HOUR_MINUTE_SS_24;
    String FOR_DD_S_MM_S_YY_HOUR_MNUTE      =  FOR_DD_S_MM_S_YY + " " + FOR_HOUR_MNUTE;
    String FOR_SERVER_CONSTANT              =  FOR_YYYY_MM_DD + "'T'" + HOUR_24 + ":" + MINUTES + ":" + SECONDS + ".SSS'Z'";
    String FOR_CURRENT_SERVER_FORMAT        =  FOR_YYYY_MM_DD + "'T'"+ FOR_HOUR_MINUTE_SS_24;
    String FOR_RESERVATION_FORMAT           =  FOR_MM_S_DD_S_YY + "' at '" +FOR_HOUR_MNUTE;
    String FOR_MAKE_RESERVATION_FORMAT      =  FOR_MM_S_DD_S_YY + "' | '" + FOR_HOUR_MNUTE;
    String FOR_RESERVATION_FORMAT_FULL_YEAR =  MONTH_NUMBER + "/" + DAY_IN_MONTH + "/" + YEAR_FULL + " "+ FOR_HOUR_MNUTE_SS;

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({FOR_HOUR_MNUTE, FOR_HOUR_MNUTE_SS, FOR_HOUR_MINUTE_SS_24, FOR_MMM_S_DD, FOR_DD_S_MM_S_YY, FOR_YYYY_MM_DD,
            FOR_MMMM_S_DD_DAY, FOR_MMMM_S_YYYY, FOR_MM_S_DD_S_YY, FOR_YYYY_MM_DD_S_HH_MM, FOR_MM_S_YYYY, FOR_SERVER_CONSTANT,
            FOR_YYYY_MM_DD_S_HH_MM_24, FOR_DD_S_MM_S_YY_HOUR_MNUTE, FOR_SERVER_CONSTANT, FOR_CURRENT_SERVER_FORMAT, FOR_RESERVATION_FORMAT,
            FOR_RESERVATION_FORMAT, FOR_MAKE_RESERVATION_FORMAT, FOR_RESERVATION_FORMAT_FULL_YEAR})
    @interface DateFormat { }

    String format(long time, @DateFormat String dateFormat);

    String format(Date date, @DateFormat String dateFormat);

    Date parse(String date, @DateFormat String dateFormat);

    String format(String date, @DateFormat String fromDateFormat, @DateFormat String toDateFormat);

    String format(String stringDate, @DateFormat String fromDateFormat,
                  @DateFormat String toDateFormat, String timeZone);

    long getDifferenceDays(Date d1, Date d2);

    boolean isTodayDate(long timeInMilliseconds);

    Calendar getCalendar();

    int getFieldFromCalendar(Date date, int fieldName);
}
