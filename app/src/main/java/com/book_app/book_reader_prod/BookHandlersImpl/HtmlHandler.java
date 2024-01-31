package com.book_app.book_reader_prod.BookHandlersImpl;

import android.content.Context;
import android.view.View;

import com.book_app.book_reader_prod.IBookHandlers.BookHandler;

import java.io.IOException;

public class HtmlHandler implements BookHandler {


    @Override
    public void open(Context context, String path) throws IOException {

    }

    @Override
    public View getView() {
        return null;
    }

    @Override
    public void close() {

    }
}
