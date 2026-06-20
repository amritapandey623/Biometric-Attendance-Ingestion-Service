package com.example.attendance.service;

import com.example.attendance.model.*;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AttendanceService {

    private final List<Student> students = List.of(
            new Student("RFID1001", "Aarav Shetty", "8-B")
    );

    private final List<Staff> staff = List.of(
            new Staff("RFID2001", "Priya Nair", "Teacher")
    );

    private final Map<String, AttendanceRecord> todayAttendance = new HashMap<>();

    private final List<PunchLog> staffLogs = new ArrayList<>();

    private final Map<String, LocalDateTime> recentPunches = new HashMap<>();

    public AttendanceService() {
        todayAttendance.put(
                "RFID1001",
                new AttendanceRecord("Absent", "Marked by Teacher")
        );
    }

    public Map<String, Object> processPunch(PunchRequest request) {

        String punchId = request.getPunchId();

        LocalDateTime punchTime =
                LocalDateTime.parse(request.getTimestamp());

        if (recentPunches.containsKey(punchId)) {

            Duration duration =
                    Duration.between(
                            recentPunches.get(punchId),
                            punchTime
                    );

            if (duration.getSeconds() < 60) {

                return Map.of(
                        "action",
                        "duplicate_ignored",
                        "message",
                        "Punch ignored within 60 seconds"
                );
            }
        }

        recentPunches.put(punchId, punchTime);

        Optional<Student> student =
                students.stream()
                        .filter(s -> s.getPunchId().equals(punchId))
                        .findFirst();

        if (student.isPresent()) {
            return handleStudent(student.get(), punchTime);
        }

        Optional<Staff> staffMember =
                staff.stream()
                        .filter(s -> s.getPunchId().equals(punchId))
                        .findFirst();

        if (staffMember.isPresent()) {
            return handleStaff(staffMember.get(), punchTime);
        }

        throw new RuntimeException("Punch ID not found");
    }

    private Map<String, Object> handleStudent(
            Student student,
            LocalDateTime time) {

        String punchId = student.getPunchId();

        AttendanceRecord record =
                todayAttendance.get(punchId);

        if (record == null) {

            todayAttendance.put(
                    punchId,
                    new AttendanceRecord(
                            "Present",
                            "New biometric record"
                    )
            );

            return Map.of(
                    "action",
                    "new_record_created",
                    "student",
                    student.getName()
            );
        }

        if ("Absent".equals(record.getStatus())) {

            record.setStatus("Present");
            record.setNote(
                    "Marked present via biometric override at "
                            + time.toLocalTime()
            );

            return Map.of(
                    "action",
                    "overridden_to_present",
                    "student",
                    student.getName()
            );
        }

        return Map.of(
                "action",
                "duplicate_ignored",
                "student",
                student.getName()
        );
    }

    private Map<String, Object> handleStaff(
            Staff staff,
            LocalDateTime time) {

        staffLogs.add(
                new PunchLog(
                        staff.getPunchId(),
                        time.toString()
                )
        );

        return Map.of(
                "action",
                "staff_punch_logged",
                "staff",
                staff.getName(),
                "time",
                time.toString()
        );
    }
}