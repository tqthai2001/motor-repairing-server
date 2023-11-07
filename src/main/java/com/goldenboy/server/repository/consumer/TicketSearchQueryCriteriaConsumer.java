package com.goldenboy.server.repository.consumer;

import com.goldenboy.server.common.DateConverter;
import com.goldenboy.server.entity.Customer;
import com.goldenboy.server.entity.Motorbike;
import com.goldenboy.server.entity.Ticket;
import com.goldenboy.server.payload.searchrequest.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.function.Consumer;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TicketSearchQueryCriteriaConsumer implements Consumer<SearchCriteria> {
    private Predicate predicate;
    private CriteriaBuilder criteriaBuilder;
    private Root<Ticket> root;
    private Join<Ticket, Customer> join;

    @Override
    public void accept(SearchCriteria param) {
        if (param.getOperation().equalsIgnoreCase(">=")) {
            predicate =
                    criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("updatedDate"),
                                                                                        DateConverter.toLocalDateTime(param.getValue()
                                                                                                                                                    .toString())));
        } else if (param.getOperation().equalsIgnoreCase("<=")) {
            predicate =
                    criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("updatedDate"),
                                                                                     DateConverter.toLocalDateTime(param.getValue()
                                                                                                                                                 .toString())
                                                                                                                           .plusDays(1)));
        } else if (param.getOperation().equalsIgnoreCase("==")) {
            switch (param.getKey()) {
                case ConstKeywords.KEYWORD_SEARCH:
                    Predicate keywordFilter = criteriaBuilder.disjunction();
                    keywordFilter = criteriaBuilder.or(keywordFilter, criteriaBuilder.like(root.get("code"),
                                                                                           "%" + param.getValue() +
                                                                                           "%"));
                    keywordFilter =
                            criteriaBuilder.or(keywordFilter, criteriaBuilder.like(root.<Motorbike> get("motorbike")
                                                                                       .get("licensePlates"),
                                                                                   "%" + param.getValue() + "%"));
                    keywordFilter = criteriaBuilder.or(keywordFilter, criteriaBuilder.like(join.get("phone"),
                                                                                           "%" + param.getValue() +
                                                                                           "%"));
                    predicate = criteriaBuilder.and(predicate, keywordFilter);
                    break;
                case "motorbike":
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.<Motorbike> get(param.getKey())
                                                                                        .get("licensePlates"),
                                                                                    "%" + param.getValue() + "%"));
                    break;
                case "status":
                    Byte reqStatus = Byte.valueOf(param.getValue().toString());
                    predicate =
                            criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get(param.getKey()), reqStatus));
                default:
                    break;
            }
        }
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.isTrue(root.get("active")));
    }
}
