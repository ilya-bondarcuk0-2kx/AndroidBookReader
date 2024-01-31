package com.book_app.book_reader_prod.BookHandlersImpl;


import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.book_app.book_reader_prod.IBookHandlers.BookHandler;
import com.github.barteksc.pdfviewer.PDFView;


public class PDFHandler  implements BookHandler{

    PDFView pdfBook;


    @Override
    public void open(Context context,String path) {
        pdfBook = new PDFView(context, null);
        pdfBook.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        pdfBook.fromUri(Uri.parse(path)).load();

    }

    @Override
    public View getView() {
        return pdfBook;
    }

    @Override
    public void close() {

    }
}