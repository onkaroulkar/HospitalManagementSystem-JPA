package com.onkar.hospitalManagement.dto;

import com.onkar.hospitalManagement.type.BloodGroupType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BloodGroupCountResponseEntity {

    private BloodGroupType bloodGroupType;
    private Long count;

    public BloodGroupCountResponseEntity(BloodGroupType bloodGroupType, Long count){
        this.bloodGroupType = bloodGroupType;
        this.count = count;
    }

    @Override
    public String toString() {
        return "BloodGroupCountResponseEntity{" +
                "bloodGroupType=" + bloodGroupType +
                ", count=" + count +
                '}';
    }
}
