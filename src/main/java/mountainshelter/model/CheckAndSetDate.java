package mountainshelter.model;

import mountainshelter.repository.DateRepository;
import mountainshelter.repository.RoomRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CheckAndSetDate {
    List<Room> roomsFromDataBase;

    public boolean ifRoomsAvailable(Date wantedDate, List<Room> roomList, RoomRepository roomRepository, DateRepository dateRepository) {
        roomsFromDataBase = new ArrayList<>();
        long[] roomsId = getRoomsId(roomList);
        Arrays.stream(roomsId).forEach(id -> roomsFromDataBase.add(roomRepository.getOne(id)));
        List<Date> dateList = new ArrayList<>();
        roomsFromDataBase.forEach(room -> dateList.addAll(room.getDates()));
        for (Date date : dateList) {
            if (date.getStartOfUnavailableTerm().isAfter(wantedDate.getStartOfUnavailableTerm())) {
                if (!wantedDate.getEndOfUnavailableTerm().isBefore(date.getStartOfUnavailableTerm())) {
                    return false;
                }
            } else {
                if (!wantedDate.getStartOfUnavailableTerm().isAfter(date.getEndOfUnavailableTerm())) {
                    return false;
                }
            }
        }
        setDatesOnRooms(roomRepository, wantedDate, dateRepository);
        return true;
    }

    public void setDatesOnRooms(RoomRepository roomRepository, Date wantedDate, DateRepository dateRepository) {
        dateRepository.save(wantedDate);
        roomsFromDataBase.forEach(room -> {
            room.addDate(wantedDate);
            roomRepository.save(room);
        });
    }

    public long[] getRoomsId(List<Room> rooms) {
        return rooms.stream()
                .mapToLong(Room::getId)
                .toArray();
    }


}
