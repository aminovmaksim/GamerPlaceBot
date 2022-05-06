package com.devian.gamerplacebot.data.redis.entity;

import com.devian.gamerplacebot.bot.state.State;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("user-state")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserState implements Serializable {

    @Id
    Long id;
    State state;
}
