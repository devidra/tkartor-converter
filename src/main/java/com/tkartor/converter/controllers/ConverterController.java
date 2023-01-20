package com.tkartor.converter.controllers;

import com.tkartor.converter.controllers.responses.People;
import com.tkartor.converter.services.ConverterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("convert")
public class ConverterController {

    private final ConverterService converterService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces=MediaType.APPLICATION_XML_VALUE)
    public People convert(@RequestParam("file") MultipartFile file) {
        return converterService.convert(file);
    }
}
