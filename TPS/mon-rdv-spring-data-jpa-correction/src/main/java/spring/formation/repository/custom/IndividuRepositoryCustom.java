package spring.formation.repository.custom;

import java.util.List;
import java.util.Set;

import spring.formation.model.Individu;

public interface IndividuRepositoryCustom {
	List<Individu> findAllByEmails(Set<String> emails);
}
