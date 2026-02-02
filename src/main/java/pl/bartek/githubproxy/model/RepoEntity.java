package pl.bartek.githubproxy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "repo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RepoEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String owner;

    @Setter
    @Column(nullable = false)
    private String name;

    public RepoEntity(String owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    public Long getId() { return id; }
    public String getOwner() { return owner; }
    public String getName() { return name; }

}
