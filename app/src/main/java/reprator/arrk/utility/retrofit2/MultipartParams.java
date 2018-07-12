package reprator.arrk.utility.retrofit2;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import reprator.arrk.utility.PlainJavaFunction;
import reprator.arrk.utility.Validation;


public class MultipartParams {
    private Map<String, RequestBody>        map = new HashMap<>();

    private MultipartParams(Builder builder) {
        this.map = builder.map;
    }

    public Map<String, RequestBody> getMap() {
        return map;
    }


    public static class Builder {
        private final String mFileConstant = "\"; filename=\"";
        HashMap<String, RequestBody> map = new HashMap<>();

        public Builder() {
        }

        public Builder add(String key, Object value) {
            if (Validation.isNull(value))
                return this;

            map.put(key, RetrofitUtils.getRequestBodyFromString(String.valueOf(value)));
            return this;
        }

        //for single file
        public Builder addFile(String key, File mFile) {
            addCFile(key, mFile);
            return this;
        }

        public Builder addFile(String key, String string) {
            addCFile(key, string);
            return this;
        }

        // for multiple file with same key
        public Builder addArrayOfFiles(String key, List<File> mFileArrayList) {
            addCArrayFiles(key,mFileArrayList);
            return this;
        }

        public Builder addArrayOfFiles(String key, String[] mFileArrayList) {
            addCArrayFiles(key,mFileArrayList);
            return this;
        }

        public Builder addArrayOfFiles(String key, File[] mFileArrayList) {
            addCArrayFiles(key,mFileArrayList);
            return this;
        }

        public Builder addStringArrayOfFiles(String key, List<String> mFileArrayList) {
            addCStringArrayFiles(key,mFileArrayList);
            return this;
        }

        private void addCArrayFiles(String key, String[] mFileArrayList) {
            if (Validation.isNull(mFileArrayList))
                return;

            addCStringArrayFiles(key, Arrays.asList(mFileArrayList));
        }

        private void addCArrayFiles(String key, File[] mFileArrayList) {
            if (Validation.isNull(mFileArrayList))
                return;

            addCArrayFiles(key, Arrays.asList(mFileArrayList));
        }

        private void addCArrayFiles(String key, List<File> mFileArrayList) {
            if (Validation.isEmptyorNull(mFileArrayList))
                return;

            for (int i = 0; i < mFileArrayList.size(); i++)
                addCFile(key, mFileArrayList.get(i));
        }

        private void addCStringArrayFiles(String key, List<String> mFileArrayList) {
            if (Validation.isEmptyorNull(mFileArrayList))
                return;

            for (int i = 0; i < mFileArrayList.size(); i++)
                addCFile(key, mFileArrayList.get(i));
        }

        private void addCFile(String key, File mFile) {
            if (Validation.isNull(mFile))
                return;
            map.put(key + mFileConstant + mFile.getName(),
                    RequestBody.create(MediaType.parse(PlainJavaFunction.getMimeType(mFile)), mFile));
        }

        private void addCFile(String key, String mFile) {
            if (Validation.isNull(mFile))
                return;
            addCFile(key, new File(mFile));
        }

        public MultipartParams build() {
            return new MultipartParams(this);
        }
    }
}

