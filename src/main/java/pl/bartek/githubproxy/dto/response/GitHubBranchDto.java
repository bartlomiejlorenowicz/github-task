package pl.bartek.githubproxy.dto.response;

public record GitHubBranchDto (
        String branchName,
        String sha
) {}
