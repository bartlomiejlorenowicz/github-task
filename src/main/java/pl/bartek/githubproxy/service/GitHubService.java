package pl.bartek.githubproxy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bartek.githubproxy.client.GithubClient;
import pl.bartek.githubproxy.dto.response.GitHubRepoResponseDto;
import pl.bartek.githubproxy.mapper.Mapper;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GitHubService {

    private final GithubClient githubClient;
    private final Mapper mapper;

    public List<GitHubRepoResponseDto> getUserRepositories(String username) {
        return githubClient.getUserRepositories(username)
                .filter(repo -> !repo.fork())
                .flatMap(repo ->
                        githubClient.getBranches(username, repo.name())
                            .collectList()
                            .map(branches -> mapper.mapToGitHubRepoResponseDto(repo, branches)))
                .collectList()
                .block();
    }
}
