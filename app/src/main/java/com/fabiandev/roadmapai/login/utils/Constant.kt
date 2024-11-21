package com.fabiandev.roadmapai.login.utils

class Constant {
    companion object {
        val emailRegex = "^[A-Za-z0-9+_.-]+@(.+)\$".toRegex()
        val minLength = 8
        val passwordRegex = """^(?=.*[0-9])(?=.*[!@#\$%^&*(),.?":{}|<>])[A-Za-z0-9!@#\$%^&*(),.?":{}|<>]{${minLength},}$""".toRegex()

    }

}