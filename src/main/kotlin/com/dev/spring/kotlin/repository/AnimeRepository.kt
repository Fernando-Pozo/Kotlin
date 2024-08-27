package com.dev.spring.kotlin.repository

import com.dev.spring.kotlin.domain.Anime
import org.springframework.data.jpa.repository.JpaRepository

interface AnimeRepository: JpaRepository<Anime, Long> {
}