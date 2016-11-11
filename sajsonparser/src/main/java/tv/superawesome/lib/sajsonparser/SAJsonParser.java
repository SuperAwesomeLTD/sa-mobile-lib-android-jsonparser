package tv.superawesome.lib.sajsonparser;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

        // checks
        if (jsonObject == null) return;
        if (key == null) return;

        // if current object is null the just add a standard NULL to the JSON
        if (object == null) {
            try {
                jsonObject.put(key, JSONObject.NULL);
            } catch (JSONException ignored) {
            }
        }
        // if object isn't NULL and is a subclass of SABaseObject
        else if (object != null && object instanceof SABaseObject) {
            try {
                jsonObject.put(key, ((SABaseObject) object).writeToJson());
            } catch (JSONException ignored) {
            }
        }
        // if it's any other object just add it
        else {
            try {
                jsonObject.put(key, object);
            } catch (JSONException ignored) {
            }
        }
    }

    /**
     * Create a JSON object more elegantly
     * @param args an array of key-value pairs given as
     *             new Object[] { "key1", val1, "key2", val2 }
     * @return a valid JSONObject (or an empty one)
     */
    public static JSONObject newObject (Object[] args) {
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
     * Init a Json object from a string, w/o the hassle of try/catch
     * @param json the JSON string
     * @return a valid JSON object
     */
    public static JSONObject newObject (String json) {
        if (json == null) {
            return new JSONObject();
        } else {
            try {
                return new JSONObject(json);
            } catch (JSONException e) {
                e.printStackTrace();
                return new JSONObject();
            }
        }
    }

    /**
     * Init a JSON array from a string, w/o the hassle of try/catch
     * @param json the JSON string
     * @return a valid JSON array
     */
    public static JSONArray newArray (String json) {
        if (json == null) {
            return new JSONArray();
        } else {
            try {
                return new JSONArray(json);
            } catch (JSONException e) {
                e.printStackTrace();
                return new JSONArray();
            }
        }
    }

    /**
     * Create a new Json array
     * @param args an array of arguments
     * @return a json array object
     */
    public static JSONArray newArray (Object[] args) {
        JSONArray jsonArray = new JSONArray();

        for (Object arg : args) {
            jsonArray.put(arg);
        }

        return jsonArray;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Safe get methods
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Standard get functions
     * @param jsonObject target json object
     * @param key the key
     * @return the return type -
     */
    public static Object get( JSONObject jsonObject,  String key) {
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
    public static Object get( JSONObject jsonObject, String key, Object def) {
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
    public static String getString(JSONObject jsonObject, String key) {
        Object object = get(jsonObject, key);
        if (object != null && object instanceof String) {
            return (String)object;
        } else {
            return null;
        }
    }

    /**
     * Get a specific string value w/ a default value
     * @param jsonObject target json object
     * @param key the key
     * @param def the default string to return
     * @return a string value from the JSON
     */
    public static String getString(JSONObject jsonObject, String key, String def) {
        Object object = get(jsonObject, key);
        if (object != null && object instanceof String) {
            return (String)object;
        } else {
            return def;
        }
    }

    /**
     * Get a specific boolean value
     * @param jsonObject target json object
     * @param key the key
     * @return a boolean value from the JSON
     */
    public static boolean getBoolean(JSONObject jsonObject, String key) {
        Object object = get(jsonObject, key);
        if (object != null && object instanceof Boolean) {
            return (Boolean) object;
        } else {
            return false;
        }
    }

    /**
     * * Get a specific boolean value w/ a default value
     * @param jsonObject target json object
     * @param key the key
     * @param def the default boolean to return
     * @return a boolean value from the JSON
     */
    public static boolean getBoolean(JSONObject jsonObject, String key, boolean def) {
        Object object = get(jsonObject, key);
        if (object != null && object instanceof Boolean) {
            return (Boolean) object;
        } else {
            return def;
        }
    }

    /**
     * Get a specific integer value
     * @param jsonObject target json object
     * @param key the key
     * @return an integer value from the JSON
     */
    public static int getInt(JSONObject jsonObject, String key) {
        Object object = get(jsonObject, key);
        if (object != null && object instanceof Integer) {
            return (Integer) object;
        } else {
            return 0;
        }
    }

    /**
     * Get a specific integer value w/ a default value
     * @param jsonObject target json object
     * @param key the key
     * @param def the default integer to return
     * @return a integer value from the JSON
     */
    public static int getInt(JSONObject jsonObject, String key, int def) {
        Object object = get(jsonObject, key);
        if (object != null && object instanceof Integer) {
            return (Integer) object;
        } else {
            return def;
        }
    }

    /**
     * Get a specific double value
     * @param jsonObject target json object
     * @param key the key
     * @return a double value from the JSON
     */
    public static double getDouble(JSONObject jsonObject, String key) {
        Object object = get(jsonObject, key);
        if (object != null && object instanceof Double) {
            return (Double) object;
        } else {
            return 0;
        }
    }

    /**
     * Get a specific double value w/ a default value
     * @param jsonObject target json object
     * @param key the key
     * @param def the default double to return
     * @return a double value from the JSON
     */
    public static double getDouble(JSONObject jsonObject, String key, double def) {
        Object object = get(jsonObject, key);
        if (object != null && object instanceof Double) {
            return (Double) object;
        } else {
            return def;
        }
    }

    /**
     * Get a specific long value w/ a default value
     * @param jsonObject target json object
     * @param key the key
     * @return a long value from the JSON
     */
    public static long getLong(JSONObject jsonObject, String key) {
        Object object = get(jsonObject, key);
        if (object != null && object instanceof Integer) {
            return ((Integer) object).longValue();
        } else {
            return 0L;
        }
    }

    /**
     * Get a specific long value w/ a default value
     * @param jsonObject target json object
     * @param key the key
     * @param def the default long to return
     * @return a long value from the JSON
     */
    public static long getLong(JSONObject jsonObject, String key, long def) {
        Object object = get(jsonObject, key);
        if (object != null && object instanceof Integer) {
            return ((Integer) object).longValue();
        } else {
            return def;
        }
    }

    /**
     * Get a specific json object value w/ a default value
     * @param jsonObject target json object
     * @param key the key
     * @return a json object value from the JSON
     */
    public static JSONObject getJsonObject(JSONObject jsonObject, String key) {
        Object object = get(jsonObject, key);
        if (object != null && object instanceof JSONObject) {
            return (JSONObject) object;
        } else {
            return new JSONObject();
        }
    }

    /**
     * Get a specific json object value w/ a default value
     * @param jsonObject target json object
     * @param key the key
     * @param def the default json object to return
     * @return a json object value from the JSON
     */
    public static JSONObject getJsonObject(JSONObject jsonObject, String key, JSONObject def) {
        Object object = get(jsonObject, key);
        if (object != null && object instanceof JSONObject) {
            return (JSONObject) object;
        } else {
            return def;
        }
    }

    /**
     * Get a specific json array value w/ a default value
     * @param jsonObject target json object
     * @param key the key
     * @return a json array value from the JSON
     */
    public static JSONArray getJsonArray(JSONObject jsonObject, String key) {
        Object object = get(jsonObject, key);
        if (object != null && object instanceof JSONArray) {
            return (JSONArray) object;
        } else {
            return new JSONArray();
        }
    }

    /**
     * Get a specific json array value w/ a default value
     * @param jsonObject target json object
     * @param key the key
     * @param def the default json array to return
     * @return a json array value from the JSON
     */
    public static JSONArray getJsonArray(JSONObject jsonObject, String key, JSONArray def) {
        Object object = get(jsonObject, key);
        if (object != null && object instanceof JSONArray) {
            return (JSONArray) object;
        } else {
            return def;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // More specific array-to-list-and-back methods
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Transform a json array into a list
     * @param jsonArray the json array
     * @param listener a listener
     * @return an ArrayList object
     */
    public static <A, B> List<A> getListFromJsonArray(JSONArray jsonArray, SAJsonToList<A, B> listener) {
        List<A> result = new ArrayList<>();

        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    if (listener != null) {
                        B jsonItem = (B) jsonArray.get(i);
                        if (jsonItem != null) {
                            A item = listener.traverseItem(jsonItem);
                            if (item != null) {
                                result.add(item);
                            } else throw  new JSONException("");
                        } else  throw  new JSONException("");
                    } else throw new JSONException("");
                } catch (JSONException | ClassCastException ignored) {}
            }
        }

        return result;
    }
    /**
     * Function that traverses an array
     * @param jsonObject target json object
     * @param key the key
     * @param listener a listener to traverse item
     */
    public static <A, B> List<A> getListFromJsonArray(JSONObject jsonObject, String key, SAJsonToList<A, B> listener) {
        JSONArray jsonArray = getJsonArray(jsonObject, key, new JSONArray());
        return getListFromJsonArray(jsonArray, listener);
    }

    /**
     * Transform a normal ArrayList to a JSONArray
     * @param arrayList the array list, on generic <A>
     * @param listener the listner
     * @param <A> the generic param
     * @return a JSON array
     */
    public static <A, B> JSONArray getJsonArrayFromList (List<B> arrayList, SAListToJson<A, B> listener) {
        JSONArray jsonArray = new JSONArray();

        if (listener != null) {
            for (B object : arrayList) {
                A item = listener.traverseItem(object);
                if (item != null) {
                    jsonArray.put(item);
                }
            }
        }

        return jsonArray;
    }
}
