package com.devian.gamerplacebot.data.mapper;

import com.devian.gamerplacebot.data.model.ClubInfo;
import com.devian.gamerplacebot.data.model.UserInfo;
import com.devian.gamerplacebot.data.postgres.entity.Club;
import com.devian.gamerplacebot.data.postgres.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DatabaseMapper {

    User userInfo(UserInfo userInfo);

    UserInfo userInfo(User user);

    Club clubInfo(ClubInfo clubInfo);

    ClubInfo clubInfo(Club club);
}
