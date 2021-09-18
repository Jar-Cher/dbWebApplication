package com.example.dbwebapplication

import io.swagger.annotations.ApiParam
import org.example.ReviewData
import org.example.UpcomingEventsData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import java.time.LocalDate


@RestController
@RequestMapping("/admin")
class AdminController {

    @Autowired
    lateinit var tablesService: TablesService

    @GetMapping("/del review")
    fun delReview(id: Int): Int {
        val startTime = System.currentTimeMillis()
        val ans = tablesService.delReview(id)
        println(System.currentTimeMillis() - startTime)
        return ans
    }

    @GetMapping("/add event")
    fun addEvent(
        name: String,
        type: String,
        starting_date: LocalDate,
        finishing_date: LocalDate,
        responsible_id: Int? = null,
        organizer_id: Int? = null,
        sponsor_id: Int? = null,
        place_id: Int? = null,
        budget: Int
    ): Int {
        val startTime = System.currentTimeMillis()
        val ans = tablesService.addEvent(
            name,
            type,
            starting_date,
            finishing_date,
            responsible_id,
            organizer_id,
            sponsor_id,
            place_id,
            budget
        )
        println(System.currentTimeMillis() - startTime)
        return ans
    }

    @GetMapping("/add executive")
    fun addExecutive(id: Int, characteristics: String?=null): Int {
        val startTime = System.currentTimeMillis()
        val ans = tablesService.addExecutive(id, characteristics)
        println(System.currentTimeMillis() - startTime)
        return ans
    }

    @GetMapping("/add organisation")
    fun addOrganisation(id: Int, name: String, balance: Int?=null, text: String?=null): Int {
        val startTime = System.currentTimeMillis()
        val ans = tablesService.addOrganisation(id, name, balance, text)
        println(System.currentTimeMillis() - startTime)
        return ans
    }
}