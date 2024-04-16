package com.lbg.everestbe.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lbg.everestbe.domain.Report;
import com.lbg.everestbe.repo.ReportRepo;

@Service
public class ReportService {

	private ReportRepo repo;

	public ReportService(ReportRepo repo) {
		super();
		this.repo = repo;
	}

	public List<Report> getReport() {
		return this.repo.findAll();
	}

	public ResponseEntity<Report> submitReport(Report report) {
		Report created = this.repo.save(report);
		return new ResponseEntity<Report>(created, HttpStatus.CREATED);

	}
}