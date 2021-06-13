package com.smartschool.schoolbackendproject.dto;

import com.smartschool.schoolbackendproject.model.Visit;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
public class VisitDto {
    private Long idUser;
    private String name;
    private String middleName;
    private String surname;
    private Timestamp dateOfArrival;
    private Timestamp dateOfLeave;

    /*public Visits toVisits(){
        Visits visits = new Visits();
        visits.setUser(user);
        visits.setDateOfArrival(dateOfArrival);
        visits.setDateOfLeave(dateOfLeave);
        return visits;
    }*/

    public static List<VisitDto> fromListVisits(List<Visit> visits) {
        List<VisitDto> visitDtoList = new ArrayList<>();

        for (Visit visit : visits) {
            visitDtoList.add(fromVisit(visit));
        }

        return visitDtoList;
    }

    public static VisitDto fromVisit(Visit visit) {
        VisitDto visitDto = new VisitDto();
        visitDto.setIdUser(visit.getUser().getId());
        visitDto.setName(visit.getUser().getName());
        visitDto.setMiddleName(visit.getUser().getMiddleName());
        visitDto.setSurname(visit.getUser().getSurname());
        visitDto.setDateOfArrival(visit.getDateOfArrival());
        visitDto.setDateOfLeave(visit.getDateOfLeave());
        return visitDto;
    }
}
