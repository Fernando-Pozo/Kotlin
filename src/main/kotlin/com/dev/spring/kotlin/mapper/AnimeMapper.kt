package com.dev.spring.kotlin.mapper

import com.dev.spring.kotlin.domain.Anime
import com.dev.spring.kotlin.requests.AnimePostRequestBody
import com.dev.spring.kotlin.requests.AnimePutRequestBody
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface AnimeMapper {
    companion object {
        val INSTANCE: AnimeMapper = Mappers.getMapper(AnimeMapper::class.java)
    }

    fun toAnime(animePostRequestBody: AnimePostRequestBody): Anime

    fun toAnime(animePutRequestBody: AnimePutRequestBody): Anime
}