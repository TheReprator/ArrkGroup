package reprator.arrk.utility.dateUtility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import reprator.arrk.utility.Validation;

public final class DateUtilsImpl implements DateUtils {

    private static final String EMPTY_TIME = "";

    @Override
    public String format(final long time, final @DateFormat String dateFormat) {
        return getStringFormat(new Date(time), dateFormat);
    }

    @Override
    public String format(final Date date, final @DateFormat String dateFormat) {
        return getStringFormat(date, dateFormat);
    }

    @Override
    public Date parse(String date, @DateFormat String dateFormat) {
        return parseDate(date, dateFormat);
    }

    @Override
    public String format(String date, @DateFormat String fromDateFormat, @DateFormat String toDateFormat) {
        Date dateParsed = parse(date, fromDateFormat);
        return getStringFormat(dateParsed, toDateFormat);
    }

    @Override
    public String format(String stringDate, @DateFormat String fromDateFormat, @DateFormat String toDateFormat, String timeZone) {
        Date date = parse(stringDate, fromDateFormat);

        TimeZone tm = TimeZone.getTimeZone(timeZone);
        int offset = tm.getRawOffset() + tm.getDSTSavings();
        long nTime =  date.getTime() + offset;

        return getStringFormat(new Date(nTime), toDateFormat);
    }

    private String getStringFormat(final Date time, final @DateFormat String format) {
        if (time == null) {
            return EMPTY_TIME;
        }
        SimpleDateFormat dateFormat = getSimpleDateFormat(format, Locale.getDefault());
        return dateFormat.format(time);
    }

    private Date parseDate(final String date, final @DateFormat String format) {
        SimpleDateFormat simpleDateFormat = getSimpleDateFormat(format, Locale.getDefault());
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private SimpleDateFormat getSimpleDateFormat(@DateFormat String dateFormat, Locale locale)
    {
        return new SimpleDateFormat(dateFormat, locale);
    }

    @Override
    public long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public Calendar getCalendar() {
        return Calendar.getInstance();
    }

    @Override
    public int getFieldFromCalendar(Date date, int fieldName) {
        Calendar cal = getCalendar();
        cal.setTime(date);
        return (cal.get(fieldName));
    }

    @Override
    public boolean isTodayDate(long timeInMilliseconds)
    {
        return android.text.format.DateUtils.isToday(timeInMilliseconds);
    }

}
