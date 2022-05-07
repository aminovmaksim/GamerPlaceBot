package com.devian.gamerplacebot.data.redis;

import com.devian.gamerplacebot.data.redis.repo.LastMessageRepo;
import com.devian.gamerplacebot.data.redis.repo.UserInfoRepo;
import com.devian.gamerplacebot.data.redis.repo.UserStateRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC, makeFinal = true)
public class RedisAccess {

    UserStateRepo userStateRepo;
    UserInfoRepo userInfoRepo;
    LastMessageRepo lastMessageRepo;
}
