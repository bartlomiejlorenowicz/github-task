package pl.bartek.githubproxy.dto.github;

public record GitHubRepo(
        String name,
        Owner owner,
        boolean fork
) {
}
