package com.victor.a1.task3.repository;

import com.victor.a1.task3.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PostingRepository extends JpaRepository<Posting, Long> {
}
