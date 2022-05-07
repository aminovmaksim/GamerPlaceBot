package com.devian.gamerplacebot.bot.state;

import com.devian.gamerplacebot.bot.state.utils.Buttons;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum Command {

    /* For BotFather
     * start - Перезапустить бота
     * menu - Перейти в главное меню
     * book - Забронировать место в клубе
     */

    START("/start", State.INITIAL),
    MENU("/menu", State.MAIN_MENU),
    BOOK("/book", State.BOOKING_SELECT),

    MENU_T(Buttons.MENU, State.MAIN_MENU),
    BOOK_T(Buttons.BOOK, State.BOOKING_SELECT)
    ;

    String commandText;
    State state;

    static final Map<String, Command> commands = new HashMap<>();
    static {
        Arrays.stream(Command.values()).forEach(command -> commands.put(command.commandText, command));
    }

    public static Command of(String commandText) {
        return commands.get(commandText);
    }
}
