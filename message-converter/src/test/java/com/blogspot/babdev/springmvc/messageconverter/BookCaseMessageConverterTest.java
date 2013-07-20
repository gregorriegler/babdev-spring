package com.blogspot.babdev.springmvc.messageconverter;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.http.MockHttpInputMessage;
import org.springframework.mock.http.MockHttpOutputMessage;

import java.io.IOException;
import java.util.Arrays;

public class BookCaseMessageConverterTest {

    private BookCaseMessageConverter bookCaseMessageConverter = new BookCaseMessageConverter();

    private final BookCase bookCase = new BookCase(Arrays.asList(new Book[]{
            new Book("123", "Book 123"),
            new Book("456", "Book 456"),
    }));

    private final String bookCaseCsv = "\"123\",\"Book 123\"\n\"456\",\"Book 456\"\n";

    @Test
    public void testSupports() {
        Assert.assertTrue(bookCaseMessageConverter.supports(BookCase.class));
    }

    @Test
    public void testReadInternal() throws IOException {
        BookCase bookCase = bookCaseMessageConverter.readInternal(BookCase.class, new MockHttpInputMessage(bookCaseCsv.getBytes()));
        Assert.assertEquals(2, bookCase.size());
        Book secondBook = bookCase.get(1);
        Assert.assertEquals("456", secondBook.getIsbn());
    }

    @Test
    public void testWriteInternal() throws IOException {
        MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
        bookCaseMessageConverter.writeInternal(bookCase, outputMessage);
        Assert.assertEquals(bookCaseCsv, outputMessage.getBodyAsString());
    }


}
