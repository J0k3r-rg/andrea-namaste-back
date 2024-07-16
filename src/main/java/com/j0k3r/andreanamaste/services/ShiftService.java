package com.j0k3r.andreanamaste.services;

import com.j0k3r.andreanamaste.exceptions.ShiftException;
import com.j0k3r.andreanamaste.http.request.ShiftCreateRange;
import com.j0k3r.andreanamaste.http.request.ShiftRequest;
import com.j0k3r.andreanamaste.http.response.ShiftResponse;
import com.j0k3r.andreanamaste.models.Shift;
import com.j0k3r.andreanamaste.repositories.ShiftRepository;
import com.j0k3r.andreanamaste.utils.ShiftUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    public Page<ShiftResponse> getAllShifts(Pageable pageable){
        return shiftRepository.findAll(pageable).map(ShiftUtils::toShiftResponse);
    }

    public Page<ShiftResponse> getAllShifts(Boolean booked, Pageable pageable){
        return shiftRepository.findByIsBooked(booked, pageable).map(ShiftUtils::toShiftResponse);
    }

    public Page<ShiftResponse> getAllShifts(LocalDate date, Pageable pageable){
        return shiftRepository.findByDate(date,pageable).map(ShiftUtils::toShiftResponse);
    }

    public Page<ShiftResponse> getAllShifts(LocalDate date, Boolean isBooked, Pageable pageable){
        return shiftRepository.findByDateAndIsBooked(date,isBooked,pageable).map(ShiftUtils::toShiftResponse);
    }

    public List<ShiftResponse> getShiftsByDateFree(LocalDate date){
        return shiftRepository.findByDateAndIsBooked(date,false).stream().map(ShiftUtils::toShiftResponse).toList();
    }

    @Transactional
    public String createShiftRange(ShiftCreateRange shiftCreateRange) {
        for(int i = shiftCreateRange.getStart(); i < shiftCreateRange.getEnd()+1; i++){
            if(shiftRepository.findByDateAndHour(shiftCreateRange.getDate(), LocalTime.of(i, 0)).isPresent())
                continue;
            shiftRepository.save(
                    Shift.builder()
                            .date(shiftCreateRange.getDate())
                            .hour(LocalTime.of(i, 0))
                            .build()
            );
        }
        return "Turnos creados correctamente";
    }

    public ShiftResponse createShit(ShiftRequest shiftRequest) throws ShiftException {
        if(shiftRepository.findByDateAndHour(shiftRequest.getDate(), shiftRequest.getHour()).isPresent())
            throw new ShiftException("El turno ya se encuentra creado",400);
         Shift shift = shiftRepository.save(
                Shift.builder()
                        .date(shiftRequest.getDate())
                        .hour(shiftRequest.getHour())
                        .build()
        );
         return ShiftUtils.toShiftResponse(shift);
    }

    @Transactional
    public void deleteShift(String id) throws ShiftException {
        if (!shiftRepository.existsById(id))
            throw new ShiftException("El turno no existe", 400);
        if (shiftRepository.findById(id).get().getIsBooked())
            throw new ShiftException("El turno ya se encuentra reservado", 400);
        shiftRepository.deleteById(id);
    }

    @Transactional
    public ShiftResponse updateShift(String id, ShiftRequest shiftRequest) throws ShiftException {
        if(shiftRepository.findByDateAndHour(shiftRequest.getDate(), shiftRequest.getHour()).isPresent())
            throw new ShiftException("El turno ya se encuentra creado",400);
        Shift shift = shiftRepository.findById(id).orElse(null);
        if (shift == null)
            throw new ShiftException("El turno no existe", 400);
        shift.setDate(shiftRequest.getDate());
        shift.setHour(shiftRequest.getHour());
        return ShiftUtils.toShiftResponse(shiftRepository.save(shift));
    }

    public ShiftResponse getShiftById(String id) throws ShiftException {
        Shift shift = shiftRepository.findById(id).orElse(null);
        if (shift == null)
            throw new ShiftException("El turno no existe", 400);
        return ShiftUtils.toShiftResponse(shift);
    }
}
