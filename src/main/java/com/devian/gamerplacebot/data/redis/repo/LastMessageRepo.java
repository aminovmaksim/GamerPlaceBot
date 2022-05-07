package com.devian.gamerplacebot.data.redis.repo;

import com.devian.gamerplacebot.data.redis.entity.LastMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LastMessageRepo extends CrudRepository<LastMessage, Long> {
}
