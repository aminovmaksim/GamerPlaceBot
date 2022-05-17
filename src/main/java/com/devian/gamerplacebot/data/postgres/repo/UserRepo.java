package com.devian.gamerplacebot.data.postgres.repo;

import com.devian.gamerplacebot.data.postgres.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
}
