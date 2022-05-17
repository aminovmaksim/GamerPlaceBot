package com.devian.gamerplacebot.data.model;

import com.devian.gamerplacebot.bot.state.State;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserInfo {

    Long id;
    String phoneNumber;
    String firstName;
    String lastName;
    State lastSavedState;
}
