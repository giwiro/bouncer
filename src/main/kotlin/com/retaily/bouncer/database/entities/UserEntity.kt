package com.retaily.bouncer.database.entities

import com.retaily.bouncer.models.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import javax.persistence.*

@Entity
@Table(name = "user", indexes = [
    Index(name = "user_email_idx", columnList = "email", unique = true)
])
class UserEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null
    private var firstName: String? = null
    private var lastName: String? = null
    private var email: String? = null
    private var password: String? = null

    constructor(firstName: String, lastName: String, email: String, password: String) : this() {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.password = password
    }

    fun mapToModel(): User {
        return User(id!!, firstName!!, lastName!!, email!!)
    }
}

@Repository
interface UserRepository : CrudRepository<UserEntity, Long> {
    fun save(entity: UserEntity): UserEntity

    @Query(value = "SELECT u FROM #{#entityName} u WHERE u.email = ?1")
    fun findByEmail(@Param("email") email: String): UserEntity?
}