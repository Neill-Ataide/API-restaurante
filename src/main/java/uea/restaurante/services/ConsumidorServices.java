package uea.restaurante.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uea.restaurante.dto.ResumoConsumidorDto;
import uea.restaurante.models.Consumidor;
import uea.restaurante.repositories.ConsumidorRepository;
import uea.restaurante.repositories.filters.ConsumidorFilter;

@Service
public class ConsumidorServices {
	
	@Autowired
	private ConsumidorRepository consumidorRepository;
	
	public List<ResumoConsumidorDto> resumir (ConsumidorFilter consumidorFilter){
		return consumidorRepository.filtrar(consumidorFilter);
	}
	
	public Consumidor criar(Consumidor consumidor) {
		return consumidorRepository.save(consumidor);
		
	}
	public List<Consumidor>listar(){
		return consumidorRepository.findAll();
	}
	public Consumidor buscarPorCodigo(Long codigo) {
		Consumidor consumidor = consumidorRepository.findById(codigo).orElseThrow();
		return consumidor;
				
	}
	public void excluir (Long codigo) {
		consumidorRepository.deleteById(codigo);
	}
	public Consumidor atualizar(Long codigo, Consumidor consumidor) {
		Consumidor consumidorSalva = consumidorRepository.findById(codigo).orElseThrow();
		BeanUtils.copyProperties(consumidor, consumidorSalva, "codigo");
		return consumidorRepository.save(consumidorSalva);
				
	}
}
