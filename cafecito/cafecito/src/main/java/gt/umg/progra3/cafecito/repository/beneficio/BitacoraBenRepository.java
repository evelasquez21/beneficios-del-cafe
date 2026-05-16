package gt.umg.progra3.cafecito.repository.beneficio;

import gt.umg.progra3.cafecito.model.beneficio.BitacoraBeneficio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BitacoraBenRepository extends JpaRepository<BitacoraBeneficio, Long> {
}
