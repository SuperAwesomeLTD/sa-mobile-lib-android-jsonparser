package tv.superawesome.lib.sajsonparser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gabriel.coman on 12/05/16.
 */
public interface JSONSerializable <T>{

    /**
     * Read from a JSON
     * @param json a valid JSON string
     * @throws JSONException
     */
    void readFromJson (JSONObject json);

    /**
     * Write to Json
     * @return a valid Json String
     */
    JSONObject writeToJson ();

    /**
     * Returns if a model is valid or not
     * @return true or false
     */
    boolean isValid ();

}
