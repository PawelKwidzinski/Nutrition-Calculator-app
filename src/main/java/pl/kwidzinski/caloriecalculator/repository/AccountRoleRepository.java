package pl.kwidzinski.caloriecalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kwidzinski.caloriecalculator.model.AccountRole;

import java.util.Optional;

@Repository
public interface AccountRoleRepository extends JpaRepository<AccountRole, Long> {
    boolean existsByName(String name);

    Optional<AccountRole> findByName(String name);
}
