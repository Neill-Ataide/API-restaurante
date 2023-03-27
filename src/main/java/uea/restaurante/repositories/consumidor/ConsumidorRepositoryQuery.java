package uea.restaurante.repositories.consumidor;

import java.util.List;

import uea.restaurante.dto.ResumoConsumidorDto;
import uea.restaurante.repositories.filters.ConsumidorFilter;

public interface ConsumidorRepositoryQuery {
	public List<ResumoConsumidorDto> filtrar(ConsumidorFilter consumidorFilter);
}
