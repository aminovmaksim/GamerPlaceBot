package com.devian.gamerplacebot.data.postgres;

import com.devian.gamerplacebot.data.postgres.repo.ClubRepo;
import com.devian.gamerplacebot.data.postgres.repo.UserRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC, makeFinal = true)
public class PostgresAccess {

    UserRepo userRepo;
    ClubRepo clubRepo;
}
