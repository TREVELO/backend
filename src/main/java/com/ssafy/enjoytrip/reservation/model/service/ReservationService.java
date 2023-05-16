package com.ssafy.enjoytrip.reservation.model.service;

import com.ssafy.enjoytrip.reservation.model.dto.ReservationResponseDto;
import java.util.List;

public interface ReservationService {

    List<ReservationResponseDto> findAllByMemberId(Long id);
}
