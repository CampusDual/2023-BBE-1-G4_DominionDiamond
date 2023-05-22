package com.campusdual.dominiondiamondhotel.controller;

import com.campusdual.dominiondiamondhotel.api.IRoomService;
import com.campusdual.dominiondiamondhotel.model.dto.HotelDto;
import com.campusdual.dominiondiamondhotel.model.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private IRoomService roomService;

    @PostMapping(value = "/add")
    public int addRoom(@RequestBody RoomDto roomDto) {
        return roomService.insertRoom(roomDto);
    }

    @GetMapping(value = "/getAll")
    public List<RoomDto> getAllRooms() {
        return roomService.queryAll();
    }
    @PutMapping(value = "/update")
    public ResponseEntity<?> updateRoom(@RequestBody RoomDto roomDto) {
        return roomService.updateRoom(roomDto);
    }

}