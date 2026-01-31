package pl.bartek.githubproxy.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pl.bartek.githubproxy.dto.github.GitHubBranch;
import pl.bartek.githubproxy.dto.github.GitHubRepo;
import pl.bartek.githubproxy.exception.UserNotExistException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GithubClient {

    private final WebClient githubWebClient;

    public Flux<GitHubRepo> getUserRepositories(String username) {
        return githubWebClient
                .get()
                .uri("/users/{username}/repos", username)
                .retrieve()
                .onStatus(status -> status.value() == 404,
                        response -> Mono.error(new UserNotExistException("User with username '" + username + "' does not exist")))
                .bodyToFlux(GitHubRepo.class);
    }

    public Flux<GitHubBranch> getBranches(String username, String repo) {
        return githubWebClient
                .get()
                .uri("/repos/{username}/{repo}/branches", username, repo)
                .retrieve()
                .bodyToFlux(GitHubBranch.class);
    }

}
