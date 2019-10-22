package AI.dtapijava.Repositories;

import AI.dtapijava.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<User, Integer> {
}
