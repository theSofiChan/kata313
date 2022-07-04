package kata.crud.kata313.repositry;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kata.crud.kata313.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
