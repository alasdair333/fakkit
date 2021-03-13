package com.fakkit.controller;

import com.fakkit.dto.VoteDto;
import com.fakkit.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping("/votes")
    public ResponseEntity<Void> vote(@RequestBody VoteDto voteDto) {
        voteService.votePost(voteDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
