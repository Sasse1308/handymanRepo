package org.example.handyman.repository;

import org.example.handyman.model.Handyman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HandyManRepo extends JpaRepository<Handyman,Long> {
    public List<Handyman> findBySkillIgnoreCase(String skill);
}
