//package Sanhak.wakeUp.global.exception;
//
//import Sanhak.wakeUp.global.utils.dto.ErrorResponse;
//import Sanhak.wakeUp.team.exception.*;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
////전역 예외처리
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//
//    @ExceptionHandler({RuntimeException.class})
//    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
//        String transactionTime = LocalDateTime.now().toString();
//        String status = HttpStatus.INTERNAL_SERVER_ERROR.toString();
//
//        // 500 Internal Server Error 처리
//        ErrorResponse errorResponse = new ErrorResponse(
//                transactionTime,
//                status,
//                "Internal server error: " + e.getMessage(),
//                HttpStatus.INTERNAL_SERVER_ERROR.value()
//        );
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//    }
//
//    @ExceptionHandler({BadRequestException.class})
//    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e) {
//        String transactionTime = LocalDateTime.now().toString();
//        String status = HttpStatus.BAD_REQUEST.toString();
//
//        // 400 Bad Request 처리
//        ErrorResponse errorResponse = new ErrorResponse(
//                transactionTime,
//                status,
//                "Bad Request: " + e.getMessage(),
//                HttpStatus.BAD_REQUEST.value()
//        );
//        return ResponseEntity.badRequest().body(errorResponse);
//    }
//
//    @ExceptionHandler({DuplicateUserException.class})
//    public ResponseEntity<ErrorResponse> handleDuplicateUserException(DuplicateUserException e) {
//        String transactionTime = LocalDateTime.now().toString();
//        String status = HttpStatus.BAD_REQUEST.toString();
//
//        ErrorResponse errorResponse = new ErrorResponse(
//                transactionTime,
//                status,
//                "Duplicate User: " + e.getMessage(),
//                HttpStatus.BAD_REQUEST.value()
//        );
//        return ResponseEntity.badRequest().body(errorResponse);
//    }
//
//
//    @ExceptionHandler({UserNotFoundException.class})
//    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
//        String transactionTime = LocalDateTime.now().toString();
//        String status = HttpStatus.NOT_FOUND.toString();
//
//        ErrorResponse errorResponse = new ErrorResponse(
//                transactionTime,
//                status,
//                "User not found: " + e.getMessage(),
//                HttpStatus.NOT_FOUND.value()
//        );
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
//    }
//
//    @ExceptionHandler(ValidationException.class)
//    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException e) {
//        String transactionTime = LocalDateTime.now().toString();
//        String status = HttpStatus.BAD_REQUEST.toString();
//        ErrorResponse errorResponse = new ErrorResponse(
//                transactionTime,
//                status,
//                "Bad Request: " + e.getMessage(),
//                HttpStatus.BAD_REQUEST.value()
//        );
//        return ResponseEntity.badRequest().body(errorResponse);
//    }
//
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
//        String transactionTime = LocalDateTime.now().toString();
//        String status = HttpStatus.BAD_REQUEST.toString();
//        ErrorResponse errorResponse = new ErrorResponse(
//                transactionTime,
//                status,
//                "Bad Request: Request body format is invalid",
//                HttpStatus.BAD_REQUEST.value()
//        );
//        return ResponseEntity.badRequest().body(errorResponse);
//    }
//
//    @ExceptionHandler(MissingServletRequestParameterException.class)
//    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
//        String transactionTime = LocalDateTime.now().toString();
//        String status = HttpStatus.BAD_REQUEST.toString();
//        ErrorResponse errorResponse = new ErrorResponse(
//                transactionTime,
//                status,
//                "Bad Request: Required parameter is missing",
//                HttpStatus.BAD_REQUEST.value()
//        );
//        return ResponseEntity.badRequest().body(errorResponse);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        String transactionTime = LocalDateTime.now().toString();
//        String status = HttpStatus.BAD_REQUEST.toString();
//        String errorMessage = "Bad Request: Validation failed for the following fields - ";
//        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
//        for (FieldError fieldError : fieldErrors) {
//            errorMessage += fieldError.getField() + ", ";
//        }
//        errorMessage = errorMessage.substring(0, errorMessage.length() - 2); // Remove the trailing comma and space
//        ErrorResponse errorResponse = new ErrorResponse(
//                transactionTime,
//                status,
//                errorMessage,
//                HttpStatus.BAD_REQUEST.value()
//        );
//        return ResponseEntity.badRequest().body(errorResponse);
//    }
//
//    @ExceptionHandler({CustomBusinessLogicException.class})
//    public ResponseEntity<ErrorResponse> handleCustomBusinessLogicException(CustomBusinessLogicException e) {
//        String transactionTime = LocalDateTime.now().toString();
//        String status = HttpStatus.BAD_REQUEST.toString();
//        ErrorResponse errorResponse = new ErrorResponse(
//                transactionTime,
//                status,
//                "Bad Request: " + e.getMessage(),
//                HttpStatus.BAD_REQUEST.value()
//        );
//        return ResponseEntity.badRequest().body(errorResponse);
//    }
//
//
//
//
//
//
//
//    @ExceptionHandler(TokenValidationException.class)
//    public ResponseEntity<ErrorResponse> handleTokenValidationException(TokenValidationException e) {
//        String transactionTime = LocalDateTime.now().toString();
//        String status = HttpStatus.UNAUTHORIZED.toString();
//
//        ErrorResponse errorResponse = new ErrorResponse(
//                transactionTime,
//                status,
//                "Token validation failed: " + e.getMessage(),
//                HttpStatus.UNAUTHORIZED.value()
//        );
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
//    }
//    // Token이 없을 경우 처리 핸들러 추가
//    @ExceptionHandler(TokenNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleTokenNotFoundException(TokenNotFoundException e) {
//        String transactionTime = LocalDateTime.now().toString();
//        String status = HttpStatus.UNAUTHORIZED.toString();
//
//        ErrorResponse errorResponse = new ErrorResponse(
//                transactionTime,
//                status,
//                "Token not found: " + e.getMessage(),
//                HttpStatus.UNAUTHORIZED.value()
//        );
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
//    }
//
//
//
//}
//
