package com.omarbakr.foodfetch.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SessionNotFoundException.class)
    public ProblemDetail handleSessionNotFound(SessionNotFoundException ex) {
        return createProblemDetail(HttpStatus.NOT_FOUND, "Session Not Found", ex);
    }

    @ExceptionHandler(SessionNotClosedException.class)
    public ProblemDetail handleSessionNotClosed(SessionNotClosedException ex) {
        return createProblemDetail(HttpStatus.CONFLICT, "Session Not Closed", ex);
    }

    @ExceptionHandler(SessionClosedException.class)
    public ProblemDetail handleSessionClosed(SessionClosedException ex) {
        return createProblemDetail(HttpStatus.CONFLICT, "Session Already Closed", ex);
    }

    @ExceptionHandler(SessionAlreadyClosedException.class)
    public ProblemDetail handleSessionAlreadyClosed(SessionAlreadyClosedException ex) {
        return createProblemDetail(HttpStatus.CONFLICT, "Session Already Closed", ex);
    }

    @ExceptionHandler(InvalidSessionCodeException.class)
    public ProblemDetail handleInvalidSessionCode(InvalidSessionCodeException ex) {
        return createProblemDetail(HttpStatus.BAD_REQUEST, "Invalid Session Code", ex);
    }

    @ExceptionHandler(ExtraFeesNotSetException.class)
    public ProblemDetail handleExtraFeesNotSet(ExtraFeesNotSetException ex) {
        return createProblemDetail(HttpStatus.BAD_REQUEST, "Extra Fees Not Set", ex);
    }

    @ExceptionHandler(PersonOrderAlreadyExistsException.class)
    public ProblemDetail handlePersonOrderAlreadyExists(PersonOrderAlreadyExistsException ex) {
        return createProblemDetail(HttpStatus.CONFLICT, "Person Already Ordered", ex);
    }

    @ExceptionHandler(MissingPersonNameException.class)
    public ProblemDetail handleMissingPersonName(MissingPersonNameException ex) {
        return createProblemDetail(HttpStatus.BAD_REQUEST, "Missing Person Name", ex);
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ProblemDetail handleRestaurantNotFound(RestaurantNotFoundException ex) {
        return createProblemDetail(HttpStatus.NOT_FOUND, "Restaurant Not Found", ex);
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception ex) {
        return createProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected Error", ex);
    }

    private ProblemDetail createProblemDetail(HttpStatus status, String title, Exception ex) {
        ProblemDetail problem = ProblemDetail.forStatus(status);
        problem.setTitle(title);
        problem.setDetail(ex.getMessage());
        return problem;
    }
}
