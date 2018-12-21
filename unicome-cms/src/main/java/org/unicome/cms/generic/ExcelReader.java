package org.unicome.cms.generic;

import java.util.List;
import java.util.Map;

public class ExcelReader<T> {
    public List<T> read(ExcelReaderCallback<T> callback) throws Exception {
        return null;
    }

    public static void main(String[] args) {
        try {
            List<String> result = new ExcelReader<String>().read(new ExcelReaderCallback<String>() {
                @Override
                public String setEntity(Map<String, String> map) {
                    // TODO
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

interface ExcelReaderCallback<T> {
    public T setEntity(Map<String, String> map);
}

