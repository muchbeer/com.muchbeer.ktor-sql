package com.muchbeer.db

import com.muchbeer.model.School
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.dsl.insertAndGenerateKey
import org.ktorm.dsl.update
import org.ktorm.entity.firstOrNull
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList
import java.util.Arrays

class DatabaseFactory {


    //config
    private val hostname = "localhost"
    private val dbName = "family"
    private val username = "root"
    private val password = ""

    //database
    private val ktormDb : Database

   init {
        val jdbcUrl = "jdbc:mysql://$hostname:3306/$dbName?user=$username&password=$password&useSSL=false"
        ktormDb = Database.connect(jdbcUrl)
    }

    fun retrievAllSchool() : List<StudentTblEntity> {
        return ktormDb.sequenceOf(StudentTable).toList()
    }

    fun getSchoolByName(name : String) : School? {
       val schoolEntitiy = ktormDb.sequenceOf(StudentTable).firstOrNull {
            it.school eq name
        }?.let { School(id = it.id, school = it.school, region = it.region, sex = it.sex) }
        return schoolEntitiy
    }

    fun insertSchool (school : School) : School {
     val schoolID : Int =   ktormDb.insertAndGenerateKey(StudentTable) {
            set(StudentTable.school, school.school)
            set(StudentTable.region, school.region)
            set(StudentTable.sex, school.sex)
        } as Int

        return School(id = schoolID, school = school.school,
            region = school.region, sex = school.sex)
    }

    fun updateSchool (name : String, school: School) : School {
        val schoolID : Int =   ktormDb.update(StudentTable) {
            set(StudentTable.school, school.school)
            set(StudentTable.region, school.region)
            set(StudentTable.sex, school.sex)

            where {
                it.school eq name
            }
        }

        return School(id = schoolID, school = school.school,
            region = school.region, sex = school.sex)
    }

    fun mapUssd() {

    }
}