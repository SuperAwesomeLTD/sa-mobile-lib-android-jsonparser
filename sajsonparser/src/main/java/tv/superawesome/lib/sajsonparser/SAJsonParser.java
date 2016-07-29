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
        if (jsonObject == null || object == null || key == null) {
            return;
        } else {
            try {
                jsonObject.put(key, object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Create a JSON object more elegantly
     * @param args an array of key-value pairs given as
     *             new Object[] { "key1", val1, "key2", val2 }
     * @return a valid JSONObject (or an empty one)
     */
    public static JSONObject create (Object[] args) {
        JSONObject jsonObject = new JSONObject();
        if (args.length % 2 != 0) return jsonObject;

        for (int i = 0; i < args.length; i+= 2) {
            Object key = args[i];
            Object val = args[i+1];
            if (key instanceof  String) {
                put(jsonObject, (String) key, val);
            }
        }

        return jsonObject;
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
            } else return null;
        }
    }

    /**
     * Standard get w/ a default value
     * @param jsonObject target json object
     * @param key the key
     * @return the return type -
     */
    public static Object get(@Nullable JSONObject jsonObject, @Nullable String key, @Nullable Object def) {
        if (jsonObject == null) {
            return def;
        }
        else {
            if (!jsonObject.isNull(key)) {
                try {
                    return jsonObject.get(key);
                } catch (JSONException e) {
                    return def;
                }
            } else return def;
        }
    }

    /**
     * Get a specific string
     * @param jsonObject target json object
     * @param key the key
     * @return a string value from the JSON
     */
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
            } else return null;
        }
    }

    /**
     * Get a specific string value w/ a default value
     * @param jsonObject target json object
     * @param key the key
     * @param def the default string to return
     * @return a string value from the JSON
     */
    public static String getString(@Nullable JSONObject jsonObject, @Nullable String key, @Nullable String def) {
        if (jsonObject == null) {
            return def;
        }
        else {
            if (!jsonObject.isNull(key)) {
                try {
                    return jsonObject.getString(key);
                } catch (JSONException e) {
                    return def;
                }
            } else return def;
        }
    }

    /**
     * Get a specific boolean value
     * @param jsonObject target json object
     * @param key the key
     * @return a boolean value from the JSON
     */
    public static boolean getBoolean(@Nullable JSONObject jsonObject, @Nullable String key) {
        if (jsonObject == null) {
            return false;
        } else {
            if (!jsonObject.isNull(key)) {
                try {
                    return jsonObject.getBoolean(key);
                } catch (JSONException e) {
                    return false;
                }
            } else return false;
        }
    }

    /**
     * * Get a specific boolean value w/ a default value
     * @param jsonObject target json object
     * @param key the key
     * @param def the default boolean to return
     * @return a boolean value from the JSON
     */
    public static boolean getBoolean(@Nullable JSONObject jsonObject, @Nullable String key, boolean def) {
        if (jsonObject == null) {
            return def;
        } else {
            if (!jsonObject.isNull(key)) {
                try {
                    return jsonObject.getBoolean(key);
                } catch (JSONException e) {
                    return def;
                }
            } else return def;
        }
    }

    /**
     * Get a specific integer value
     * @param jsonObject target json object
     * @param key the key
     * @return an integer value from the JSON
     */
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
            } else return 0;
        }
    }

    /**
     * Get a specific integer value w/ a default value
     * @param jsonObject target json object
     * @param key the key
     * @param def the default integer to return
     * @return a integer value from the JSON
     */
    public static int getInt(@Nullable JSONObject jsonObject, @Nullable String key, int def) {
        if (jsonObject == null) {
            return def;
        }
        else {
            if (!jsonObject.isNull(key)) {
                try {
                    return jsonObject.getInt(key);
                } catch (JSONException e) {
                    return def;
                }
            } else return def;
        }
    }

    /**
     * Get a specific double value
     * @param jsonObject target json object
     * @param key the key
     * @return a double value from the JSON
     */
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
            } else return 0;
        }
    }

    /**
     * Get a specific double value w/ a default value
     * @param jsonObject target json object
     * @param key the key
     * @param def the default double to return
     * @return a double value from the JSON
     */
    public static double getDouble(@Nullable JSONObject jsonObject, @Nullable String key, double def) {
        if (jsonObject == null) {
            return def;
        } else {
            if (!jsonObject.isNull(key)) {
                try {
                    return jsonObject.getDouble(key);
                } catch (JSONException e) {
                    return def;
                }
            } else return def;
        }
    }

    /**
     * Get a specific long value w/ a default value
     * @param jsonObject target json object
     * @param key the key
     * @return a long value from the JSON
     */
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
            } else return 0;
        }
    }

    /**
     * Get a specific long value w/ a default value
     * @param jsonObject target json object
     * @param key the key
     * @param def the default long to return
     * @return a long value from the JSON
     */
    public static long getLong(@Nullable JSONObject jsonObject, @Nullable String key, long def) {
        if (jsonObject == null) {
            return def;
        } else {
            if (!jsonObject.isNull(key)) {
                try {
                    return jsonObject.getLong(key);
                } catch (JSONException e) {
                    return def;
                }
            } else return def;
        }
    }

    /**
     * Get a specific json array value w/ a default value
     * @param jsonObject target json object
     * @param key the key
     * @return a json array value from the JSON
     */
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
            } else return null;
        }
    }

    /**
     * Get a specific json array value w/ a default value
     * @param jsonObject target json object
     * @param key the key
     * @param def the default json array to return
     * @return a json array value from the JSON
     */
    @Nullable
    public static JSONArray getJsonArray(@Nullable JSONObject jsonObject, @Nullable String key, @Nullable JSONArray def) {
        if (jsonObject == null) {
            return def;
        } else {
            if (!jsonObject.isNull(key)) {
                try {
                    return jsonObject.getJSONArray(key);
                } catch (JSONException e) {
                    return def;
                }
            } else return def;
        }
    }

    /**
     * Get a specific json object value w/ a default value
     * @param jsonObject target json object
     * @param key the key
     * @return a json object value from the JSON
     */
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
            } else return null;
        }
    }

    /**
     * Get a specific json object value w/ a default value
     * @param jsonObject target json object
     * @param key the key
     * @param def the default json object to return
     * @return a json object value from the JSON
     */
    @Nullable
    public static JSONObject getJsonObject(@Nullable JSONObject jsonObject, @Nullable String key, @Nullable JSONObject def) {
        if (jsonObject == null) {
            return def;
        }
        else  {
            if (!jsonObject.isNull(key)) {
                try {
                    return jsonObject.getJSONObject(key);
                } catch (JSONException e) {
                    return def;
                }
            } else return def;
        }
    }
}
