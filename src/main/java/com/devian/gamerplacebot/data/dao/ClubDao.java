package com.devian.gamerplacebot.data.dao;

import com.devian.gamerplacebot.data.mapper.DatabaseMapper;
import com.devian.gamerplacebot.data.model.ClubInfo;
import com.devian.gamerplacebot.data.postgres.PostgresAccess;
import com.devian.gamerplacebot.data.redis.RedisAccess;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC, makeFinal = true)
public class ClubDao {

    RedisAccess redisAccess;
    PostgresAccess postgresAccess;
    DatabaseMapper databaseMapper;

    public List<ClubInfo> getClubList() {
        var clubList = new ArrayList<ClubInfo>();
        postgresAccess.clubRepo.findAll().forEach(club -> clubList.add(databaseMapper.clubInfo(club)));
        return clubList;
    }
}
