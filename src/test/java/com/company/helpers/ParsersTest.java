package com.company.helpers;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ParsersTest {


    @Test
    public void should_returnMapWithKeyAndValueStrings_when_providedFormDataAsString() {
        // name=zxc&surname=serverS&login=qweasd&password=pass
        String formData = "name=zxc&surname=server&login=qweasd&password=pass";

        Map<String, String> actualMap = Parsers.parseFormData(formData);
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("name", "zxc");
        expectedMap.put("surname", "server");
        expectedMap.put("login", "qweasd");
        expectedMap.put("password", "pass");

        assertEquals(expectedMap, actualMap);
    }
}