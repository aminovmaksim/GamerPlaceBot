package com.devian.gamerplacebot.bot.state;

import com.devian.gamerplacebot.data.DataAccess;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Slf4j
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class StateHandlerProvider {

    Map<State, StateHandler> handlers;

    public StateHandlerProvider(DataAccess dataAccess) throws ReflectiveOperationException {
        handlers = new EnumMap<>(State.class);
        for (State state : State.values()) {
            handlers.put(state, state.getHandler().getDeclaredConstructor(DataAccess.class).newInstance(dataAccess));
        }
        log.info("Registered {} handlers", handlers.size());
    }

    public StateHandler getHandler(State state) {
        return handlers.get(state);
    }
}
