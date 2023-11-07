package com.goldenboy.server.repository;

import com.goldenboy.server.dto.reportDTO.*;
import com.goldenboy.server.entity.Customer;
import com.goldenboy.server.entity.Employee;
import com.goldenboy.server.entity.Motorbike;
import com.goldenboy.server.entity.Ticket;
import com.goldenboy.server.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends BaseRepository<Ticket, Long> {
    boolean existsByCode(String code);

    List<Ticket> findByRepairingEmployee(Employee repairingEmployee);

    List<Ticket> findByCustomer(Customer customer);

    List<Ticket> findByMotorbike(Motorbike motorbike);

    @Query("SELECT t FROM Ticket t WHERE t.active = true ORDER BY t.id DESC")
    Page<Ticket> findByActive(boolean active, Pageable pageable);

    @Query(value = "CALL select_revenue_by_date(:start_date, :end_date);", nativeQuery = true)
    List<RevenueDTO> selectRevenueByDate(@Param("start_date") String start_date, @Param("end_date") String end_date);

    @Query(value = "CALL select_revenue_by_month(:start_date, :end_date)", nativeQuery = true)
    List<RevenueDTO> selectRevenueByMonth(@Param("start_date") String start_date, @Param("end_date") String end_date);

    @Query(value = "CALL select_top_mechanic_by_date(:start_date, :end_date, :top)", nativeQuery = true)
    List<TopMechanicDTO> selectTopMechanicByDate(@Param("start_date") String start_date,
                                                 @Param("end_date") String end_date,
                                                 @Param("top") Integer top);

    @Query(value = "CALL select_mechanic_ticket(:start_date, :end_date)", nativeQuery = true)
    List<TopMechanicDTO> selectMechanicTicket(@Param("start_date") String start_date,
                                              @Param("end_date") String end_date);

    @Query(value = "CALL select_top_used_products_by_date(:start_date, :end_date, :top)", nativeQuery = true)
    List<TopUsedProductDTO> selectTopUsedProduct(@Param("start_date") String start_date,
                                                 @Param("end_date") String end_date,
                                                 @Param("top") Integer top);

    @Query(value = "CALL select_top_used_services_by_date(:start_date, :end_date, :top)", nativeQuery = true)
    List<TopUsedServiceDTO> selectTopUsedService(@Param("start_date") String start_date,
                                                 @Param("end_date") String end_date,
                                                 @Param("top") Integer top);

    @Query(value = "CALL select_top_customer(:top)", nativeQuery = true)
    List<TopCustomerDTO> selectTopCustomer(@Param("top") Integer top);

    @Query(value = "CALL select_new_customer_by_date(:start_date, :end_date)", nativeQuery = true)
    List<CountNewCustomerDTO> selectNewCustomerByDate(@Param("start_date") String start_date,
                                                      @Param("end_date") String end_date);

    @Query(value = "CALL select_new_customer_by_month(:start_date, :end_date)", nativeQuery = true)
    List<CountNewCustomerDTO> selectNewCustomerByMonth(@Param("start_date") String start_date,
                                                       @Param("end_date") String end_date);
}
