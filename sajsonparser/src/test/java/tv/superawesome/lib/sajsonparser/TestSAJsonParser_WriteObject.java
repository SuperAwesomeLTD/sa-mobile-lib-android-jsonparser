package tv.superawesome.lib.sajsonparser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.Arrays;

import tv.superawesome.lib.sajsonparser.mocks.SACompany;
import tv.superawesome.lib.sajsonparser.mocks.SAEmployee;
import tv.superawesome.lib.sajsonparser.mocks.SAPosition;

/**
 * Created by gabriel.coman on 17/10/16.
 */
public class TestSAJsonParser_WriteObject {

    @Test
    public void test_SAJsonparser_WriteObject_WithSimpleObject () {
        // given
        SAPosition position = new SAPosition("CEO", 100000);

        // when
        JSONObject expected = new JSONObject();
        try {
            expected.
                    put("name", "CEO").
                    put("salary", 100000);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // then
        JSONObject result = position.writeToJson();

        try {
            JSONAssert.assertEquals(expected, result, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_SAJsonparser_WriteObject_WithNestedObject () throws Exception {
        // given
        SAPosition given1 = new SAPosition("Junior Engineer", 28000);
        SAPosition given2 = new SAPosition("Engineer", 35000);
        SAEmployee given = new SAEmployee("Jim", 23, true, given2, Arrays.asList(given1));

        // when
        JSONObject ex1 = new JSONObject();
        ex1.put("name", "Junior Engineer").put("salary", 28000);

        JSONObject ex2 = new JSONObject();
        ex2.put("name", "Engineer").put("salary", 35000);

        JSONArray arry = new JSONArray();
        arry.put(ex1);
        JSONObject expected = new JSONObject();
        expected.
                put("name", "Jim").
                put("age", 23).
                put("isActive", true).
                put("position", ex2).
                put("previous", arry);

        // then
        JSONObject result = given.writeToJson();
        JSONAssert.assertEquals(expected, result, false);
    }

    @Test
    public void test_SAJsonparser_WriteObject_WithVeryComplexObject () throws Exception {
        // given
        SAPosition given00 = new SAPosition("Intern", 0);
        SAPosition given11 = new SAPosition("Junior Engineer", 28000);
        SAPosition given12 = new SAPosition("Engineer", 35000);

        SAEmployee given1 = new SAEmployee("John", 23, true, given11, Arrays.asList(given00));
        SAEmployee given2 = new SAEmployee("Danna", 18, true, given12, Arrays.asList(given00, given11));

        SACompany given = new SACompany("John Smith Ltd", Arrays.asList(given1, given2));

        // when
        JSONObject ex00 = new JSONObject();
        ex00.put("name", "Intern").put("salary", 0);
        JSONObject ex11 = new JSONObject();
        ex11.put("name", "Junior Engineer").put("salary", 28000);
        JSONObject ex12 = new JSONObject();
        ex12.put("name", "Engineer").put("salary", 35000);

        JSONArray arr11 = new JSONArray().put(ex00);
        JSONArray arr22 = new JSONArray().put(ex00).put(ex11);

        JSONObject ex1 = new JSONObject();
        ex1.put("name", "John").put("age", 23).put("isActive", true).put("position", ex11).put("previous", arr11);

        JSONObject ex2 = new JSONObject();
        ex2.put("name", "Danna").put("age", 18).put("isActive", true).put("position", ex12).put("previous", arr22);


        JSONArray arr33 = new JSONArray().put(ex1).put(ex2);

        JSONObject expected = new JSONObject();
        expected.put("name", "John Smith Ltd").put("employees", arr33);

        // then
        JSONObject result = given.writeToJson();
        JSONAssert.assertEquals(expected, result, false);
    }

    @Test
    public void test_SAJsonparser_WriteObject_WithNulls () throws Exception {
        // given
        SAPosition position = new SAPosition("Junior Engineer", 28000);
        SAEmployee given = new SAEmployee(null, 32, false, null, Arrays.asList(position));

        // then
        JSONObject pos = new JSONObject();
        pos.put("name", "Junior Engineer").put("salary", 28000);

        JSONArray arr = new JSONArray().put(pos);
        JSONObject expected = new JSONObject();

        expected.put("age", 32).put("isActive", false).put("previous", arr);

        // then
        JSONObject result = given.writeToJson();


        JSONAssert.assertEquals(expected, result, false);
    }
}
