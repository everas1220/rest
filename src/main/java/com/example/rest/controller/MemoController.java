package com.example.rest.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.rest.dto.MemoDTO;
import com.example.rest.service.MemoService;

@Log4j2
@RequestMapping("/memo")
@RequiredArgsConstructor
@RestController
public class MemoController {

    private final MemoService memoService;

    // 주소 설계
    // 전체 memo 조회 : /memo/list
    @GetMapping("/list")
    public List<MemoDTO> getList(Model model) {
        List<MemoDTO> list = memoService.getList();
        return list;
    }

    // 특정 memo 조회 : /memo/read?mno=3
    @GetMapping(value = { "/read", "/update" })
    public MemoDTO getRow(Long mno, Model model) {
        log.info("조회 요청{}", mno);
        MemoDTO dto = memoService.getRow(mno);
        return dto;
    }

    @PutMapping("/update")
    public Long postUpdate(@RequestBody MemoDTO dto) {
        log.info("메모 수정 {}", dto);
        Long mno = memoService.memoUpdate(dto);
        return mno;
    }

    // memo 수정 : /memo/update?mno=3
    // memo 추가 : /memo/new
    @GetMapping("/new")
    public void getNew() {
        log.info("새 메모 작성 폼 요청");
    }

    @PostMapping("/new")
    public Long postNew(@RequestBody MemoDTO dto) {
        log.info("새 메모 작성 {}", dto);
        Long mno = memoService.memoCreate(dto);
        return mno;
    }

    @DeleteMapping("/remove/{mno}")
    public Long getRemove(@PathVariable Long mno) {
        log.info("메모 삭제 요청 {}", mno);

        memoService.memoDelete(mno);

        return mno;
    }
    // memo 삭제 : /memo/remove?mno=3

}
