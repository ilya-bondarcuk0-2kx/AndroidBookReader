package com.book_app.book_reader_prod.BookHandlersImpl;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.book_app.book_reader_prod.IBookHandlers.BookHandler;
import java.io.IOException;



public class EPubHandler implements BookHandler {

    WebView epubBook;


    @Override
    public void open(Context context, String path) throws IOException {
        epubBook = new WebView(context, null);
        epubBook.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        epubBook.loadUrl(path);

    }

    @Override
    public View getView() {

        return epubBook;
    }

    @Override
    public void close() {

    }
}
