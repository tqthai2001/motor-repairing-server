package com.goldenboy.server.repository.dao;

import com.goldenboy.server.entity.Employee;
import com.goldenboy.server.payload.searchrequest.SearchCriteria;
import com.goldenboy.server.repository.RoleRepository;
import com.goldenboy.server.repository.consumer.EmployeeSearchQueryCriteriaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class EmployeeDAO {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private RoleRepository roleRepository;

    public List<Employee> searchEmployee(List<SearchCriteria> params) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);
        Predicate predicate = cb.conjunction();
        EmployeeSearchQueryCriteriaConsumer searchConsumer =
                new EmployeeSearchQueryCriteriaConsumer(predicate, cb, root, roleRepository);
        params.stream().forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();
        cq.where(predicate);
        List<Employee> result = entityManager.createQuery(cq).getResultList();
        return result;
    }
}
