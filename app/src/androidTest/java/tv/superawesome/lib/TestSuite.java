package tv.superawesome.lib;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by gabriel.coman on 17/10/16.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SAJsonParser_Array_Deserialization_Tests.class,
        SAJsonParser_Array_Serialization_Tests.class,
        SAJsonParser_Dictionary_Serialization_Tests.class,
        SAJsonParser_Dictionary_Deserialization_Tests.class,
        SAJsonParser_Object_Deserialization_Tests.class,
        SAJsonParser_Object_Serialization_Tests.class
})
public class TestSuite {
}
