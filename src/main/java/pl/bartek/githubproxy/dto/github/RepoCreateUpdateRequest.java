package pl.bartek.githubproxy.dto.github;

import jakarta.validation.constraints.NotBlank;

public record RepoCreateUpdateRequest(
        @NotBlank String owner,
        @NotBlank String name
) {}
