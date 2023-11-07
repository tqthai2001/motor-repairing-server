package com.goldenboy.server.exception.handler;

import com.goldenboy.server.exception.BusinessLogicException;
import com.goldenboy.server.exception.CustomValidationException;
import com.goldenboy.server.exception.DuplicateEntityException;
import com.goldenboy.server.exception.EntityNotFoundException;
import com.goldenboy.server.exception.errorresponse.CustomErrorResponse;
import com.goldenboy.server.exception.errorresponse.SubError;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
@ResponseBody
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST, ex);
        errorResponse.setMessage("Validation error");
        errorResponse.addValidationErrors(ex.getBindingResult().getFieldErrors());
        errorResponse.addValidationError(ex.getBindingResult().getGlobalErrors());
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(javax.persistence.EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleEntityNotFound(javax.persistence.EntityNotFoundException ex) {
        return buildResponseEntity(new CustomErrorResponse(HttpStatus.NOT_FOUND, ex));
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleNoSuchElement(javax.persistence.NoResultException ex) {
        return buildResponseEntity(new CustomErrorResponse(HttpStatus.NOT_FOUND, ex));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.NOT_FOUND, ex);
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setDebugMessage(StringUtils.joinWith("\n", ex.getStackTrace()));
        errorResponse.addSubError(new SubError(ex.getObject(), "", ex.getMessage()) {});
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(DuplicateEntityException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ResponseEntity<Object> handleDuplicateEntity(DuplicateEntityException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.CONFLICT, ex);
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setDebugMessage(StringUtils.joinWith("\n", ex.getStackTrace()));
        errorResponse.addSubError(new SubError(ex.getObject(), ex.getFieldName(), ex.getMessage()) {
            @Override
            public String toString() {
                return super.toString();
            }
        });
        return buildResponseEntity(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers,
                                                                          HttpStatus status,
                                                                          WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";
        return buildResponseEntity(new CustomErrorResponse(HttpStatus.BAD_REQUEST, error, ex));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers,
                                                                     HttpStatus status,
                                                                     WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        return buildResponseEntity(new CustomErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE, builder.substring(0,
                                                                                                                builder.length() -
                                                                                                                2),
                                                           ex));
    }

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleConstraintViolation(javax.validation.ConstraintViolationException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST, ex);
        errorResponse.setMessage("Validation error");
        errorResponse.addValidationErrors(ex.getConstraintViolations());
        return buildResponseEntity(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        log.info("{} to {}", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());
        String error = "Malformed JSON request";
        return buildResponseEntity(new CustomErrorResponse(HttpStatus.BAD_REQUEST, error, ex));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        String error = "Error writing JSON output";
        return buildResponseEntity(new CustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers,
                                                                   HttpStatus status,
                                                                   WebRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST, ex);
        errorResponse.setMessage(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(),
                                               ex.getRequestURL()));
        errorResponse.setDebugMessage(ex.getMessage());
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
                                                                  WebRequest request) {
        if (ex.getCause() instanceof ConstraintViolationException) {
            return buildResponseEntity(new CustomErrorResponse(HttpStatus.CONFLICT, "Database error", ex.getCause()));
        }
        return buildResponseEntity(new CustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                      WebRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST, ex);
        errorResponse.setMessage(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'"
                , ex.getName(), ex.getValue(), ex.getRequiredType()
                                                                                                                                                      .getSimpleName()));
        errorResponse.setDebugMessage(ex.getMessage());
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> handleUnauthorized(BadCredentialsException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.UNAUTHORIZED, ex);
        errorResponse.setMessage(ex.getMessage() + ": Username or Password incorrect");
        errorResponse.setDebugMessage(StringUtils.joinWith("\n", ex.getStackTrace()));
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.UNAUTHORIZED, ex);
        errorResponse.setMessage(ex.getMessage() + ": You are not unauthorized to request http resource");
        errorResponse.setDebugMessage(StringUtils.joinWith("\n", ex.getStackTrace()));
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(CustomValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleCustomValidation(CustomValidationException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST, ex);
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setDebugMessage(StringUtils.joinWith("\n", ex.getStackTrace()));
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(BusinessLogicException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleBusinessLogicError(BusinessLogicException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST, ex);
        errorResponse.setMessage("Lỗi logic nghiệp vụ (BusinessLogicException): " + ex.getMessage());
        errorResponse.setDebugMessage(ex.getStackTrace().toString());
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllUncaughtException(RuntimeException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex);
        errorResponse.setMessage(ex.getLocalizedMessage());
        errorResponse.setDebugMessage(StringUtils.joinWith("\n", ex.getStackTrace()));
        return buildResponseEntity(errorResponse);
    }

    private ResponseEntity<Object> buildResponseEntity(CustomErrorResponse customErrorResponse) {
        return new ResponseEntity<>(customErrorResponse, customErrorResponse.getStatus());
    }
}
