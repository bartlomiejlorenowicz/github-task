package pl.bartek.githubproxy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.bartek.githubproxy.exception.response.ApiError;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(UserNotExistException.class)
    public ResponseEntity<ApiError> handleUserNotExistException(UserNotExistException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiError(404, ex.getMessage()));
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<String> handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex) {

        String json = """
                {
                  "status": 406,
                  "message": "Requested media type is not acceptable"
                }
                """;

        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(json);
    }

    @ExceptionHandler(RepoNotFoundException.class)
    public ResponseEntity<ApiError> handleRepoNotFound(RepoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiError(404, ex.getMessage()));
    }

}
