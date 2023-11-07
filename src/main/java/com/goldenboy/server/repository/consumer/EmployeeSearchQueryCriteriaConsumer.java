package com.goldenboy.server.repository.consumer;

import com.goldenboy.server.common.ERole;
import com.goldenboy.server.entity.Employee;
import com.goldenboy.server.entity.Role;
import com.goldenboy.server.exception.EntityNotFoundException;
import com.goldenboy.server.payload.searchrequest.SearchCriteria;
import com.goldenboy.server.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeSearchQueryCriteriaConsumer implements Consumer<SearchCriteria> {
    private Predicate predicate;
    private CriteriaBuilder criteriaBuilder;
    private Root<Employee> root;
    private RoleRepository roleRepository;

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
            if (param.getKey().equalsIgnoreCase(ConstKeywords.KEYWORD_SEARCH)) {
                Predicate keywordFilter = criteriaBuilder.disjunction();
                keywordFilter = criteriaBuilder.or(keywordFilter, criteriaBuilder.like(root.get("code"),
                                                                                       "%" + param.getValue() + "%"));
                keywordFilter = criteriaBuilder.or(keywordFilter, criteriaBuilder.like(root.get("name"),
                                                                                       "%" + param.getValue() + "%"));
                keywordFilter = criteriaBuilder.or(keywordFilter, criteriaBuilder.like(root.get("phone"),
                                                                                       "%" + param.getValue() + "%"));
                keywordFilter = criteriaBuilder.or(keywordFilter, criteriaBuilder.like(root.get("address"),
                                                                                       "%" + param.getValue() + "%"));
                predicate = criteriaBuilder.and(predicate, keywordFilter);
            } else if (root.get(param.getKey()).getJavaType() == Boolean.class) {
                predicate =
                        criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get(param.getKey()),
                                                                             Boolean.valueOf(param.getValue()
                                                                                                                            .toString())));
            } else if (root.get(param.getKey()).getJavaType() == String.class) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(param.getKey()),
                                                                                "%" + param.getValue() + "%"));
            } else {
                predicate =
                        criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get(param.getKey()),
                                                                             param.getValue()));
            }
        } else if (param.getOperation().equalsIgnoreCase(":")) {
            Pattern pattern = Pattern.compile("(\\w*?),");
            Matcher matcher = pattern.matcher(param.getValue().toString() + ",");
            Predicate orPredicate = criteriaBuilder.disjunction();
            while (matcher.find()) {
                String roleName = matcher.group(1);
                Role role;
                switch (roleName) {
                    case "moderator":
                        role = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                             .orElseThrow(() -> new EntityNotFoundException(Role.class, "role",
                                                                                            ERole.ROLE_MODERATOR.name()));
                        break;
                    case "mechanic":
                        role = roleRepository.findByName(ERole.ROLE_MECHANIC)
                                             .orElseThrow(() -> new EntityNotFoundException(Role.class, "role",
                                                                                            ERole.ROLE_MECHANIC.name()));
                        break;
                    case "admin":
                        role = roleRepository.findByName(ERole.ROLE_ADMIN)
                                             .orElseThrow(() -> new EntityNotFoundException(Role.class, "role",
                                                                                            ERole.ROLE_ADMIN.name()));
                        break;
                    case "cashier":
                        role = roleRepository.findByName(ERole.ROLE_CASHIER)
                                             .orElseThrow(() -> new EntityNotFoundException(Role.class, "role",
                                                                                            ERole.ROLE_CASHIER.name()));
                        break;
                    default:
                        throw new EntityNotFoundException(Role.class, "roleName", roleName);
                }
                orPredicate = criteriaBuilder.or(orPredicate, criteriaBuilder.isMember(role, root.get("roles")));
            }
            predicate = criteriaBuilder.and(predicate, orPredicate);
        }
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.isTrue(root.get("active")));
    }
}
