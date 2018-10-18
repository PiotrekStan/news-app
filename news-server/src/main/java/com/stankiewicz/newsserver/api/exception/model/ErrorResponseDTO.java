package com.stankiewicz.newsserver.api.exception.model;

import com.stankiewicz.newsserver.api.exception.model.enums.ErrorCodeDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponseDTO {

    private int code;
    private ErrorCodeDTO errorCodeType;
    private String message;

    public ErrorResponseDTO(ErrorCodeDTO type, String message) {
        this.errorCodeType = type;
        this.message = message;
        this.code = type.ordinal();
    }

}
