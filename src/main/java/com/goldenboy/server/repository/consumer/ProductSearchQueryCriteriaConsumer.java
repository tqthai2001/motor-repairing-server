package com.goldenboy.server.repository.consumer;

import com.goldenboy.server.entity.Brand;
import com.goldenboy.server.entity.Category;
import com.goldenboy.server.entity.Model;
import com.goldenboy.server.entity.Product;
import com.goldenboy.server.payload.searchrequest.SearchCriteria;
import com.goldenboy.server.service.BrandService;
import com.goldenboy.server.service.CategoryService;
import com.goldenboy.server.service.ModelService;
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
public class ProductSearchQueryCriteriaConsumer implements Consumer<SearchCriteria> {
    private Predicate predicate;
    private CriteriaBuilder criteriaBuilder;
    private Root<Product> root;
    private CategoryService categoryService;
    private ModelService modelService;
    private BrandService brandService;

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
            switch (param.getKey()) {
                case ConstKeywords.KEYWORD_SEARCH:
                    Predicate keywordFilter = criteriaBuilder.disjunction();
                    keywordFilter = criteriaBuilder.or(keywordFilter, criteriaBuilder.like(root.get("code"),
                                                                                           "%" + param.getValue() +
                                                                                           "%"));
                    keywordFilter = criteriaBuilder.or(keywordFilter, criteriaBuilder.like(root.get("name"),
                                                                                           "%" + param.getValue() +
                                                                                           "%"));
                    predicate = criteriaBuilder.and(predicate, keywordFilter);
                    break;
                case "brand":
                    Long brandId = Long.parseLong(param.getValue().toString());
                    Brand brand = brandService.findById(brandId);
                    Predicate orPredicate = criteriaBuilder.disjunction();
                    for (var model : brand.getModels()) {
                        orPredicate =
                                criteriaBuilder.or(orPredicate, criteriaBuilder.isMember(model, root.get("models")));
                    }
                    predicate = criteriaBuilder.and(predicate, orPredicate);
                    break;
                case "category":
                    Long categoryId = Long.parseLong(param.getValue().toString());
                    Category category = categoryService.findById(categoryId);
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("category"), category));
                    break;
                case "name":
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(param.getKey()),
                                                                                    "%" + param.getValue() + "%"));
                default:
                    break;
            }
        } else if (param.getOperation().equalsIgnoreCase(":")) {
            Long modelId = Long.parseLong(param.getValue().toString());
            Model model = modelService.findById(modelId);
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.isMember(model, root.get("models")));
        }
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.isTrue(root.get("active")));
    }
}
