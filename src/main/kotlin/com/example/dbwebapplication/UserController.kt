package com.example.dbwebapplication

import io.swagger.annotations.ApiParam
import org.example.ReviewData
import org.example.UpcomingEventsData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import java.lang.NullPointerException
import java.time.LocalDate


@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    lateinit var tablesService: TablesService

    @PutMapping("/add registered person")
    fun addRegisteredPerson(surname: String, name: String, parental_name: String?=null, phone_number: String?=null): Int {
        val startTime = System.currentTimeMillis()
        val ans = tablesService.addRegisteredPerson(surname, name, parental_name, phone_number)
        val endTime = System.currentTimeMillis()
        println(endTime - startTime)
        return ans
    }

    @PutMapping("/add visit")
    fun addVisit(person_id: Int, event_id: Int): Int {
        val startTime = System.currentTimeMillis()
        val ans = tablesService.addVisit(person_id, event_id)
        val endTime = System.currentTimeMillis()
        println(endTime - startTime)
        return ans
    }

    @PutMapping("/add review")
    fun addReview(visit_id: Int, text: String): Int {
        val startTime = System.currentTimeMillis()
        val ans = tablesService.addReview(visit_id, text)
        val endTime = System.currentTimeMillis()
        println(endTime - startTime)
        return ans
    }

    @GetMapping("/get reviews")
    fun getReviews(event_id: Int, reviewsToList: Int): List<ReviewData> {
        val startTime = System.currentTimeMillis()
        val ans = tablesService.getReviews(event_id, reviewsToList)
        val endTime = System.currentTimeMillis()
        println(endTime - startTime)
        return ans
    }

    @GetMapping("/get upcoming events")
    fun getUpcomingEvents(today: String?, eventsToList: Int): List<UpcomingEventsData> {
        val startTime = System.currentTimeMillis()
        val ans = tablesService.getUpcomingEvents(
            try {
                LocalDate.parse(today)
            } catch (e: NullPointerException) {
                LocalDate.now()
            },
            eventsToList
        )
        println(System.currentTimeMillis() - startTime)
        return ans
    }
}