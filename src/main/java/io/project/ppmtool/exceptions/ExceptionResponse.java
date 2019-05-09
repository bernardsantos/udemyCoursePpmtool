/**
 *
 */
package io.project.ppmtool.exceptions;

import org.springframework.stereotype.Component;

/**
 * @author Bernard A. Santos Jr.  23 Apr 2019
 */
@Component
public class ExceptionResponse {

    private String message;
    private int code;
    
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    
}

