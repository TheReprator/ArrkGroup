package reprator.arrk.utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.Locale;

import timber.log.Timber;


public class Intents {

    /************************Activity Intents*************************/
    public static void removeAllAndStartNewActivity(@NonNull final Class<?> destination,
                                                    @NonNull final Object context) {
        removeAllAndStartNewActivity(destination, context, null);
    }

    public static void removeAllAndStartNewActivity(@NonNull final Class<?> destination,
                                                    @NonNull final Object context,
                                                    final Bundle bundle) {
        Activity activity = ContextFunction.getActivity(context);
        Intent intent = new Intent(activity, destination);
        if (!Validation.isNull(bundle))
            intent.putExtras(bundle);
     /*   If set, and the activity being launched is already running in
        the current task, then instead of launching a
        new instance of that activity, all of the other activities
        on top of it will be closed and this Intent will be delivered
        to the (now on top) old activity as a new Intent.*/
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        /*This flag works similar to “launchMode = singleTop”.*/
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        /*This flag will cause any existing task that would be associated
        with the activity to be cleared before the activity is started.
                The activity becomes the new root of an otherwise empty
        task, and any old activities are finished.*/
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        /*“launchMode = singleTask”*/
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        ActivityCompat.finishAffinity(activity);
    }

    public static void removeAllAndStartNewActivityInASingleStack(@NonNull final Class<?> destination,
                                                                  @NonNull final Object context,
                                                                  final Bundle bundle) {
        Activity activity=ContextFunction.getActivity(context);
        Intent intent = new Intent(activity, destination);
        if (!Validation.isNull(bundle))
            intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        ActivityCompat.finishAffinity(activity);
    }

    public static void nextActivity(@NonNull final Class<?> destination,
                                    @NonNull final Object context) {
        nextActivity(destination,context,null);
    }

    public static void nextNewActivity(@NonNull final Class<?> destination,
                                       @NonNull final Object context) {
        Activity activity=ContextFunction.getActivity(context);
        Intent intent = new Intent(activity, destination);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent,activity);
    }

    public static void nextActivity(@NonNull final Class<?> destination,
                                    @NonNull final Object context, final Bundle bundle) {
        Activity activity=ContextFunction.getActivity(context);
        Intent intent = new Intent(activity, destination);
        if (!Validation.isNull(bundle ))
            intent.putExtras(bundle);
        startActivity(intent,activity);
    }

    private static void startActivity(@NonNull final Intent intent,
                                      @NonNull final Activity context) {
        context.startActivity(intent);
    }

    public static void startActivityForResult(@NonNull final Class<?> destination,
                                              @NonNull final Object context, int code) {
        startActivityForResult(destination,context,code,null);
    }

    public static void startActivityForResult(@NonNull final Class<?> destination,
                                              @NonNull final Object context, int code,
                                              final Bundle bundle) {
        Activity activity=ContextFunction.getActivity(context);
        Intent intent = new Intent(activity, destination);
        if (!Validation.isNull(bundle ))
            intent.putExtras(bundle);
        activity.startActivityForResult(intent, code);
    }

    public static void startActivityForResult(@NonNull final Class<?> destination,
                                              @NonNull final Fragment context, int code) {
        startActivityForResult(destination,context,code,null);
    }

    public static void startActivityForResult(@NonNull final Class<?> destination,
                                              @NonNull final Fragment context, int code,
                                              final Bundle bundle) {
        Context activity=context.getContext();
        Intent intent = new Intent(activity, destination);
        if (!Validation.isNull(bundle ))
            intent.putExtras(bundle);
        context.startActivityForResult(intent, code);
    }

    public static void withoutHistory(@NonNull final Class<?> destination,
                                      @NonNull final Object context) {
        withoutHistory(destination,context,null);
    }

    private static void withoutHistory(@NonNull final Class<?> destination,
                                       @NonNull final Object context, final Bundle bundle) {
        Activity activity=ContextFunction.getActivity(context);
        Intent intent = new Intent(activity, destination);
        if(bundle!=null)
            intent.putExtras(bundle);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        activity.startActivity(intent);
    }

    public static void removeFragment(@NonNull final Object context, @NonNull Fragment fragment) {
        FragmentManager fragmentManager=getFragmentManager(context);

        Fragment myFragment = fragmentManager.
                findFragmentByTag(PlainJavaFunction.getClassName(fragment));
        if (!Validation.isNull(myFragment ))
            fragmentManager.beginTransaction().remove(fragment).commitNow();
    }

    public static void removeFragment(@NonNull final Object context, @IdRes int frameId) {
        FragmentManager fragmentManager=getFragmentManager(context);

        fragmentManager.beginTransaction().
                remove(fragmentManager.findFragmentById(frameId)).commit();
    }

    private static Fragment createFragmentInstance(@NonNull final Class<?> destination) throws InstantiationException {
        try {
            return (Fragment) destination.newInstance();
        } catch (InstantiationException e) {
            throw new InstantiationException("Unable to create Instance");
        } catch (IllegalAccessException e) {
            throw new InstantiationException("Illegal Access");
        }
    }

    public static void replaceFragment(@NonNull final Object context, @IdRes int target,
                                       @NonNull final Class<?> destination){
        replaceFragment(context,target,destination,null);
    }

    public static void replaceFragment(@NonNull final Object context, @IdRes int target,
                                       @NonNull final Class<?> destination, Bundle bundle) {
        replaceFragment(context,target, null,destination,bundle);
    }

    public static void replaceFragment(@NonNull final Object context, @IdRes int target, String tagName,
                                       @NonNull final Class<?> destination) {
        replaceFragment(context,target,tagName,destination,null);
    }

    public static void replaceFragment(@NonNull final Object context, @IdRes int target, String tagName,
                                       @NonNull final Class<?> destination, Bundle bundle) {
        try {
            Fragment fragmentObject = createFragmentInstance(destination);
            replaceFragment(context,target, tagName, fragmentObject,bundle);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static <T extends Fragment> void replaceFragment(@NonNull final Object context,
                                                            @IdRes int target, @NonNull T fragment)
    {
        replaceFragment(context,target,null,fragment,null);
    }
    /****************************Fragment Operations**********************************/
    public static void replaceFragment(@NonNull final Object context,
                                       @IdRes int target, String tagName,
                                       @NonNull Fragment fragment, Bundle bundle) {
        replaceFragmentBackStack(context, target, fragment, bundle, tagName, null, false);
    }


    public static <T extends Fragment> void replaceFragmentBackStack(@NonNull final Object context,
                                                                     @IdRes int target,
                                                                     @NonNull T fragment) {
        replaceFragmentBackStack(context,target,fragment,null,null);
    }

    public static void replaceFragmentBackStack(@NonNull final Object context, @IdRes int target,
                                                @NonNull Fragment fragment, String tagName) {
        replaceFragmentBackStack(context,target,fragment,null, tagName);
    }

    public static void replaceFragmentBackStack(@NonNull final Object context, @IdRes int target,
                                                @NonNull Fragment fragment, Bundle bundle ) {
        replaceFragmentBackStack(context,target,fragment,bundle,null);
    }

    public static void replaceFragmentBackStack(@NonNull final Object context, @IdRes int target,
                                                @NonNull Fragment fragment, Bundle bundle, String tagName) {
        replaceFragmentBackStack(context,target,fragment,bundle,tagName,null,true);
    }

    //PlainJavaFunction.getClassName(fragment)
    public static void replaceFragmentBackStack(@NonNull final Object context, @IdRes int target,
                                                @NonNull Fragment fragment, Bundle bundle,
                                                String tagName, String backStackName, boolean isBackStack) {
        FragmentManager fragmentManager=getFragmentManager(context);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (!Validation.isNull(bundle))
            fragment.setArguments(bundle);

        String tag = Validation.isNull(tagName)?
                PlainJavaFunction.getClassName(fragment):tagName;

        Fragment myFragment = fragmentManager.findFragmentByTag(tag);
        if (!Validation.isNull(myFragment)) {
            if (myFragment.isAdded())
                return;
            else
            if (myFragment.getClass().equals(fragment.getClass()))
                fragmentTransaction.replace(target, fragment, tag).commit();
        }
        else
        {
            fragmentTransaction.replace(target, fragment, tag);
            if(isBackStack) {
                backStackName = Validation.isNull(backStackName)?
                        PlainJavaFunction.getClassName(fragment):backStackName;
                fragmentTransaction.addToBackStack(backStackName);
            }
            fragmentTransaction.commit();
        }
    }

    public static void clearAllFragmentsFromActivity(Object object)
    {
        getFragmentManager(object).popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public static <T extends Fragment> void addFragment(@NonNull final Object context,
                                                        @NonNull T fragment) {
        addFragment(context,fragment, PlainJavaFunction.getClassName(fragment));
    }

    public static void showProgressDialog(DialogFragment currentDialog, String tagName,
                                          Object context) {
        addFragment(context,currentDialog,tagName);
    }

    private static <T extends Fragment>  void addFragment(@NonNull final Object context,
                                                          @NonNull T fragment, String tagName)
    {
        FragmentManager fragmentManager=getFragmentManager(context);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(fragment, tagName);
        transaction.commit();
    }

    public static void hideProgressDialog(String tagName, Object context) {
        if(Validation.isNull(context))
            return;
        FragmentManager fragmentManager=getFragmentManager(context);
        DialogFragment transparentDialog = (DialogFragment)
                fragmentManager.findFragmentByTag(tagName);
        if(!Validation.isNull(transparentDialog))
            return;
        transparentDialog.dismissAllowingStateLoss();
    }

    public static <T extends DialogFragment>  void showDialogFragment(@NonNull final Object context,
                                                                      @NonNull T fragment)
    {
        showDialogFragment(context,fragment,null);
    }

    public static <T extends DialogFragment>  void showDialogFragment
            (@NonNull final Object context, @NonNull T fragment, Bundle bundle)
    {
        FragmentManager fragmentManager=getFragmentManager(context);
        DialogFragment myFragment = (DialogFragment)fragmentManager.findFragmentByTag
                (PlainJavaFunction.getClassName(fragment));
        if(!Validation.isNull(myFragment))
            myFragment.dismiss();
        if(!Validation.isNull(bundle))
            fragment.setArguments(bundle);
        fragment.show(fragmentManager, PlainJavaFunction.getClassName(fragment));
    }

    public static <T extends DialogFragment>  void showDialogSetTargetFragment
            (@NonNull final Fragment context, @NonNull T fragment, int dialogCode)
    {
        showDialogSetTargetFragment(context, fragment, null, dialogCode);
    }
    //https://stackoverflow.com/questions/42704924/can-not-perform-this-action-after-onsaveinstancestate-when-dialog-fragment-is-di
    public static <T extends DialogFragment>  void showDialogSetTargetFragment
    (@NonNull final Fragment context, @NonNull T fragment, Bundle bundle, int dialogCode)
    {
        FragmentManager fragmentManager = context.getFragmentManager();
        DialogFragment myFragment = (DialogFragment)fragmentManager.findFragmentByTag
                (PlainJavaFunction.getClassName(fragment));
        if(!Validation.isNull(myFragment))
            myFragment.dismiss();
        if(!Validation.isNull(bundle))
            fragment.setArguments(bundle);

        /*fragment.setTargetFragment(context, dialogCode);
        fragment.show(fragmentManager, PlainJavaFunction.getClassName(fragment));*/
        FragmentTransaction ft = fragmentManager.beginTransaction();
        //newFragment.show(ft,"progressDialog");
        fragment.setTargetFragment(context, dialogCode);
        //ft.add(fragment, "progressDialog");
        ft.add(fragment, PlainJavaFunction.getClassName(fragment));
        ft.commitAllowingStateLoss();
    }

    public static <T extends DialogFragment>  void hideDialogFragment
            (@NonNull final Object context, @NonNull T fragment)
    {
        FragmentManager fragmentManager=getFragmentManager(context);
        DialogFragment myFragment = (DialogFragment)fragmentManager.
                findFragmentByTag(PlainJavaFunction.getClassName(fragment));
        if(!Validation.isNull(myFragment))
            myFragment.dismiss();
    }

    /**************************Fragment Manager From Activity and ChildFragment Namager
     * from fragment*****************************************************/
    private static FragmentManager getFragmentManager(Object context)
    {
        if(context instanceof FragmentActivity)
            return  ((FragmentActivity)context).getSupportFragmentManager();
        else
            return ((Fragment)context).getChildFragmentManager();
    }

    /********************************Call a Number******************************/
    public static void callNumberWithDialer(final Object object, final String number) {
        try {
            Activity context=ContextFunction.getActivity(object);
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + number));
            intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
            context.startActivity(intent);
        } catch (Exception e) {
            Timber.e("SampleApp Failed to invoke call"+ e);
        }
    }

}
