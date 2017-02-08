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
public class SAJsonParser_Array_Serialization_Tests extends ApplicationTestCase<Application> {
    public SAJsonParser_Array_Serialization_Tests() {
        super(Application.class);
    }

    @SmallTest
    public void testArray1 () {
        // given
        JSONArray given = SAJsonParser.newArray(new Object[] {
            3, 2, "name", null
        });

        // then
        String expected = "[3,2,\"name\",null]";

        // then
        String result = given.toString();

        try {
            JSONArray expectedArray = new JSONArray(expected);
            JSONArray resultArray = new JSONArray(result);
            assertNotNull(expectedArray);
            assertNotNull(resultArray);
            JSONAssert.assertEquals(expectedArray, resultArray, false);
        } catch (JSONException e) {
            fail("Exception occurred " + e.toString());
        }
    }

    @SmallTest
    public void testArray2 () {
        // given
        JSONArray given = SAJsonParser.newArray(new Object[] {
                3,
                "name",
                SAJsonParser.newObject(new Object[] {
                        "name", "John",
                        "age", 32
                }),
                SAJsonParser.newObject(new Object[] {
                        "name", "mary",
                        "age", null
                })
        });

        // when
        String expected = "[3,\"name\",{\"age\":32,\"name\":\"John\"},{\"name\":\"mary\"}]";

        // then
        String result = given.toString();

        try {
            JSONArray expectedArray = new JSONArray(expected);
            JSONArray resultArray = new JSONArray(result);
            assertNotNull(expectedArray);
            assertNotNull(resultArray);
            JSONAssert.assertEquals(expectedArray, resultArray, false);
        } catch (JSONException e) {
            fail("Exception occurred " + e.toString());
        }
    }

    @SmallTest
    public void testArray3 () {
        // given
        JSONArray given1 = SAJsonParser.newArray((String)null);
        JSONArray given2 = SAJsonParser.newArray((Object[]) null);

        // when
        String expected = "[]";

        // then
        String result1 = given1.toString();
        String result2 = given2.toString();

        try {
            JSONArray expectedArray = new JSONArray(expected);
            JSONArray resultArray1 = new JSONArray(result1);
            JSONArray resultArray2 = new JSONArray(result2);
            assertNotNull(expectedArray);
            assertNotNull(resultArray1);
            assertNotNull(resultArray2);
            JSONAssert.assertEquals(expectedArray, resultArray1, false);
            JSONAssert.assertEquals(expectedArray, resultArray2, false);
        } catch (JSONException e) {
            fail("Exception occurred " + e.toString());
        }
    }
}
