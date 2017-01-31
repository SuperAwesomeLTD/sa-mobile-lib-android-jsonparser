package tv.superawesome.lib;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;

import tv.superawesome.lib.sajsonparser.SAJsonParser;

/**
 * Created by gabriel.coman on 17/10/16.
 */
public class SAJsonParser_Dictionary_Deserialization_Tests extends ApplicationTestCase<Application> {
    public SAJsonParser_Dictionary_Deserialization_Tests() {
        super(Application.class);
    }

    @SmallTest
    public void testJSON1 () {
        // given
        String given = "{ \"field\":23 }";

        // expected
        JSONObject expected = new JSONObject();
        try {
            expected.put("field", 23);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // then
        JSONObject result = SAJsonParser.newObject(given);
        try {
            JSONAssert.assertEquals(result, expected, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SmallTest
    public void testJSON2 () {
        // given
        String given = "{ \"field\": 23 , \"name\": \"John\", \"isOK\": true }";

        // expected
        JSONObject expected = new JSONObject();
        try {
            expected.put("field", 23);
            expected.put("name", "John");
            expected.put("isOK", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // then
        JSONObject result = SAJsonParser.newObject(given);
        try {
            JSONAssert.assertEquals(result, expected, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SmallTest
    public void testJSON3 () {
        // given
        String given = "{ \"field\": 23, \"name\": null, \"value\": 3.5, \"isOK\": true }";

        // expected
        JSONObject expected = new JSONObject();
        try {
            expected.put("field", 23);
            expected.put("name", JSONObject.NULL);
            expected.put("value", 3.5);
            expected.put("isOK", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // then
        JSONObject result = SAJsonParser.newObject(given);

        try {
            JSONAssert.assertEquals(result, expected, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SmallTest
    public void testJSON4 () {
        // given
        String given = "{ \"field\": 33, \"name\": \"Smith\", \"school\": { \"name\": \"St. Mary\", \"start\": 2008, \"end\": 2010 } }";

        // expected
        JSONObject expected1 = new JSONObject();
        try {
            expected1.put("name", "St. Mary");
            expected1.put("start", 2008);
            expected1.put("end", 2010);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject expected2 = new JSONObject();
        try {
            expected2.put("field", 33);
            expected2.put("name", "Smith");
            expected2.put("school", expected1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // then
        JSONObject result = SAJsonParser.newObject(given);

        try {
            JSONAssert.assertEquals(result, expected2, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SmallTest
    public void testJSON5 () {
        // given
        String given = null;

        // expected
        JSONObject expected = new JSONObject();

        // then
        JSONObject result = SAJsonParser.newObject(given);

        try {
            JSONAssert.assertEquals(result, expected, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SmallTest
    public void testJSON6 () {
        // given
        String given1 = "{ name : 23 }";
        String given2 = "";
        String given3 = "{ \"name: 48 ";
        String given4 = "{ \"name: 33} ";

        // expected
        JSONObject expected1 = new JSONObject();
        try {
            expected1.put("name", 23);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject expected2 = new JSONObject();

        // then
        JSONObject result1 = SAJsonParser.newObject(given1);
        JSONObject result2 = SAJsonParser.newObject(given2);
        JSONObject result3 = SAJsonParser.newObject(given3);
        JSONObject result4 = SAJsonParser.newObject(given4);

        try {
            JSONAssert.assertEquals(result1, expected1, false);
            JSONAssert.assertEquals(result2, expected2, false);
            JSONAssert.assertEquals(result3, expected2, false);
            JSONAssert.assertEquals(result4, expected2, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SmallTest
    public void testJSON7 () {
        // given
        String given = "{ \"name\": \"John\", \"grades\": [ 6, 7, 8, \"pass\"] }";

        // expected
        JSONArray expected1 = new JSONArray();
        expected1.put(6);
        expected1.put(7);
        expected1.put(8);
        expected1.put("pass");
        JSONObject expected2 = new JSONObject();
        try {
            expected2.put("name", "John");
            expected2.put("grades", expected1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // then
        JSONObject result = SAJsonParser.newObject(given);

        try {
            JSONAssert.assertEquals(result, expected2, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SmallTest
    public void testJSON8 () {
        // given
        String given = "{ \"name\": \"John\", \"grades\": [ 3, null, 5, \"pass\" ] }";

        // expected
        JSONArray expected1 = new JSONArray();
        expected1.put(3);
        expected1.put(JSONObject.NULL);
        expected1.put(5);
        expected1.put("pass");
        JSONObject expected2 = new JSONObject();
        try {
            expected2.put("name", "John");
            expected2.put("grades", expected1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // then
        JSONObject result = SAJsonParser.newObject(given);

        try {
            JSONAssert.assertEquals(result, expected2, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SmallTest
    public void testJSON9 () {
        // given
        String given = "[ 3, 2, 5, \"abc\" ]";

        // when
        JSONObject expected = new JSONObject();

        // then
        JSONObject result = SAJsonParser.newObject(given);

        // assert
        try {
            JSONAssert.assertEquals(result, expected, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SmallTest
    public void testJSON10 () {
        // given
        String given = "{ \"field\": 33, \"name\": \"Smith\", \"school\": { \"name\": \"St. Mary\", \"start\": 2008, \"end\": 2010 }, \"grades\":[3, 2] }";

        // when
        JSONArray a1 = new JSONArray().put(3).put(2);
        JSONObject d1 = new JSONObject();
        try {
            d1.put("name", "St. Mary").put("start", 2008).put("end", 2010);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject expected = new JSONObject();
        try {
            expected.put("field", 33).put("name", "Smith").put("school", d1).put("grades", a1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int expected1 = 33;
        int expected2 = -1;
        int expected3 = -1;
        String expected4 = "test";
        String expected5 = "Smith";
        String expected6 = "test2";
        Object expected7 = 33;
        Object expected8 = "Smith";
        Object expected9 = new JSONObject();
        try {
            ((JSONObject)expected9).put("name", "St. Mary").put("start", 2008).put("end", 2010);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject expected10 = new JSONObject();
        JSONObject expected11 = null;
        JSONObject expected12 = (JSONObject) expected9;
        JSONArray expected13 = null;
        JSONArray expected14 = new JSONArray().put(3).put(2);

        // then
        JSONObject result = SAJsonParser.newObject(given);

        int result1 = SAJsonParser.getInt(result, "field", -1);
        int result2 = SAJsonParser.getInt(result, "name", -1);
        int result3 = SAJsonParser.getInt(result, "school", -1);
        String result4 = SAJsonParser.getString(result, "field", "test");
        String result5 = SAJsonParser.getString(result, "name", null);
        String result6 = SAJsonParser.getString(result, "school", "test2");
        Object result7 = SAJsonParser.get(result, "field", null);
        Object result8 = SAJsonParser.get(result, "name", null);
        Object result9 = SAJsonParser.get(result, "school", new JSONObject());
        JSONObject result10 = SAJsonParser.getJsonObject(result, "field", new JSONObject());
        JSONObject result11 = SAJsonParser.getJsonObject(result, "name", null);
        JSONObject result12 = SAJsonParser.getJsonObject(result, "school", null);
        JSONArray result13 = SAJsonParser.getJsonArray(result, "school", null);
        JSONArray result14 = SAJsonParser.getJsonArray(result, "grades", null);

        // assert
        try {
            JSONAssert.assertEquals(result, expected, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assertEquals(result1, expected1);
        assertEquals(result2, expected2);
        assertEquals(result3, expected3);
        assertEquals(result4, expected4);
        assertEquals(result5, expected5);
        assertEquals(result6, expected6);
        assertEquals(result7, expected7);
        assertEquals(result8, expected8);
        try {
            JSONAssert.assertEquals((JSONObject)result9, (JSONObject)expected9, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONAssert.assertEquals(result10, expected10, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        assertEquals(result11, expected11);

        try {
            JSONAssert.assertEquals(result12, expected12, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        assertEquals(result13, expected13);
        assertEquals(result14, expected14);
    }
}
