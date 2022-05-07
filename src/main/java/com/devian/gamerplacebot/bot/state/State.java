package com.devian.gamerplacebot.bot.state;

import com.devian.gamerplacebot.bot.state.handlers.BookingSelect;
import com.devian.gamerplacebot.bot.state.handlers.ClubSelected;
import com.devian.gamerplacebot.bot.state.handlers.Initial;
import com.devian.gamerplacebot.bot.state.handlers.MainMenu;
import com.devian.gamerplacebot.bot.state.handlers.PhoneRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum State {

    // Начальный статус бота при запуске или сбросе
    INITIAL(Initial.class),

    // Запрос номера мобильного телефона
    PHONE_REQUEST(PhoneRequest.class),

    // Главное меню
    MAIN_MENU(MainMenu.class),

    // Выбор клуба для бронирования
    BOOKING_SELECT(BookingSelect.class),

    // Выбран клуб для бронирования
    CLUB_SELECTED(ClubSelected.class)
    ;

    Class<? extends StateHandler> handler;
}
