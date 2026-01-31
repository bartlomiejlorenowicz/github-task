package pl.bartek.githubproxy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bartek.githubproxy.dto.response.GitHubRepoResponseDto;
import pl.bartek.githubproxy.service.GitHubService;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class GitHubController {

    private final GitHubService gitHubService;

    @GetMapping(value = "/{username}/repos", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GitHubRepoResponseDto> getRepos(@PathVariable String username) {
        return gitHubService.getUserRepositories(username);
    }
}
