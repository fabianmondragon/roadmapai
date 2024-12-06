package com.fabiandev.roadmapai.login.domain.util

sealed class RoadMapResult <out T> {
    data class Success<out T> (val data: T, val message: String): RoadMapResult<T>()
    data class Fail (val msg: String): RoadMapResult<Nothing>()
    data class Processing (val msg: String): RoadMapResult<Nothing>()

}