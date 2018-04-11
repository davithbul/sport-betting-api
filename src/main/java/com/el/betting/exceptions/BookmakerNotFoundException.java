package com.el.betting.exceptions;

public class BookmakerNotFoundException extends Exception {

    private String bookmakerName;

    public BookmakerNotFoundException(String bookmakerName) {
        this.bookmakerName = bookmakerName;
    }

    @Override
    public String getMessage() {
        return "Bookmaker with name: '" + bookmakerName + "' hasn't been found.";
    }
}
