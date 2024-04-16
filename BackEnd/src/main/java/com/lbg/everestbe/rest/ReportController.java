package com.lbg.everestbe.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lbg.everestbe.domain.Report;
import com.lbg.everestbe.service.ReportService;

@RequestMapping("/report")
@CrossOrigin
@RestController
public class ReportController {

	private final ReportService service;

	public ReportController(ReportService service) {
		this.service = service;
	}

	@GetMapping("/getAll")
	public List<Report> getReport() {
		return this.service.getReport();
	}

	@PostMapping("/submit")
	public ResponseEntity<Report> submitReport(@RequestBody Report report) {
		return this.service.submitReport(report);

	}
}
