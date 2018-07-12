package reprator.arrk.utility.retrofit2;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import reprator.arrk.utility.PlainJavaFunction;
import reprator.arrk.utility.Validation;

public class RetrofitUtils {

    /**
     * @param value
     * @return
     */
    public static RequestBody getRequestBodyFromString(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }


    /**
     * @param key
     * @param file
     * @return
     */
    public static MultipartBody.Part getPartBodyFromFile(String key, File file) {
        if (Validation.isNull(file))
            return null;
        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(MediaType.parse(PlainJavaFunction.getMimeType(file)), file);
        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(key, file.getName(), requestFile);
    }

/*    public static String getMimeType(File file) {
        String mimeType = "image/png";
        try {
            Uri selectedUri = Uri.fromFile(file);
            String fileExtension
                    = MimeTypeMap.getFileExtensionFromUrl(selectedUri.toString());
            mimeType
                    = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension);
        } catch (Exception e) {
            LogUtil.e("mime type exception ", e.toString());
        }
        return mimeType;
    }*/
}
