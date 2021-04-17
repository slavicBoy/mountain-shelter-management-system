package com.example.demo.repositories;

import com.example.demo.model.worker.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
