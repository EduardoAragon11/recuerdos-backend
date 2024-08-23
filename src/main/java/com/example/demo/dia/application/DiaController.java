package com.example.demo.dia.application;

import com.example.demo.dia.domain.DiaService;
import com.example.demo.dia.dto.DiaResponseDTO;
import com.example.demo.dia.dto.NewDiaDTO;
import com.example.demo.dia.dto.SimpleDiaResponseDTO;
import com.example.demo.event.dto.NewEventDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dia")
public class DiaController {
    @Autowired
    DiaService diaService;

    @PostMapping()
    public ResponseEntity<Void> createDay(@RequestBody NewDiaDTO newDayDTO){
        diaService.newDay(newDayDTO);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiaResponseDTO> getDay(@PathVariable Long id){
        return ResponseEntity.ok(diaService.getDay(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDay(@PathVariable Long id){
        diaService.deleteDay(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/event")
    public ResponseEntity<Void> newEvent(@PathVariable Long id, @RequestBody NewEventDTO newEventDTO){
        diaService.newEvent(id, newEventDTO);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/year-month")
    public ResponseEntity<List<SimpleDiaResponseDTO>> getDayByYearAndMonth(@RequestParam("year") String year, @RequestParam("month") String month){
        return ResponseEntity.ok(diaService.getDayByYearAndMonth(year,month));
    }
}
