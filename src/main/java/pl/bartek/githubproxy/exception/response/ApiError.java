package pl.bartek.githubproxy.exception.response;

import org.springframework.http.HttpStatus;

public record ApiError(int status, String message) {

}
