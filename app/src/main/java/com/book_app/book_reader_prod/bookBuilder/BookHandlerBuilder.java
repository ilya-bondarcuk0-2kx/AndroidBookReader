package com.book_app.book_reader_prod.bookBuilder;






import android.app.Activity;

import com.book_app.book_reader_prod.BookHandlersImpl.EPubHandler;
import com.book_app.book_reader_prod.BookHandlersImpl.PDFHandler;
import com.book_app.book_reader_prod.IBookHandlers.BookHandler;
import com.book_app.book_reader_prod.R;

public class BookHandlerBuilder {


    public static BookHandler buildHandler(String extension){

        if(extension.contains("pdf"))
            return new PDFHandler();
        else if(extension.contains("epub"))
            return new EPubHandler();
//        else if(extension.contains("fb2"))

        return null;
    }



}
