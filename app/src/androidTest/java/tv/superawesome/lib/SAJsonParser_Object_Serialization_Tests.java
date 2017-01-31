package tv.superawesome.lib;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.Arrays;

import tv.superawesome.lib.modelspace.SACompany;
import tv.superawesome.lib.modelspace.SAEmployee;
import tv.superawesome.lib.modelspace.SAPosition;

/**
 * Created by gabriel.coman on 17/10/16.
 */
public class SAJsonParser_Object_Serialization_Tests extends ApplicationTestCase<Application> {
    public SAJsonParser_Object_Serialization_Tests() {
        super(Application.class);
    }

    @SmallTest
    public void testSimpleModel () {
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

    @SmallTest
    public void testComplexModel () {
        // given
        SAPosition given1 = new SAPosition("Junior Engineer", 28000);
        SAPosition given2 = new SAPosition("Engineer", 35000);
        SAEmployee given = new SAEmployee("Jim", 23, true, given2, Arrays.asList(given1));

        // when
        JSONObject ex1 = new JSONObject();
        try {
            ex1.put("name", "Junior Engineer").put("salary", 28000);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject ex2 = new JSONObject();
        try {
            ex2.put("name", "Engineer").put("salary", 35000);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray arry = new JSONArray();
        arry.put(ex1);
        JSONObject expected = new JSONObject();
        try {
            expected.
                    put("name", "Jim").
                    put("age", 23).
                    put("isActive", true).
                    put("position", ex2).
                    put("previous", arry);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // then
        JSONObject result = given.writeToJson();

        try {
            JSONAssert.assertEquals(expected, result, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SmallTest
    public void testVeryComplexModel () {
        // given
        SAPosition given00 = new SAPosition("Intern", 0);
        SAPosition given11 = new SAPosition("Junior Engineer", 28000);
        SAPosition given12 = new SAPosition("Engineer", 35000);

        SAEmployee given1 = new SAEmployee("John", 23, true, given11, Arrays.asList(given00));
        SAEmployee given2 = new SAEmployee("Danna", 18, true, given12, Arrays.asList(given00, given11));

        SACompany given = new SACompany("John Smith Ltd", Arrays.asList(given1, given2));

        // when
        JSONObject ex00 = new JSONObject();
        try { ex00.put("name", "Intern").put("salary", 0); } catch (JSONException ignored) {}
        JSONObject ex11 = new JSONObject();
        try { ex11.put("name", "Junior Engineer").put("salary", 28000); } catch (JSONException ignored) {}
        JSONObject ex12 = new JSONObject();
        try { ex12.put("name", "Engineer").put("salary", 35000); } catch (JSONException ignored) {}

        JSONArray arr11 = new JSONArray().put(ex00);
        JSONArray arr22 = new JSONArray().put(ex00).put(ex11);

        JSONObject ex1 = new JSONObject();
        try {
            ex1.put("name", "John").put("age", 23).put("isActive", true).put("position", ex11).put("previous", arr11);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject ex2 = new JSONObject();
        try {
            ex2.put("name", "Danna").put("age", 18).put("isActive", true).put("position", ex12).put("previous", arr22);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray arr33 = new JSONArray().put(ex1).put(ex2);

        JSONObject expected = new JSONObject();
        try {
            expected.put("name", "John Smith Ltd").put("employees", arr33);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // then
        JSONObject result = given.writeToJson();

        try {
            JSONAssert.assertEquals(expected, result, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SmallTest
    public void testModelWithNulls () {
        // given
        SAPosition position = new SAPosition("Junior Engineer", 28000);
        SAEmployee given = new SAEmployee(null, 32, false, null, Arrays.asList(position));

        // then
        JSONObject pos = new JSONObject();
        try {
            pos.put("name", "Junior Engineer").put("salary", 28000);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray arr = new JSONArray().put(pos);
        JSONObject expected = new JSONObject();
        try {
            expected.put("name", JSONObject.NULL).put("age", 32).put("isActive", false).put("position", JSONObject.NULL).put("previous", arr);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // then
        JSONObject result = given.writeToJson();

        try {
            JSONAssert.assertEquals(expected, result, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
