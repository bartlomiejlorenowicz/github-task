package pl.bartek.githubproxy.dto.response;

public record RepoResponse(
        Long id,
        String owner,
        String name
) {}
