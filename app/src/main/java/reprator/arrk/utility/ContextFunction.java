package reprator.arrk.utility;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by MPROGA1\vikram.singh on 24/6/17.
 */

public class ContextFunction {

    /*******************************
     * Find A View and Cast It
     ***************************************/
    public static <T> T findViewByIdAndCast(Activity activity, int id) {
        return (T) activity.findViewById(id);
    }

    public static <T> T findViewByIdAndCast(View activity, int id) {
        return (T) activity.findViewById(id);
    }

    /*******************************
     * Check to get Activity Instance from Object of framgent
     * Activityt
     ***************************************/
    public static Activity getActivity(Object object) {
        if (object instanceof Activity)
            return ((Activity) object);
        else if (object instanceof Fragment)
            return ((Fragment) object).getActivity();
        else
            return null;
    }
    public static Context getContext(Object object) {
        if (object instanceof Activity)
            return ((Activity) object);
        else if (object instanceof Fragment)
            return ((Fragment) object).getActivity();
        else if(object instanceof Service)
            return ((Service)object).getApplicationContext();
        return null;
    }

    public static Activity getActivity(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    /***************************************
     * Show Toast
     ********************************************/
    public static void showToast(Context context, String message) {
        if (Validation.isNull(context))
            return;
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context, @StringRes int stringResourceId,
                                 @StringRes int... stringArray) {
        if (Validation.isNull(context))
            return;

        StringBuilder stringBuilder = new StringBuilder();
        for (int i : stringArray)
            stringBuilder.append(getStringResource(context, i));
        showToast(context, String.format(Locale.getDefault(),
                getStringResource(context, stringResourceId), stringBuilder.toString()));
    }

    public static void viewGone(View view) {
        view.setVisibility(View.GONE);
    }

    public static void viewInvisible(View view) {
        view.setVisibility(View.INVISIBLE);
    }

    public static void viewInvisible(ViewGroup view) {
        view.setVisibility(View.INVISIBLE);
    }

    public static void viewGone(ViewGroup view) {
        view.setVisibility(View.GONE);
    }

    public static void viewGone(ViewParent view) {
        viewGone((View) view);
    }

    public static void viewShow(View view) {
        view.setVisibility(View.VISIBLE);
    }

    public static void viewShow(ViewGroup view) {
        view.setVisibility(View.VISIBLE);
    }

    public static void viewShow(ViewParent view) {
        viewShow((View) view);
    }


    public static void viewInvisible(ViewParent view) {
        viewInvisible((View) view);
    }

    public static void viewInVisiblityToggle(View view, boolean toggleView) {
        if (toggleView)
            view.setVisibility(View.VISIBLE);
        else
            view.setVisibility(View.INVISIBLE);
    }

    public static void viewVisiblityToggle(View view, boolean toggleView) {
        if (toggleView)
            view.setVisibility(View.VISIBLE);
        else
            view.setVisibility(View.GONE);
    }

    public static void viewVisiblityToggle(View view) {
        view.setVisibility(View.VISIBLE == view.getVisibility() ? View.GONE : View.VISIBLE);
    }

    public static int getVisibility(View view) {
        return view.getVisibility();
    }

    /******************************** Return String resource  ***************************************/
    public static String getStringResource(Object object, @StringRes int stringResourceId) {
        Activity activity = getActivity(object);
        return activity.getString(stringResourceId);
    }

    public static String[] getStringResourceArray(Object object, @ArrayRes int stringResourceId) {
        Activity activity = getActivity(object);
        return activity.getResources().getStringArray(stringResourceId);
    }

    public static List<String> getStringResourceList(Object object, @ArrayRes int stringResourceId) {
        return Arrays.asList(getStringResourceArray(object, stringResourceId));
    }

    public static String getStringResource(Object object, @StringRes int stringResourceId,
                                           String stringArray) {
        Context activity = getActivity(object);
        return String.format(Locale.getDefault(), getStringResource(activity, stringResourceId), stringArray);
    }

    public static String getStringResource(Object object, @StringRes int stringResourceId,
                                           String... stringArray) {
        Context activity = getActivity(object);
        return String.format(Locale.getDefault(), getStringResource(activity, stringResourceId), stringArray);
    }

    public static String getStringResource(Object object, @StringRes int stringResourceId, String tag,
                                           int intArray) {
        Context activity = getActivity(object);
        return String.format(Locale.getDefault(), getStringResource(activity, stringResourceId), tag, intArray);
    }

    public static String getStringResource(Object object, @StringRes int stringResourceId, String tag,
                                           int intMin, int intMax) {
        Context activity = getActivity(object);
        return String.format(Locale.getDefault(), getStringResource(activity, stringResourceId), tag, intMin, intMax);
    }

    /*******************************
     * Return Color resource
     ***************************************/
    public static ColorDrawable getColorDrawable(Object object, @ColorRes int colorResourceId) {
        return new ColorDrawable(getColorResource(object, colorResourceId));
    }

    public static int getColorResource(Object object, @ColorRes int colorResourceId) {
        Activity activity = getActivity(object);
        return ContextCompat.getColor(activity, colorResourceId);
    }

    public static ColorStateList getColorStateListResource(Object object, @ColorRes int colorResourceId) {
        Activity activity = getActivity(object);
        return ContextCompat.getColorStateList(activity, colorResourceId);
    }

    public static Drawable getDrawableResource(Object object, @DrawableRes int drawableResourceId) {
        Activity activity = getActivity(object);
        return ContextCompat.getDrawable(activity, drawableResourceId);
    }

    public static Drawable getDrawableResourceFilter(Object object, @DrawableRes int drawableResourceId, @ColorRes int colorFilter) {
        Drawable drawable = getDrawableResource(object, drawableResourceId).mutate();
        drawable.setColorFilter(getColorResource(object, colorFilter), PorterDuff.Mode.SRC_ATOP);
        return drawable;
    }


    public static TypedArray getDrawableArray(Object object, @ArrayRes int drawableResourceId) {
        Activity activity = getActivity(object);
        return activity.getResources().obtainTypedArray(drawableResourceId);
    }


    /*******************************
     * Get Dimen
     ***************************************/
    public static int getDimenResource(Object object, @DimenRes int dimenResourceId) {
        Activity activity = getActivity(object);
        return (int) activity.getResources().getDimension(dimenResourceId);
    }

    public static int getIntResource(Object object, @IntegerRes int dimenResourceId) {
        Activity activity = getActivity(object);
        return (int) activity.getResources().getInteger(dimenResourceId);
    }

    public static void enableDisableView(View view, boolean isEnable) {
        view.setEnabled(isEnable);
    }

    /* Permisson Granted Check*/
    public static boolean checkAllPermisssionGranterd(@NonNull String[] permissions,
                                                      @NonNull int[] grantResults) {
        boolean permissionResult = true;
        for (int i = 0; i < permissions.length; i++)
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED)
                permissionResult = false;
        return permissionResult;
    }

    public static float pxToDp(int pixel, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return pixel / density;
    }

    public static float dpToPixel(int someDpValue, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return someDpValue * density;
    }
}