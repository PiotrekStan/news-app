package com.stankiewicz.newsserver.api.exception;

import com.stankiewicz.newsserver.api.exception.model.ErrorResponseDTO;
import com.stankiewicz.newsserver.api.exception.model.enums.ErrorCodeDTO;
import com.stankiewicz.newsserver.client.exception.api.NewsApiException;
import com.stankiewicz.newsserver.client.exception.api.NewsApiKeyInvalidException;
import com.stankiewicz.newsserver.client.exception.api.NewsApiKeyMissingException;
import com.stankiewicz.newsserver.client.exception.client.NewsClientConnectionException;
import com.stankiewicz.newsserver.client.exception.client.NewsClientException;
import com.stankiewicz.newsserver.client.exception.client.NewsClientUnauthorized;
import com.stankiewicz.newsserver.client.exception.server.NewsServerException;
import com.stankiewicz.newsserver.client.exception.server.NewsServiceUnavailable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controller advice for mapping exceptions thrown by application
 */
@ControllerAdvice
public class ExceptionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(NewsApiKeyInvalidException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidApiKeyException(NewsApiKeyInvalidException e) {
        LOGGER.error("NewsApiKeyInvalidException occurred with message: {}", e.getLocalizedMessage());
        ErrorResponseDTO responseDto = new ErrorResponseDTO(ErrorCodeDTO.INVALID_API_KEY, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

    @ExceptionHandler(NewsApiKeyMissingException.class)
    public ResponseEntity<ErrorResponseDTO> handleMissingApiKeyException(NewsApiKeyMissingException e) {
        LOGGER.error("NewsApiKeyMissingException occurred with message: {}", e.getLocalizedMessage());
        ErrorResponseDTO responseDto = new ErrorResponseDTO(ErrorCodeDTO.MISSING_API_KEY, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

    @ExceptionHandler(NewsApiException.class)
    public ResponseEntity<ErrorResponseDTO> handleUnexpectedException(NewsApiException e) {
        LOGGER.warn("NewsApiException occurred with message: {}", e.getLocalizedMessage());
        ErrorResponseDTO responseDto = new ErrorResponseDTO(ErrorCodeDTO.UNEXPECTED_ERROR, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

    @ExceptionHandler(NewsClientException.class)
    public ResponseEntity<ErrorResponseDTO> handleClientException(NewsClientException e) {
        LOGGER.warn("NewsClientException occurred with message: {}", e.getLocalizedMessage());
        ErrorResponseDTO responseDto = new ErrorResponseDTO(ErrorCodeDTO.CLIENT_ERROR, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

    @ExceptionHandler(NewsClientUnauthorized.class)
    public ResponseEntity<ErrorResponseDTO> handleUnauthorizedException(NewsClientUnauthorized e) {
        LOGGER.warn("NewsClientUnauthorized occurred with message: {}", e.getLocalizedMessage());
        ErrorResponseDTO responseDto = new ErrorResponseDTO(ErrorCodeDTO.NEWS_API_UNAUTHORIZED, e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDto);
    }

    @ExceptionHandler(NewsServiceUnavailable.class)
    public ResponseEntity<ErrorResponseDTO> handleNewsServiceUnavailableException(NewsServiceUnavailable e) {
        LOGGER.warn("NewsServiceUnavailable occurred with message: {}", e.getLocalizedMessage());
        ErrorResponseDTO responseDto = new ErrorResponseDTO(ErrorCodeDTO.NEWS_API_UNAVAILABLE, e.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(responseDto);
    }

    @ExceptionHandler(NewsServerException.class)
    public ResponseEntity<ErrorResponseDTO> handleNewsServerException(NewsServerException e) {
        LOGGER.warn("NewsServerException occurred with message: {}", e.getLocalizedMessage());
        ErrorResponseDTO responseDto = new ErrorResponseDTO(ErrorCodeDTO.SERVER_ERROR, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

    @ExceptionHandler(NewsClientConnectionException.class)
    public ResponseEntity<ErrorResponseDTO> handleClientConnectionExcetpion(NewsClientConnectionException e) {
        LOGGER.warn("NewsClientConnectionException occurred with message: {}", e.getLocalizedMessage());
        ErrorResponseDTO responseDto = new ErrorResponseDTO(ErrorCodeDTO.CLIENT_CONNECTION_EXCEPTION, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

}
