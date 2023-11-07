package com.goldenboy.server.service;

import com.goldenboy.server.entity.Motorbike;
import com.goldenboy.server.entity.Ticket;
import com.goldenboy.server.payload.searchrequest.SearchCriteria;
import com.goldenboy.server.service.base.BaseService;

import java.util.List;

public interface MotorbikeService extends BaseService<Motorbike> {
    List<Motorbike> searchMotorbike(List<SearchCriteria> params);

    List<Ticket> findAllTicket(Long motorbikeId);
}
