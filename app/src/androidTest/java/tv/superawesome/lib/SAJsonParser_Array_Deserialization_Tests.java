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
public class SAJsonParser_Array_Deserialization_Tests extends ApplicationTestCase<Application> {
    public SAJsonParser_Array_Deserialization_Tests() {
        super(Application.class);
    }

    @SmallTest
    public void testJSON1 () {
        // given
        String given = "[ 23, 5, 2, 8 ]";

        // then
        JSONArray expected = new JSONArray();
        expected.put(23).put(5).put(2).put(8);

        JSONArray result = SAJsonParser.newArray(given);

        try {
            JSONAssert.assertEquals(expected, result, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SmallTest
    public void testJSON2 () {
        // given
        String given = "[ 23, 5, \"papa\", null ]";

        // when
        JSONArray expected = new JSONArray();
        expected.put(23).put(5).put("papa").put(JSONObject.NULL);

        // then
        JSONArray result = SAJsonParser.newArray(given);

        try {
            JSONAssert.assertEquals(expected, result, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SmallTest
    public void testJSON3 () {
        // given
        String given = "[ 23, \"papa\", { \"name\": \"john\", \"age\": 23 }, { \"name\": \"theresa\" } ]";

        // then
        JSONObject e1 = new JSONObject();
        JSONObject e2 = new JSONObject();
        try {
            e1.put("name", "john");
            e1.put("age", 23);
            e2.put("name", "theresa");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray expected = new JSONArray();
        expected.put(23).put("papa").put(e1).put(e2);

        // then
        JSONArray result = SAJsonParser.newArray(given);

        try {
            JSONAssert.assertEquals(expected, result, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SmallTest
    public void testJSON4 () {
        // given
        String given1 = "";
        String given2 = null;

        // expected
        JSONArray expected = new JSONArray();

        // then
        JSONArray result1 = SAJsonParser.newArray(given1);
        JSONArray result2 = SAJsonParser.newArray(given2);

        try {
            JSONAssert.assertEquals(expected, result1, false);
            JSONAssert.assertEquals(expected, result2, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SmallTest
    public void testJSON5 () {
        // given
        String given1 = "[ 3, 3, \" abc ";
        String given2 = "[ 3, 3, \" abc ";
        String given3 = " 3, 3";
        String given4 = "";

        // expected
        JSONArray expected = new JSONArray();

        // then
        JSONArray result1 = SAJsonParser.newArray(given1);
        JSONArray result2 = SAJsonParser.newArray(given2);
        JSONArray result3 = SAJsonParser.newArray(given3);
        JSONArray result4 = SAJsonParser.newArray(given4);

        try {
            JSONAssert.assertEquals(expected, result1, false);
            JSONAssert.assertEquals(expected, result2, false);
            JSONAssert.assertEquals(expected, result3, false);
            JSONAssert.assertEquals(expected, result4, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SmallTest
    public void testJSON6 () {
        // given
        String given = "{ \"name\": \"John\", \"age\": 35 }";

        // then
        JSONArray expected = new JSONArray();

        JSONArray result = SAJsonParser.newArray(given);

        try {
            JSONAssert.assertEquals(expected, result, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
