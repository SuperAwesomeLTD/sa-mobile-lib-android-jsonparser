package tv.superawesome.lib.sajsonparser;

import org.json.JSONObject;
import org.junit.Test;

import java.util.Arrays;

import tv.superawesome.lib.sajsonparser.mocks.SAEmployee;
import tv.superawesome.lib.sajsonparser.mocks.SALongHolder;
import tv.superawesome.lib.sajsonparser.mocks.SAPosition;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by gabriel.coman on 17/10/16.
 */
public class TestSAJsonParser_ParseObject {

    @Test
    public void test_SAJsonParser_ParseObject_WithSimpleObjects () {
        // given
        String given0 = "{ \"name\": \"Intern\", \"salary\": 0 }";
        String given1 = "{ \"name\": \"Junior Engineer\", \"salary\": 28000 }";
        String given = "{ \"name\": \"John\", \"age\": 32, \"isActive\": true, \"position\":"+given1+", \"previous\":["+given0+"] }";

        // when
        SAPosition expected0 = new SAPosition("Intern", 0);
        SAPosition expected1 = new SAPosition("Junior Engineer", 28000);
        SAEmployee expected = new SAEmployee("John", 32, true, expected1, Arrays.asList(expected0));

        // then
        JSONObject jsonObject = SAJsonParser.newObject(given);
        SAEmployee result = new SAEmployee(jsonObject);

        // assert
        assertNotNull(jsonObject);
        assertNotNull(result);
        assertEquals(result.name, expected.name);
        assertEquals(result.age, expected.age);
        assertEquals(result.isActive, expected.isActive);
        assertTrue(result.isValid());
        assertNotNull(result.position);
        assertNotNull(result.previous);
        assertEquals(result.previous.size(), 1);
        assertEquals(result.position.name, expected.position.name);
        assertEquals(result.position.salary, expected.position.salary);
    }

    @Test
    public void test_SAJsonParser_ParseObject_WithLongValues () {
        // given
        String given0 = "{ \"val1\": 128, \"val2\": 1478862037 }";

        // when
        SALongHolder expected0 = new SALongHolder(128, 1478862037);

        // then
        JSONObject jsonObject = SAJsonParser.newObject(given0);
        SALongHolder result = new SALongHolder(jsonObject);

        // assert
        assertNotNull(jsonObject);
        assertNotNull(result);
        assertEquals(result.val1, expected0.val1);
        assertEquals(result.val2, expected0.val2);
    }

    @Test
    public void test_SAJsonParser_ParseObject_WithNulls () {
        // given
        String given = null;

        // expected
        SAPosition expected = new SAPosition();

        // then
        JSONObject jsonObject = SAJsonParser.newObject(given);
        SAPosition result = new SAPosition(jsonObject);

        // assert
        assertNotNull(jsonObject);
        assertNotNull(result);
        assertEquals(result.name, expected.name);
        assertEquals(result.salary, expected.salary);
        assertFalse(result.isValid());
    }

    @Test
    public void test_SAJsonParser_ParseObject_WithInvalidInput () {
        // given
        String given = "{ \"name\": \"CEO\" }";

        // expected
        SAPosition expected = new SAPosition("CEO", 0);

        // then
        JSONObject jsonObject = SAJsonParser.newObject(given);
        SAPosition result = new SAPosition(jsonObject);

        // assert
        assertNotNull(jsonObject);
        assertNotNull(result);
        assertEquals(result.name, expected.name);
        assertEquals(result.salary, expected.salary);
        assertTrue(result.isValid());
    }

    @Test
    public void test_SAJsonParser_ParseObject_WithBadJson () {
        // given
        String given1 = "";
        String given2 = "{ \"name: 48 ";
        String given3 = "{ \"name: 33} ";

        // expected

        // then
        JSONObject jsonObject1 = SAJsonParser.newObject(given1);
        JSONObject jsonObject2 = SAJsonParser.newObject(given2);
        JSONObject jsonObject3 = SAJsonParser.newObject(given3);

        SAPosition expected1 = new SAPosition(jsonObject1);
        SAPosition expected2 = new SAPosition(jsonObject2);
        SAPosition expected3 = new SAPosition(jsonObject3);

        // assert
        assertNotNull(jsonObject1);
        assertNotNull(jsonObject2);
        assertNotNull(jsonObject3);
        assertNotNull(expected1);
        assertNotNull(expected2);
        assertNotNull(expected3);
        assertFalse(expected1.isValid());
        assertFalse(expected2.isValid());
        assertFalse(expected3.isValid());
    }
}
