package com.dev.spring.kotlin.controller

import com.dev.spring.kotlin.domain.Anime
import com.dev.spring.kotlin.requests.AnimePostRequestBody
import com.dev.spring.kotlin.requests.AnimePutRequestBody
import com.dev.spring.kotlin.service.AnimeService
import com.dev.spring.kotlin.util.DateUtil
import lombok.AllArgsConstructor
import lombok.extern.log4j.Log4j2
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("animes")
@Log4j2
@AllArgsConstructor
class AnimeController @Autowired constructor(private val dateUtil: DateUtil,private val animeService:  AnimeService) {

    companion object {
        private val log = org.apache.logging.log4j.LogManager.getLogger(AnimeController::class.java)
    }

    @GetMapping
    fun list(): ResponseEntity<List<Anime>> {
        log.info(dateUtil.formatLocalDateTimeToDataBaseStyle(LocalDateTime.now()))
        return ResponseEntity(animeService.listAll(), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun findByID(@PathVariable id: Long): ResponseEntity<Anime> {
        log.info(dateUtil.formatLocalDateTimeToDataBaseStyle(LocalDateTime.now()))
        return ResponseEntity.ok(animeService.findById(id))
    }

    @PostMapping
    fun save(@RequestBody animePostRequestBody: AnimePostRequestBody): ResponseEntity<Anime> {
       return ResponseEntity(animeService.save(animePostRequestBody), HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void>{
        animeService.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PutMapping
    fun replace(@RequestBody animePutRequestBody: AnimePutRequestBody): ResponseEntity<Void>{
        animeService.replace(animePutRequestBody)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}