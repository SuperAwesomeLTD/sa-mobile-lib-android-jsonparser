package tv.superawesome.lib.sajsonparser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by gabriel.coman on 17/10/16.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestSAJsonParser_ParseArray.class,
        TestSAJsonParser_WriteArray.class,
        TestSAJsonParser_WriteDictionary.class,
        TestSAJsonParser_ParseDictionary.class,
        TestSAJsonParser_ParseObject.class,
        TestSAJsonParser_WriteObject.class
})
public class TestSuite {
}
