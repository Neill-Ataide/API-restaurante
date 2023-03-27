package uea.restaurante.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import uea.restaurante.dto.ResumoConsumidorDto;
import uea.restaurante.models.Consumidor;
import uea.restaurante.repositories.filters.ConsumidorFilter;
import uea.restaurante.services.ConsumidorServices;

@RestController
@RequestMapping("/consumidores")
public class ConsumidorResources {

	@Autowired
	private ConsumidorServices consumidorServices;

	@PostMapping
	public ResponseEntity<Consumidor> criar(@RequestBody Consumidor consumidor) {
		Consumidor consumidorSalva = consumidorServices.criar(consumidor);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}")
				.buildAndExpand(consumidorSalva.getCodigo()).toUri();

		return ResponseEntity.created(uri).body(consumidorSalva);
	}

	@GetMapping
	public ResponseEntity<List<ResumoConsumidorDto>> listar(ConsumidorFilter consumidorFilter) {
		List<ResumoConsumidorDto> resumos = consumidorServices.resumir(consumidorFilter);
		return ResponseEntity.ok().body(resumos);
	}

	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Consumidor> buscarPorCodigo(@PathVariable Long codigo) {
		Consumidor consumidores = consumidorServices.buscarPorCodigo(codigo);
		return ResponseEntity.ok().body(consumidores);
	}

	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<Void> excluir(@PathVariable Long codigo) {
		consumidorServices.excluir(codigo);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{codigo}")
	public ResponseEntity<Consumidor> atualizar(@PathVariable Long codigo,@RequestBody Consumidor consumidor) {
		Consumidor consumidorSalva = consumidorServices.atualizar(codigo, consumidor);
		return ResponseEntity.ok().body(consumidorSalva);

	}

}
