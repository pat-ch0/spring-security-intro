package fr.aelion.atosBoris.cyber.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Data
@NoArgsConstructor
public class ApiError {
    private Integer code;
    private String msg;
    private Exception exception;

    // No setter to emulate final behavior
    @Value("${debug:false}")
    @Setter(AccessLevel.NONE)
    @JsonIgnore
    private boolean debugActive; //TODO: Boolean isn't working

    public ApiError(Integer code, String msg, Exception exception) {
        this.code = code; // TODO: maybe enum, or anything like HttpStatus.CODE
        this.msg = msg;
        if(this.debugActive){
            this.exception = exception;
        }
    }
}
