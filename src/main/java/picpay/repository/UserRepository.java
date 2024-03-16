package picpay.repository;

import org.springframework.stereotype.Repository;
import picpay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
    User findByCpf(String cpf);
    User findByAccountPix(String PIX);
}
