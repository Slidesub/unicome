package org.unicome.cms.template;

import lombok.Data;

@Data
public class ExcelException extends Exception {
    private String message;

    ExcelException(String message) {
        super(message);
        this.message = message;
    }
}
