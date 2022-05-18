package com.devian.gamerplacebot.web;

import com.devian.gamerplacebot.data.DataAccess;
import com.devian.gamerplacebot.data.model.ClubInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BotRestController {

    private final DataAccess dataAccess;

    @GetMapping(value = "/clubs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClubInfo> getClubList() {
        return dataAccess.clubDao.getClubList();
    }
}
