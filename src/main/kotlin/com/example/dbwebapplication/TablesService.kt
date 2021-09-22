package com.example.dbwebapplication

import org.example.ReviewData
import org.example.UpcomingEventsData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.jdbc.core.query
import org.springframework.stereotype.Service
import java.sql.ResultSet
import java.time.LocalDate

@Service
class TablesService {
    @Autowired
    lateinit var tablesRepository: TablesRepository

    //@CacheEvict(value = ["registeredPersons"], allEntries = true)
    fun addRegisteredPerson(surname: String, name: String, parental_name: String?=null, phone_number: String?=null): Int {
        return tablesRepository.addRegisteredPerson(surname, name, parental_name, phone_number)
    }

    //@CacheEvict(value = ["visits"], allEntries = true)
    fun addVisit(person_id: Int, event_id: Int): Int {
        return tablesRepository.addVisit(person_id, event_id)
    }

    @CacheEvict(value = ["reviews"], allEntries = true)
    fun addReview(visit_id: Int, text: String): Int {
        return tablesRepository.addReview(visit_id, text)
    }

    @Cacheable("reviews")
    fun getReviews(event_id: Int, reviewsToList: Int): List<ReviewData> {
        return tablesRepository.getReviews(event_id, reviewsToList)
    }

    @Cacheable("events")
    fun getUpcomingEvents(today: LocalDate = LocalDate.now(), eventsToList: Int): List<UpcomingEventsData> {
        return tablesRepository.getUpcomingEvents(today, eventsToList)
    }

    @CacheEvict(value = ["reviews"], allEntries = true)
    fun delReview(id: Int): Int {
        return tablesRepository.delReview(id)
    }

    @CacheEvict(value = ["events"], allEntries = true)
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
        return tablesRepository.addEvent(
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
    }

    //@CacheEvict(value = ["executives"], allEntries = true)
    fun addExecutive(id: Int, characteristics: String?=null): Int {
        return tablesRepository.addExecutive(id, characteristics)
    }

    //@CacheEvict(value = ["organisations"], allEntries = true)
    fun addOrganisation(name: String, balance: Int?=null, characteristics: String?=null): Int {
        return tablesRepository.addOrganisation(name, balance, characteristics)
    }
}