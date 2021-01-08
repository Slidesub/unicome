package org.unicome.data.resource.util;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectionUtils extends org.springframework.util.CollectionUtils {

    public static final String DEFAULT_SEPARATOR_COMMA = ",";

    public static Set<String> separatedStringToSet(String... str) {
        if (str !=  null && str.length > 0 && !StringUtils.isEmpty(str[0])) {
            String separator = DEFAULT_SEPARATOR_COMMA;
            if (str.length > 1) {
                separator = str[1];
            }
            return Arrays.stream(str[0].split(separator)).map(s -> s.trim()).collect(Collectors.toSet());
        }
        return Collections.EMPTY_SET;
    }

    public static List<String> separatedStringToList(String... str) {
        if (str != null && str.length > 0 && !StringUtils.isEmpty(str[0])) {
            String separator = DEFAULT_SEPARATOR_COMMA;
            if (str.length > 1) {
                separator = str[1];
            }
            return Arrays.stream(str[0].split(separator)).map(s -> s.trim()).collect(Collectors.toList());
        }
        return Collections.EMPTY_LIST;
    }
}
