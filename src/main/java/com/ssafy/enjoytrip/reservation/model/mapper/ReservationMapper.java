package com.ssafy.enjoytrip.reservation.model.mapper;

import com.ssafy.enjoytrip.reservation.model.dto.request.ReservationSaveRequestDto;
import com.ssafy.enjoytrip.reservation.model.entity.Reservation;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {

    List<Reservation> findAllByMemberId(Long id);

    Optional<Reservation> findById(Long id);

    void save(ReservationSaveRequestDto reservationSaveRequestDto);

    void delete(Long id);
}
