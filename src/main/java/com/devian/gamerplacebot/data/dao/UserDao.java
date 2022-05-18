package com.devian.gamerplacebot.data.dao;

import com.devian.gamerplacebot.bot.state.State;
import com.devian.gamerplacebot.data.mapper.DatabaseMapper;
import com.devian.gamerplacebot.data.model.UserInfo;
import com.devian.gamerplacebot.data.postgres.PostgresAccess;
import com.devian.gamerplacebot.data.redis.RedisAccess;
import com.devian.gamerplacebot.data.redis.entity.UserState;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC, makeFinal = true)
public class UserDao {

    RedisAccess redisAccess;
    PostgresAccess postgresAccess;
    DatabaseMapper databaseMapper;

    /**
     * Получение состояния пользователя
     * Если состояние не найдено, то сохраняется и возвращается INITIAL
     * @param id        идентификатор пользователя
     * @return объект   State
     */
    public State getState(Long id) {
        var stateOpt = redisAccess.userStateRepo.findById(id);
        var state = State.INITIAL;
        if (stateOpt.isEmpty()) {
            setState(id, state);
        } else {
            state = stateOpt.get().getState();
        }
        return state;
    }

    /**
     * Установка нового состояния пользователю
     * @param id    идентификатор пользователя
     * @param state новое состояние
     */
    public void setState(Long id, State state) {
        if (id != null && state != null) {
            redisAccess.userStateRepo.save(new UserState(id, state));
        }
    }

    /**
     * Сохранение данных о пользователе
     */
    public void setUserInfo(UserInfo info) {
        if (info != null) {
            postgresAccess.userRepo.save(databaseMapper.userInfo(info));
        }
    }

    /**
     * Получение данных о пользователе
     */
    public UserInfo getUserInfo(Long id) {
        var infoOpt = postgresAccess.userRepo.findById(id);
        if (infoOpt.isEmpty()) {
            return null;
        }
        return databaseMapper.userInfo(infoOpt.get());
    }
}
