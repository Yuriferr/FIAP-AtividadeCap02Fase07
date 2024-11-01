package repository;

import model.Coleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ColetaRepository extends JpaRepository<Coleta,Long> {

    @Query("SELECT c FROM Coleta c WHERE c.endereco = :endereco")
    public Optional<Coleta> buscarPeloEndereco(@Param("nome") String endereco);
}
