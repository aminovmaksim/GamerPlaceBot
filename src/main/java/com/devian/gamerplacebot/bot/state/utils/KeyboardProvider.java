package com.devian.gamerplacebot.bot.state.utils;

import com.pengrad.telegrambot.model.WebAppInfo;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

import static com.devian.gamerplacebot.bot.state.utils.Buttons.BOOK;
import static com.devian.gamerplacebot.bot.state.utils.Buttons.CHANGE;
import static com.devian.gamerplacebot.bot.state.utils.Buttons.CHANGE_OK;
import static com.devian.gamerplacebot.bot.state.utils.Buttons.CORRECT;
import static com.devian.gamerplacebot.bot.state.utils.Buttons.CORRECT_OK;
import static com.devian.gamerplacebot.bot.state.utils.Buttons.MAP;
import static com.devian.gamerplacebot.bot.state.utils.Buttons.MENU;
import static com.devian.gamerplacebot.bot.state.utils.Buttons.REQUEST_NUMBER;

public class KeyboardProvider {

    public static ReplyKeyboardMarkup requestNumber() {
        return reply(new ReplyKeyboardMarkup(
                new KeyboardButton(REQUEST_NUMBER).requestContact(true)
        ));
    }

    public static ReplyKeyboardMarkup mainMenu() {
        return reply(new ReplyKeyboardMarkup(
                new KeyboardButton(BOOK)
        ));
    }

    public static ReplyKeyboardMarkup map() {
        return reply(new ReplyKeyboardMarkup(
                new KeyboardButton(MAP).webAppInfo(new WebAppInfo("https://aminovmaksim.github.io/mapbox-test")),
                new KeyboardButton(MENU)
        ));
    }

    public static InlineKeyboardMarkup confirmSelectedClub() {
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton(CORRECT).callbackData(CORRECT),
                new InlineKeyboardButton(CHANGE).callbackData(CHANGE));
    }

    public static InlineKeyboardMarkup confirmSelectedClub_Correct() {
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton(CORRECT_OK).callbackData(CORRECT),
                new InlineKeyboardButton(CHANGE).callbackData(CHANGE));
    }

    public static InlineKeyboardMarkup confirmSelectedClub_Change() {
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton(CORRECT).callbackData(CORRECT),
                new InlineKeyboardButton(CHANGE_OK).callbackData(CHANGE));
    }

    private static ReplyKeyboardMarkup reply(ReplyKeyboardMarkup keyboard) {
        return keyboard
                .oneTimeKeyboard(true)
                .resizeKeyboard(true);
    }
}
