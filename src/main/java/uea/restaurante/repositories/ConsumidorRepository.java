package uea.restaurante.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uea.restaurante.models.Consumidor;
import uea.restaurante.repositories.consumidor.ConsumidorRepositoryQuery;

@Repository
public interface ConsumidorRepository extends JpaRepository<Consumidor, Long>, ConsumidorRepositoryQuery{

}
