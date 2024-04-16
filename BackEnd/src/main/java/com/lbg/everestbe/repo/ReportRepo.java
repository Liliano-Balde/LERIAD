package com.lbg.everestbe.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lbg.everestbe.domain.Report;

public interface ReportRepo extends JpaRepository<Report, Integer> {

}
