package com.goldenboy.server.repository.consumer;

import com.goldenboy.server.entity.Brand;
import com.goldenboy.server.entity.Model;
import com.goldenboy.server.payload.searchrequest.SearchCriteria;
import com.goldenboy.server.repository.BrandRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.function.Consumer;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ModelSearchQueryCriteriaConsumer implements Consumer<SearchCriteria> {
    private Predicate predicate;
    private CriteriaBuilder criteriaBuilder;
    private Root<Model> root;
    private BrandRepository brandRepository;

    @Override
    public void accept(SearchCriteria param) {
        if (param.getOperation().equalsIgnoreCase(">=")) {
            predicate =
                    criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get(param.getKey()),
                                                                                        param.getValue()
                                                                                                                       .toString()));
        } else if (param.getOperation().equalsIgnoreCase("<=")) {
            predicate =
                    criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get(param.getKey()),
                                                                                     param.getValue()
                                                                                                                    .toString()));
        } else if (param.getOperation().equalsIgnoreCase("==")) {
            String brandName = param.getValue().toString();
            Brand brand = brandRepository.findByBrandName(brandName).get();
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get(param.getKey()), brand));
        }
    }
}
