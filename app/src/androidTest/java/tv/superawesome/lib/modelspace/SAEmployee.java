package tv.superawesome.lib.modelspace;

import org.json.JSONObject;

import java.util.List;

import tv.superawesome.lib.sajsonparser.SABaseObject;
import tv.superawesome.lib.sajsonparser.SAJsonParser;
import tv.superawesome.lib.sajsonparser.SAJsonToList;
import tv.superawesome.lib.sajsonparser.SAListToJson;

/**
 * Created by gabriel.coman on 18/10/16.
 */
public class SAEmployee extends SABaseObject {
    public String name;
    public int age;
    public boolean isActive;
    public SAPosition position;
    public List<SAPosition> previous;

    public SAEmployee (String name, int age, boolean isActive, SAPosition position, List<SAPosition> previous) {
        this.name = name;
        this.age = age;
        this.isActive = isActive;
        this.position = position;
        this.previous = previous;
    }

    public SAEmployee (JSONObject jsonObject) {
        super(jsonObject);
    }

    @Override
    public void readFromJson(JSONObject json) {
        name = SAJsonParser.getString(json, "name");
        age = SAJsonParser.getInt(json, "age");
        isActive = SAJsonParser.getBoolean(json, "isActive");
        position = new SAPosition(SAJsonParser.getJsonObject(json, "position"));
        previous = SAJsonParser.getListFromJsonArray(json, "previous", new SAJsonToList<SAPosition, JSONObject>() {
            @Override
            public SAPosition traverseItem(JSONObject param) {
                return new SAPosition(param);
            }
        });
    }

    @Override
    public JSONObject writeToJson() {
        return SAJsonParser.newObject(new Object[] {
                "name", name,
                "age", age,
                "isActive", isActive,
                "position", position,
                "previous", SAJsonParser.getJsonArrayFromList(previous, new SAListToJson<JSONObject, SAPosition>() {
                            @Override
                                public JSONObject traverseItem(SAPosition param) {
                                    return param.writeToJson();
                                }
                            })
        });
    }

    @Override
    public boolean isValid() {
        return name != null;
    }
}
