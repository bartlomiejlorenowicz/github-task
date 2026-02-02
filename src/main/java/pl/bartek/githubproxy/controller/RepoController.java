package pl.bartek.githubproxy.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.bartek.githubproxy.dto.github.RepoCreateUpdateRequest;
import pl.bartek.githubproxy.dto.response.RepoResponse;
import pl.bartek.githubproxy.service.RepoService;

@RestController
@RequestMapping("/api/repos")
@RequiredArgsConstructor
public class RepoController {

    private final RepoService repoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RepoResponse create(@Valid @RequestBody RepoCreateUpdateRequest req) {
        return repoService.create(req);
    }

    @GetMapping("/{id}")
    public RepoResponse getById(@PathVariable Long id) {
        return repoService.getById(id);
    }

    @GetMapping
    public Page<RepoResponse> getAll(
            @PageableDefault(size = 10, sort = "id") Pageable pageable,
            @RequestParam(required = false) String owner
    ) {
        if (owner != null && !owner.isBlank()) {
            return repoService.getByOwner(owner, pageable);
        }
        return repoService.getAll(pageable);
    }

    @PutMapping("/{id}")
    public RepoResponse update(@PathVariable Long id, @Valid @RequestBody RepoCreateUpdateRequest req) {
        return repoService.update(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        repoService.delete(id);
    }
}
