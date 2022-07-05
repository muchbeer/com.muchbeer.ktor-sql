package com.muchbeer.repository

import com.muchbeer.model.School

interface SchoolRepository {

    fun retrievAllSchool() : List<School>

    fun findSchoolByName(name : String) : School?

    fun insertSchool(school: School) : School

    fun removeSchoolByRegion(region: String)


}