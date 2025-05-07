package com.example.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rest.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long> {

}
