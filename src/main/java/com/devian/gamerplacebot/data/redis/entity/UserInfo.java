package com.devian.gamerplacebot.data.redis.entity;

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
@RedisHash("user-info")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserInfo implements Serializable {

    @Id
    Long id;
    String phoneNumber;
    String firstName;
    String lastName;
}
