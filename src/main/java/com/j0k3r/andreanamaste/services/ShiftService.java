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
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    public List<ShiftResponse> getAllShifts(){
        return shiftRepository.findAll().stream().map(ShiftUtils::toShiftResponse).toList();
    }

    public List<ShiftResponse> getShiftsByDateFree(LocalDate date){
        return shiftRepository.findByDateAndIsBooked(date,false).stream().map(ShiftUtils::toShiftResponse).toList();
    }

    @Transactional
    public String createShiftRange(ShiftCreateRange shiftCreateRange) {
        for(int i = shiftCreateRange.getStart(); i < shiftCreateRange.getEnd(); i++){
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

}
