package com.zinemaapp.zinemaapp.controller;

import com.zinemaapp.zinemaapp.application.TvSerieService;
import com.zinemaapp.zinemaapp.dto.internal.TvSerieDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tv")
public class TvSerieController {

    private final TvSerieService tvSerieService;

    public TvSerieController(TvSerieService tvSerieService) {
        this.tvSerieService = tvSerieService;
    }

    @GetMapping("/top_rated")
    public ResponseEntity<List<TvSerieDTO>> getTopRatedSeries(@RequestParam int page){
        return ResponseEntity.ok(tvSerieService.getTopRatedSerie(page));
    }


}
