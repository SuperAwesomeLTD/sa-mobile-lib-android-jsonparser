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
public class SACompany extends SABaseObject {
    public String name;
    public List<SAEmployee> employees;

    public SACompany (String name, List<SAEmployee> employees) {
        this.name = name;
        this.employees = employees;
    }

    public SACompany (JSONObject jsonObject) {
        super(jsonObject);
    }

    @Override
    public void readFromJson(JSONObject json) {
        name = SAJsonParser.getString(json, "name");
        employees = SAJsonParser.getListFromJsonArray(json, "employees", new SAJsonToList<SAEmployee, JSONObject>() {
            @Override
            public SAEmployee traverseItem(JSONObject param) {
                return new SAEmployee(param);
            }
        });
    }

    @Override
    public JSONObject writeToJson() {
        return SAJsonParser.newObject(new Object[] {
                "name", name,
                "employees", SAJsonParser.getJsonArrayFromList(employees, new SAListToJson<JSONObject, SAEmployee>() {
                                @Override
                                public JSONObject traverseItem(SAEmployee param) {
                                    return param.writeToJson();
                                }
                            })
        });
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
