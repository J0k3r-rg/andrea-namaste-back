package com.j0k3r.andreanamaste.utils;

import com.j0k3r.andreanamaste.http.request.ShiftRequest;
import com.j0k3r.andreanamaste.http.response.ShiftResponse;
import com.j0k3r.andreanamaste.models.Shift;
import org.springframework.stereotype.Component;

@Component
public class ShiftUtils {

    public static ShiftResponse toShiftResponse(Shift shift){
        return ShiftResponse.builder()
                .id(shift.getId())
                .date(shift.getDate())
                .hour(shift.getHour())
                .product(shift.getProduct() != null ? ProductUtils.toProductResponse(shift.getProduct()) : null)
                .build();
    }

    public static Shift toShift(ShiftRequest shiftRequest){
        return Shift.builder()
                .date(shiftRequest.getDate())
                .hour(shiftRequest.getHour())
                .product(shiftRequest.getIdProduct() != null ? ProductUtils.toProductById(shiftRequest.getIdProduct()) : null)
                .build();
    }

}
