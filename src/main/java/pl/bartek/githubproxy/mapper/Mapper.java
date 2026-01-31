package pl.bartek.githubproxy.mapper;

import org.springframework.stereotype.Component;
import pl.bartek.githubproxy.dto.github.GitHubBranch;
import pl.bartek.githubproxy.dto.github.GitHubRepo;
import pl.bartek.githubproxy.dto.response.GitHubBranchDto;
import pl.bartek.githubproxy.dto.response.GitHubRepoResponseDto;

import java.util.List;

@Component
public class Mapper {

    public GitHubRepoResponseDto mapToGitHubRepoResponseDto(GitHubRepo gitHubRepo, List<GitHubBranch> branches) {

        List<GitHubBranchDto> branchDtos = branches.stream()
                .map(branch -> new GitHubBranchDto(
                        branch.name(),
                        branch.commit().sha()
                ))
                .toList();

        return new GitHubRepoResponseDto(
                gitHubRepo.name(),
                gitHubRepo.owner().login(),
                branchDtos
        );
    }
}
