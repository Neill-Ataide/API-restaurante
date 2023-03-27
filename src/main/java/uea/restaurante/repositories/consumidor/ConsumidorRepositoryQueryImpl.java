package uea.restaurante.repositories.consumidor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ObjectUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import uea.restaurante.dto.ResumoConsumidorDto;
import uea.restaurante.models.Consumidor;
import uea.restaurante.repositories.filters.ConsumidorFilter;

public class ConsumidorRepositoryQueryImpl implements ConsumidorRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<ResumoConsumidorDto> filtrar(ConsumidorFilter consumidorFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();

		CriteriaQuery<ResumoConsumidorDto> criteria = builder.createQuery(ResumoConsumidorDto.class);
		Root<Consumidor> root = criteria.from(Consumidor.class);

		criteria.select(builder.construct(ResumoConsumidorDto.class, root.get("codigo"), root.get("nome"),
				root.get("dataNascimento")));

		Predicate[] predicates = criarRestricoes(consumidorFilter, builder, root);
		if (predicates.length > 0) {
			criteria.where(predicates);
		}

		List<ResumoConsumidorDto> returnList = manager.createQuery(criteria).getResultList();
		return returnList;
	}

	private Predicate[] criarRestricoes(ConsumidorFilter consumidorFilter, CriteriaBuilder builder,
			Root<Consumidor> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!ObjectUtils.isEmpty(consumidorFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get("nome")),
					"%" + consumidorFilter.getNome().toLowerCase() + "%"));
		}

		if (consumidorFilter.getDataNascimento() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get("dataNascimento"), consumidorFilter.getDataNascimento()));
		}

		if (consumidorFilter.getCpf() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("cpf"), consumidorFilter.getCpf()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}
}
