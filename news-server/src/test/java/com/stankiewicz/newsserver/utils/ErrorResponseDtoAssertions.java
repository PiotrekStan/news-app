package com.stankiewicz.newsserver.utils;

import com.stankiewicz.newsserver.api.exception.model.ErrorResponseDTO;
import com.stankiewicz.newsserver.api.exception.model.enums.ErrorCodeDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@AllArgsConstructor
public class ErrorResponseDtoAssertions {
    private ResponseEntity<ErrorResponseDTO> responseData;

    public static ErrorResponseDtoAssertions assertResponse(ResponseEntity<ErrorResponseDTO> responseData) {
        assertThat(responseData).isNotNull();
        return new ErrorResponseDtoAssertions(responseData);
    }

    public ErrorResponseDtoAssertions hasStatus(HttpStatus status) {
        assertThat(this.responseData.getStatusCode()).isEqualTo(status);
        return this;
    }

    public ErrorResponseDtoAssertions hasCodeType(ErrorCodeDTO codeType) {
        assertThat(this.responseData.getBody().getErrorCodeType()).isEqualTo(codeType);
        return this;
    }

    public ErrorResponseDtoAssertions hasCode(int code) {
        assertThat(this.responseData.getBody().getCode()).isEqualTo(code);
        return this;
    }

    public ErrorResponseDtoAssertions hasMessage(String message) {
        assertThat(this.responseData.getBody().getMessage()).isEqualTo(message);
        return this;
    }

}

