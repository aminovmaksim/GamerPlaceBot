package com.devian.gamerplacebot.data.redis.repo;

import com.devian.gamerplacebot.data.redis.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepo extends CrudRepository<UserInfo, Long> {
}
