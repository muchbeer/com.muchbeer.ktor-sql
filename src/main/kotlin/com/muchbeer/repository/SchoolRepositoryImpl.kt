package com.muchbeer.repository

import com.muchbeer.db.DatabaseFactory
import com.muchbeer.model.School

class SchoolRepositoryImpl : SchoolRepository {

    val dbConnect = DatabaseFactory()

    override fun retrievAllSchool(): List<School> {
        return dbConnect.retrievAllSchool().map {
            School(
                id = it.id,
                school = it.school,
                region = it.region,
                sex = it.sex
            )
        }
    }

    override fun findSchoolByName(name: String): School? {
       return dbConnect.getSchoolByName(name)
    }

    override fun insertSchool(school: School): School {
       return dbConnect.insertSchool(school)
    }

    override fun removeSchoolByRegion(region: String) {
        TODO("Not yet implemented")
    }
}