package pl.bartek.githubproxy.dto.response;

import java.util.List;

public record GitHubRepoResponseDto(
    String name,
    String ownerLogin,
    List<GitHubBranchDto> branches
) {}
