package com.zinemaapp.zinemaapp.controller;

import com.zinemaapp.zinemaapp.application.TvSerieService;
import com.zinemaapp.zinemaapp.dto.internal.TvSerieDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<TvSerieDTO> getSerieById(@PathVariable int id){
        return ResponseEntity.ok(tvSerieService.getSerieById(id));
    }
}
