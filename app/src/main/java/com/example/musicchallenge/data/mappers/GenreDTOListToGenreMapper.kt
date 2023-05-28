package com.example.musicchallenge.data.mappers

import com.example.musicchallenge.data.remotedatasource.dto.genre.GenreDto
import com.example.musicchallenge.domain.models.Genre
import javax.inject.Inject

class GenreDTOListToGenreMapper @Inject constructor() {
    operator fun invoke(listGenre: List<GenreDto>): List<Genre> {
        with(listGenre) {
            return listGenre.map { genre ->
                Genre(
                    id = genre.id,
                    name = genre.name,
                    picture = genre.picture,
                    pictureSmall = genre.pictureSmall,
                    pictureMedium = genre.pictureMedium,
                    pictureBig = genre.pictureBig,
                    pictureXl = genre.pictureXl,
                    type = genre.type
                )
            }
        }
    }
}