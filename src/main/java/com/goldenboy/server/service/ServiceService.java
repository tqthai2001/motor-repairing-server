package com.goldenboy.server.service;

import com.goldenboy.server.entity.Service;
import com.goldenboy.server.payload.searchrequest.SearchCriteria;
import com.goldenboy.server.service.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ServiceService extends BaseService<Service> {
    // DELETE
    @Transactional
    void deleteByIdTmp(Long serviceId);

    @Transactional
    void deleteByIdArrayTmp(List<Long> serviceIds);

    List<Service> searchService(List<SearchCriteria> params);
}
