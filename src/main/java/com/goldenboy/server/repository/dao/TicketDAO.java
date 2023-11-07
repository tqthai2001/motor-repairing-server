package com.goldenboy.server.repository.dao;

import com.goldenboy.server.annotations.com.goldenboy.server.entity.Ticket_;
import com.goldenboy.server.entity.Customer;
import com.goldenboy.server.entity.Ticket;
import com.goldenboy.server.payload.searchrequest.SearchCriteria;
import com.goldenboy.server.repository.consumer.TicketSearchQueryCriteriaConsumer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.Metamodel;
import java.util.List;

@Component
public class TicketDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Ticket> searchTicket(List<SearchCriteria> params) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        Metamodel m = entityManager.getMetamodel();
        CriteriaQuery<Ticket> cq = cb.createQuery(Ticket.class);
        Root<Ticket> root = cq.from(Ticket.class);
        Join<Ticket, Customer> nullableCustomer = root.join(Ticket_.CUSTOMER, JoinType.LEFT);
        Predicate predicate = cb.conjunction();
        TicketSearchQueryCriteriaConsumer searchConsumer =
                new TicketSearchQueryCriteriaConsumer(predicate, cb, root, nullableCustomer);
        params.forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();
        cq.where(predicate);
        return entityManager.createQuery(cq).getResultList();
    }

    public Page<Ticket> searchTicketPaging(List<SearchCriteria> params, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ticket> cq = cb.createQuery(Ticket.class);
        Root<Ticket> root = cq.from(Ticket.class);
        Predicate predicate = cb.conjunction();
        TicketSearchQueryCriteriaConsumer searchConsumer =
                new TicketSearchQueryCriteriaConsumer(predicate, cb, root, null);
        params.forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();
        cq.where(predicate);
        List<Ticket> result = entityManager.createQuery(cq)
                                           .setFirstResult((int) pageable.getOffset())
                                           .setMaxResults(pageable.getPageSize())
                                           .getResultList();
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Ticket> countRoot = countQuery.from(Ticket.class);
        countQuery.select(cb.count(countRoot)).where(predicate);
        Long count = entityManager.createQuery(countQuery).getSingleResult();
        return new PageImpl<>(result, pageable, count);
    }
}
