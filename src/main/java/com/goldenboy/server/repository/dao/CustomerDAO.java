package com.goldenboy.server.repository.dao;

import com.goldenboy.server.entity.Customer;
import com.goldenboy.server.payload.searchrequest.SearchCriteria;
import com.goldenboy.server.repository.consumer.CustomerSearchQueryCriteriaConsumer;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class CustomerDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Customer> searchCustomer(List<SearchCriteria> params) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> root = cq.from(Customer.class);
        Predicate predicate = cb.conjunction();
        CustomerSearchQueryCriteriaConsumer searchConsumer =
                new CustomerSearchQueryCriteriaConsumer(predicate, cb, root);
        params.stream().forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();
        cq.where(predicate);
        List<Customer> result = entityManager.createQuery(cq).getResultList();
        return result;
    }
}
