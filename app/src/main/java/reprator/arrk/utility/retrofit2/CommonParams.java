package reprator.arrk.utility.retrofit2;

import java.util.HashMap;

public class CommonParams {
    private HashMap<String, String>         map     = new HashMap<>();

    private CommonParams(Builder builder) {
        this.map = builder.map;

    }

    public HashMap<String, String> getMap() {
        return map;
    }


    public static class Builder {
        HashMap<String, String> map = new HashMap<>();

        public Builder() {
        }

        public Builder add(String key, Object value) {
            if (value == null)
                return this;
            map.put(key, String.valueOf(value));
            return this;
        }

        public CommonParams build() {
            return new CommonParams(this);
        }
    }
}


