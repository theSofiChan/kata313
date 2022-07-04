package kata.crud.kata313.repositry;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kata.crud.kata313.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}


