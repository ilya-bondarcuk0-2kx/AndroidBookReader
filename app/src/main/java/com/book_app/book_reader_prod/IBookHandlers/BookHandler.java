package com.book_app.book_reader_prod.IBookHandlers;


import android.content.Context;
import android.view.View;
import java.io.IOException;

public interface BookHandler {
    void open(Context context, String path) throws IOException;

    View getView();
    void close();

}
