package annotations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnnotationDemoTest {

    @Test
    void jsonAnnotationTest() {
        Person person1 = new Person("fname1", "lname1", "28", "addr1");
        ObjectToJsonConvertor convertor = new ObjectToJsonConvertor();
        try {
            String person1JsonString = convertor.convertToJson(person1);
            System.out.println("person1JsonString: " + person1JsonString);
        } catch (JsonSerializationException e) {
            e.printStackTrace();
        }
    }

}