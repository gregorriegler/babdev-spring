package com.blogspot.babdev.springmvc.messageconverter;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/bookcase")
public class BookCaseController {

    private BookCase bookCase;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public BookCase getBookCase() {
        return this.bookCase;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setBookCase(@RequestBody BookCase bookCase) {
        this.bookCase = bookCase;
    }

}
