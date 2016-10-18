package tv.superawesome.lib.sajsonparser;

import org.json.JSONObject;

/**
 * Created by gabriel.coman on 18/10/16.
 */
public class SABaseObject implements JSONSerializable {

    public SABaseObject () {
        // default
    }

    public SABaseObject (JSONObject jsonObject) {
        readFromJson(jsonObject);
    }

    @Override
    public void readFromJson(JSONObject json) {

    }

    @Override
    public JSONObject writeToJson() {
        return null;
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
