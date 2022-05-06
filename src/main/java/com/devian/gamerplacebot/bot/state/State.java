package com.devian.gamerplacebot.bot.state;

import com.devian.gamerplacebot.bot.state.handlers.InitialState;
import com.devian.gamerplacebot.bot.state.handlers.PhoneRequestState;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum State {
    INITIAL(InitialState.class),            // Начальный статус бота при запуске или сбросе
    PHONE_REQUEST(PhoneRequestState.class)  // Запрос номера мобильного телефона
    ;

    Class<? extends StateHandler> handler;
}
