package org.ftn.upp.lass.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ftn.upp.lass.model.Genre;
import org.ftn.upp.lass.repository.GenreRepository;
import org.ftn.upp.lass.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public List<Genre> getAvailableGenres() {
        return this.genreRepository.findAll();
    }
}
