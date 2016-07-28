package tv.superawesome.lib.sajsonparser;

import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gabriel.coman on 28/07/16.
 */
public class SAJsonParser {

    /**
     * Function that safely puts a value in a Json object
     * @param jsonObject the target Json object
     * @param key the key under which to put the target object
     * @param object the actual target object
     */
    public static void put(JSONObject jsonObject, String key, Object object) {
        try {
            jsonObject.put(key, object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Standard get functions
     * @param jsonObject target json object
     * @param key the key
     * @return the return type -
     */
    @Nullable
    public static Object get(@Nullable JSONObject jsonObject, @Nullable String key) {
        if (jsonObject == null) {
            return null;
        }
        else {
            if (!jsonObject.isNull(key)) {
                try {
                    return jsonObject.get(key);
                } catch (JSONException e) {
                    return null;
                }
            }
        }
        return null;
    }

    @Nullable
    public static String getString(@Nullable JSONObject jsonObject, @Nullable String key) {
        if (jsonObject == null) {
            return null;
        }
        else {
            if (!jsonObject.isNull(key)) {
                try {
                    return jsonObject.getString(key);
                } catch (JSONException e) {
                    return null;
                }
            }
        } return null;
    }

    public static boolean getBoolean(@Nullable JSONObject jsonObject, @Nullable String key) {
        if (jsonObject == null) {
            return false;
        }
        else if (!jsonObject.isNull(key)) {
            try {
                return jsonObject.getBoolean(key);
            } catch (JSONException e) {
                return false;
            }
        } return false;
    }

    public static int getInt(@Nullable JSONObject jsonObject, @Nullable String key) {
        if (jsonObject == null) {
            return 0;
        }
        else {
            if (!jsonObject.isNull(key)) {
                try {
                    return jsonObject.getInt(key);
                } catch (JSONException e) {
                    return 0;
                }
            }
        } return 0;
    }

    public static double getDouble(@Nullable JSONObject jsonObject, @Nullable String key) {
        if (jsonObject == null) {
            return 0;
        } else {
            if (!jsonObject.isNull(key)) {
                try {
                    return jsonObject.getDouble(key);
                } catch (JSONException e) {
                    return 0;
                }
            }
        } return 0;
    }

    public static long getLong(@Nullable JSONObject jsonObject, @Nullable String key) {
        if (jsonObject == null) {
            return 0;
        } else {
            if (!jsonObject.isNull(key)) {
                try {
                    return jsonObject.getLong(key);
                } catch (JSONException e) {
                    return 0;
                }
            }
        } return 0;
    }

    @Nullable
    public static JSONArray getJsonArray(@Nullable JSONObject jsonObject, @Nullable String key) {
        if (jsonObject == null) {
            return null;
        } else {
            if (!jsonObject.isNull(key)) {
                try {
                    return jsonObject.getJSONArray(key);
                } catch (JSONException e) {
                    return null;
                }
            }
        } return null;
    }

    @Nullable
    public static JSONObject getJsonObject(@Nullable JSONObject jsonObject, @Nullable String key) {
        if (jsonObject == null) {
            return null;
        }
        else  {
            if (!jsonObject.isNull(key)) {
                try {
                    return jsonObject.getJSONObject(key);
                } catch (JSONException e) {
                    return null;
                }
            } return null;
        }
    }

}
