package spring.formation.repository.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import spring.formation.model.Individu;

public class IndividuRepositoryCustomImpl implements IndividuRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Individu> findAllByEmails(Set<String> emails) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Individu> query = cb.createQuery(Individu.class); // Query<Individu>
        Root<Individu> i = query.from(Individu.class); // from Individu i

        Path<String> emailPath = i.get("email"); // i.email

        List<Predicate> predicates = new ArrayList<>();
        for (String email : emails) {
            predicates.add(cb.equal(emailPath, email)); // i.email= :email[0]
        }
        query.select(i) // select i
            .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));  // where i.email = :email[0] or i.email = :email[1]

        return entityManager.createQuery(query)
            .getResultList();
	}

}
