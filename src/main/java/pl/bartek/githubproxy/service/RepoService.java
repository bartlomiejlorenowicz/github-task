package pl.bartek.githubproxy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.bartek.githubproxy.dto.github.RepoCreateUpdateRequest;
import pl.bartek.githubproxy.dto.response.RepoResponse;
import pl.bartek.githubproxy.exception.RepoNotFoundException;
import pl.bartek.githubproxy.mapper.Mapper;
import pl.bartek.githubproxy.model.RepoEntity;
import pl.bartek.githubproxy.repository.RepoRepository;

@Service
@RequiredArgsConstructor
public class RepoService {

    private final RepoRepository repoRepository;
    private final Mapper mapper;

    public RepoResponse create(RepoCreateUpdateRequest req) {
        RepoEntity saved = repoRepository.save(new RepoEntity(req.owner(), req.name()));
        return mapper.toResponse(saved);
    }

    public RepoResponse getById(Long id) {
        RepoEntity entity = repoRepository.findById(id)
                .orElseThrow(() -> new RepoNotFoundException("Repo with id " + id + " not found"));
        return mapper.toResponse(entity);
    }

    public Page<RepoResponse> getAll(Pageable pageable) {
        return repoRepository.findAll(pageable).map(mapper::toResponse);
    }

    public Page<RepoResponse> getByOwner(String owner, Pageable pageable) {
        return repoRepository.findByOwner(owner, pageable).map(mapper::toResponse);
    }

    public RepoResponse update(Long id, RepoCreateUpdateRequest req) {
        RepoEntity entity = repoRepository.findById(id)
                .orElseThrow(() -> new RepoNotFoundException("Repo with id " + id + " not found"));

        entity.setOwner(req.owner());
        entity.setName(req.name());

        RepoEntity saved = repoRepository.save(entity);
        return mapper.toResponse(saved);
    }

    public void delete(Long id) {
        if (!repoRepository.existsById(id)) {
            throw new RepoNotFoundException("Repo with id " + id + " not found");
        }
        repoRepository.deleteById(id);
    }
}
