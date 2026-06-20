package com.example.attendance.controller;

import com.example.attendance.model.PunchRequest;
import com.example.attendance.service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ams")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(
            AttendanceService attendanceService) {

        this.attendanceService = attendanceService;
    }

    @PostMapping("/punch")
    public ResponseEntity<?> processPunch(
            @RequestBody PunchRequest request) {

        try {

            return ResponseEntity.ok(
                    attendanceService.processPunch(request)
            );

        } catch (Exception e) {

            return ResponseEntity.status(404)
                    .body(
                            Map.of(
                                    "error",
                                    e.getMessage()
                            )
                    );
        }
    }
}