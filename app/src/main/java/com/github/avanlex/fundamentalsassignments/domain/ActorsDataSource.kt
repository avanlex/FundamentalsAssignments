package com.github.avanlex.fundamentalsassignments.domain

import com.github.avanlex.fundamentalsassignments.data.models.Actor

class ActorsDataSource {
    fun getList(): List<Actor> {
        return listOf(
            Actor("Alicia Vikander", "https://image.ibb.co/nKNBrd/Alicia_Vikander.jpg"),
            Actor("Amanda Seyfried", "https://image.ibb.co/j142xJ/Amanda_Seyfried.jpg"),
            Actor("Anne Hathaway", "https://image.ibb.co/euy6Py/Anne_Hathaway.jpg"),
            Actor("Emma Stone", "https://image.ibb.co/dJY6Py/Emma_Stone.jpg"),
            Actor("Keira Knightley", "https://image.ibb.co/cxX0jy/Keira_Knightley.jpg"),
            Actor("George Clooney", "https://image.ibb.co/ce1t4y/George_Clooney.jpg"),
            Actor("Lucy Liu", "https://image.ibb.co/dWurrd/Lucy_Liu.jpg"),
            Actor("Matthew McConaughey", "https://image.ibb.co/e3JHWd/Matthew_Mc_Conaughey.jpg"),
            Actor("Morgan Freeman", "https://image.ibb.co/h9GhxJ/Morgan_Freeman.jpg"),
            Actor("Ryan Gosling", "https://image.ibb.co/buLLjy/Ryan_Gosling.jpg"),
            Actor("Will Smith", "https://image.ibb.co/gxoUcJ/Will_Smith.jpg"),
            Actor("Robert de Niro", "https://image.ibb.co/e6T6Py/Robert_de_Niro.jpg"),
            Actor("Zoe Saldana", "https://image.ibb.co/i9WRPy/Zoe_Saldana.jpg")
        )
    }
}