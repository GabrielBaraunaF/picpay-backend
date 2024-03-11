package picpay.repository;

import org.springframework.stereotype.Repository;
import picpay.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {
}
