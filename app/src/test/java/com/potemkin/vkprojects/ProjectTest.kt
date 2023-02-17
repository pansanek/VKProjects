package com.potemkin.vkprojects

import org.junit.Test

import org.junit.Assert.*

internal class ProjectTest {

    @Test
    fun  `checkProjectItem`(){
        val item= ProjectModelClass("url", "website", "goodwebsite", "someurl")
        assertEquals(item.name,"website")
        assertEquals(item.icon_url,"url")
        assertEquals(item.description,"goodwebsite")
        assertEquals(item.service_url,"someurl")
    }
}