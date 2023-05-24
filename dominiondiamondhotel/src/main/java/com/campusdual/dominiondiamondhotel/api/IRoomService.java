package com.campusdual.dominiondiamondhotel.api;

import com.campusdual.dominiondiamondhotel.model.State;
import com.campusdual.dominiondiamondhotel.model.dto.HotelDto;
import com.campusdual.dominiondiamondhotel.model.dto.RoomDto;
import com.campusdual.dominiondiamondhotel.model.dto.StateDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRoomService {
    int insertRoom(RoomDto roomDto);
    List<RoomDto> queryAll();
    ResponseEntity<?> updateRoom(RoomDto roomDto);
    ResponseEntity<?> manageGetRoom(int id);
    ResponseEntity<?> getRoomsFromHotel(int id);
    ResponseEntity<?> updateRoomState(RoomDto roomDto);
}
