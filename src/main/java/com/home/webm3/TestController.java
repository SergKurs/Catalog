package com.home.webm3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @GetMapping("/catalog2")
    public List<DataCatalogItem> mymetod(){
        //
        return ItemDAO.selectAll();





    }
}
