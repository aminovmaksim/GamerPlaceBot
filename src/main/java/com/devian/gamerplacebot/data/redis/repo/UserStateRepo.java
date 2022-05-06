package com.devian.gamerplacebot.data.redis.repo;

import com.devian.gamerplacebot.data.redis.entity.UserState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStateRepo extends CrudRepository<UserState, Long> {}
