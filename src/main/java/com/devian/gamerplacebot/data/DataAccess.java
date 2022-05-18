package com.devian.gamerplacebot.data;

import com.devian.gamerplacebot.data.dao.ClubDao;
import com.devian.gamerplacebot.data.dao.UserDao;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC, makeFinal = true)
public class DataAccess {

    UserDao userDao;
    ClubDao clubDao;
}
