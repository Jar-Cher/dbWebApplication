package com.example.dbwebapplication

import org.example.ReviewData
import org.example.UpcomingEventsData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.time.LocalDate

@Repository
class TablesRepository {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    //     user - addReg
    fun addRegisteredPerson(surname: String, name: String, parental_name: String?=null, phone_number: String?=null): Int {
        return jdbcTemplate.update("INSERT INTO registered_persons (surname, name, parental_name, phone_number) VALUES (?,?,?,?)",
        surname, name, parental_name, phone_number)
    }
    //     user - addVisit
    fun addVisit(person_id: Int, event_id: Int): Int {
        return jdbcTemplate.update("INSERT INTO visits (person_id, event_id) VALUES (?,?)", person_id, event_id)
    }
    //     user - add/view/Review
    fun addReview(visit_id: Int, text: String): Int {
        return jdbcTemplate.update("INSERT INTO reviews (visit_id, text) VALUES (?,?)", visit_id, text)
    }
    fun getReviews(event_id: Int, reviewsToList: Int): List<ReviewData>{
        return jdbcTemplate.query("SELECT reviews.id, reviews.text, name\n" +
                "FROM reviews\n" +
                "         INNER JOIN visits ON visits.id = reviews.visit_id\n" +
                "         INNER JOIN events ON visits.event_id = events.id AND events.id = ? ORDER BY reviews.id LIMIT ?", event_id, reviewsToList
        ) { result: ResultSet, _: Int ->
            ReviewData(result.getInt("id"), result.getString("name"), result.getString("text"))
        }
    }
    //     user - viewUpcomingEvents
    fun getUpcomingEvents(today: LocalDate = LocalDate.now(), eventsToList: Int): List<UpcomingEventsData>{
        return jdbcTemplate.query(
            "SELECT id, name, type, starting_date, finishing_date FROM events WHERE starting_date >= ? ORDER BY starting_date LIMIT ?",
            today,
            eventsToList
        ) { result: ResultSet, _: Int ->
            UpcomingEventsData(
                result.getInt("id"),
                result.getString("name"),
                result.getString("type"),
                LocalDate.parse(result.getString("starting_date")),
                LocalDate.parse(result.getString("finishing_date"))
            )
        }
    }
    //     admin += user
    //     admin - deleteReview. Censorship, yay!
    fun delReview(id: Int): Int {
        return jdbcTemplate.update("DELETE FROM reviews WHERE id = ?", id)
    }
    //     admin - addEvent
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
        return jdbcTemplate.update(
            "INSERT INTO events (name, type, starting_date, finishing_date, responsible_id, organizer_id, sponsor_id, place_id,\n" +
                    "                      budget)\n" +
                    "VALUES (?,?,?,?,?,?,?,?,?)",
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
    //     admin - addExecutive
    fun addExecutive(id: Int, characteristics: String?=null): Int {
        return jdbcTemplate.update("INSERT INTO executives (id, characteristics) VALUES (?,?)", id, characteristics)
    }
    //     admin - addOrganisation
    fun addOrganisation(name: String, balance: Int?=null, characteristics: String?=null): Int {
        return jdbcTemplate.update("INSERT INTO organisations (name, balance, characteristics) VALUES (?,?,?)", name, balance, characteristics)
    }
}