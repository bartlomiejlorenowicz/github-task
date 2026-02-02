package pl.bartek.githubproxy.exception;

public class RepoNotFoundException extends RuntimeException {
    public RepoNotFoundException(String message) {
        super(message);
    }
}
