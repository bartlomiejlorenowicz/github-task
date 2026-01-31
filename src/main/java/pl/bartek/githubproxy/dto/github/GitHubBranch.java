package pl.bartek.githubproxy.dto.github;

public record GitHubBranch(
        String name,
        Commit commit
) {
}
