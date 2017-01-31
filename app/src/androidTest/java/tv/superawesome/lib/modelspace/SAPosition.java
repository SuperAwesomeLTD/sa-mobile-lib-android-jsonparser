package tv.superawesome.lib.modelspace;

import org.json.JSONObject;

import tv.superawesome.lib.sajsonparser.SABaseObject;
import tv.superawesome.lib.sajsonparser.SAJsonParser;

/**
 * Created by gabriel.coman on 18/10/16.
 */
public class SAPosition extends SABaseObject {
    public String name;
    public int salary;

    public SAPosition () {

    }

    public SAPosition (String name, int salary) {
        super();
        this.name = name;
        this.salary = salary;
    }

    public SAPosition(JSONObject jsonObject) {
        super(jsonObject);
    }

    @Override
    public void readFromJson(JSONObject json) {
        name = SAJsonParser.getString(json, "name");
        salary = SAJsonParser.getInt(json, "salary");
    }

    @Override
    public JSONObject writeToJson() {
        return SAJsonParser.newObject(new Object[] {
                "name", name,
                "salary", salary
        });
    }

    @Override
    public boolean isValid() {
        return name != null;
    }
}