package pl.bartek.githubproxy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.bartek.githubproxy.model.RepoEntity;

public interface RepoRepository extends JpaRepository<RepoEntity, Long> {

    Page<RepoEntity> findByOwner(String owner, Pageable pageable);
}