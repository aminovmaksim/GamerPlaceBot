package com.devian.gamerplacebot.bot.state;

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
    START("/start", State.INITIAL);

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
