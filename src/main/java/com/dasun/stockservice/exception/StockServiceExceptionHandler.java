package com.dasun.stockservice.exception;

import com.dasun.stockservice.common.CommonController;
import com.dasun.stockservice.constants.ErrorCodes;
import com.dasun.stockservice.stock.StockController;
import com.dasun.stockservice.stock.StockServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletRequest;
import java.util.stream.Collectors;

@ControllerAdvice
public class StockServiceExceptionHandler extends CommonController {

    private static final Logger logger = LoggerFactory.getLogger(StockServiceExceptionHandler.class);

    /**
     * Handles invalid input exceptions
     *
     * @param e InvalidInputException
     * @return ResponseEntity
     */
    @ExceptionHandler(value = InvalidInputException.class)
    public ResponseEntity<?> exception(InvalidInputException e) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCodes.INVALID_INPUT_ERROR_CODE,
                ErrorCodes.INVALID_INPUT_ERROR_MSG, e.getMessage());

        logger.error("Invalid Input Exception : " + e.getMessage());

        return generateResponse(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Handles input validation exceptions
     *
     * @param e InvalidInputException
     * @return ResponseEntity
     */
    @ExceptionHandler(value = InputValidationException.class)
    public ResponseEntity<?> exception(InputValidationException e) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCodes.INVALID_INPUT_ERROR_CODE,
                ErrorCodes.INVALID_INPUT_ERROR_MSG, e.getParameterName());

        logger.error(
                "Input Validation Exception : Parameter : " + e.getParameterName() + " , Message :" + e.getMessage());

        return generateResponse(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Handles record not found exceptions
     *
     * @param e RecordNotFoundException
     * @return ResponseEntity
     */
    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<?> exception(RecordNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCodes.INVALID_INPUT_ERROR_CODE,
                ErrorCodes.INVALID_INPUT_ERROR_MSG, e.getMessage());

        logger.error("Record Not Found Exception :" + e.getMessage());

        return generateResponse(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles missing API parameter exception
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> MissingServletRequestParameterException(
            MissingServletRequestParameterException e) {

        ErrorResponse errorResponse = new ErrorResponse(ErrorCodes.MISSING_API_FIELD_ERROR_CODE,
                ErrorCodes.MISSING_API_FIELD_ERROR_MSG, e.getParameterName());

        logger.error("Missing Servlet Request Parameter Exception : Parameter : " + e.getParameterName()
                + " , Message :" + e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Handles missing API parameter exception
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMsg = e.getAllErrors().stream()
                .map(a -> DefaultMessageSourceResolvable.class.cast(a.getArguments()[0]).getDefaultMessage() + " - " + String.valueOf(a.getDefaultMessage()))
                .collect(Collectors.joining(","));

        ErrorResponse errorResponse = new ErrorResponse(ErrorCodes.API_OBJECT_FIELD_VALIDATION_ERROR_CODE,
                ErrorCodes.API_OBJECT_FIELD_VALIDATION_ERROR_MSG, errorMsg);

        logger.error("Missing Servlet Request Parameter Exception : " + e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Handles any other unhandled exceptions
     *
     * @param servletRequest
     * @param e              Exception
     * @return ResponseEntity
     */
    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<?> handleOtherExceptions(ServletRequest servletRequest, final Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCodes.INTERNAL_ERROR_CODE, ErrorCodes.INTERNAL_ERROR_MSG,
                e.getMessage());

        logger.error("Unhandled Exception :" + e.getMessage());

        return generateResponse(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
