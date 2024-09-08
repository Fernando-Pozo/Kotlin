package com.dev.spring.kotlin.service

import com.dev.spring.kotlin.domain.Anime
import com.dev.spring.kotlin.mapper.AnimeMapper
import com.dev.spring.kotlin.repository.AnimeRepository
import com.dev.spring.kotlin.requests.AnimePostRequestBody
import com.dev.spring.kotlin.requests.AnimePutRequestBody
import jakarta.transaction.Transactional
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class AnimeService(
    @Autowired
    private val animeRepository: AnimeRepository
)  {

    fun listAll(): List<Anime> {
        return animeRepository.findAll()
    }

    fun findByName(name: String): List<Anime> {
        return animeRepository.findByName(name)
    }

    fun findById(id: Long): Anime {
        return  animeRepository.findById(id).orElseThrow {
            NoSuchElementException("Anime com id $id não encontrado")
        }
    }

    @Transactional
    fun save(animePostRequestBody: AnimePostRequestBody): Anime {
        val animeName = animePostRequestBody.name ?: throw IllegalArgumentException("O nome do anime não pode ser nulo")
        val anime = AnimeMapper.INSTANCE.toAnime(animePostRequestBody.copy(name = animeName))
        return animeRepository.save(anime)
    }

    @Transactional
    fun delete(id: Long){
       val animeToDelete = findById(id)
        animeRepository.delete(animeToDelete)
    }

    @Transactional
    fun replace(animePutRequestBody: AnimePutRequestBody): Anime {
        val id = animePutRequestBody.id ?: throw IllegalArgumentException("Anime ID não pode ser nulo")
        val existingAnime = animeRepository.findById(id)
            .orElseThrow { NoSuchElementException("Anime com id $id não encontrado") }
        val updatedAnime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody.copy(id = existingAnime.id))
        return animeRepository.save(updatedAnime)
    }
}