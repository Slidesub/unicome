package org.unicome.cms.template;

import java.io.File;
import java.util.Map;

public interface ExcelParserInterface {
    public Map<String, String> parse(File file) throws ExcelException;
    public Map<String, String> parse2(File file) throws ExcelException;
}
