package com.github.avanlex.fundamentalsassignments.domain

import com.github.avanlex.fundamentalsassignments.data.models.Actor
import com.github.avanlex.fundamentalsassignments.data.models.Movie


class MoviesDataSource {
    fun getList(): List<Movie> {
        return listOf(
            Movie(
                "Avengers: Endgame", 2019, 137, "13+", 4, 129,
                "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos\' actions and restore balance to the universe.",
                "https://avatars.mds.yandex.net/get-kinopoisk-image/1600647/ae22f153-9715-41bb-adb4-f648b3e16092/600x900",
                listOf(
                    Actor("Alicia Vikander", "https://image.ibb.co/nKNBrd/Alicia_Vikander.jpg"),
                    Actor("Amanda Seyfried", "https://image.ibb.co/j142xJ/Amanda_Seyfried.jpg"),
                    Actor("Anne Hathaway", "https://image.ibb.co/euy6Py/Anne_Hathaway.jpg"),
                    Actor("Emma Stone", "https://image.ibb.co/dJY6Py/Emma_Stone.jpg"),
                )
            ),
            Movie(
                "Tenet", 2020, 97, "16+", 4, 156,
                "Armed with only one word, Tenet, and fighting for the survival of the entire world, a Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.",
                "https://ordu.net.tr/uploads/sinema/2020/08/tenet.jpg",
                listOf(
                    Actor("Keira Knightley", "https://image.ibb.co/cxX0jy/Keira_Knightley.jpg"),
                    Actor("George Clooney", "https://image.ibb.co/ce1t4y/George_Clooney.jpg"),
                    Actor("Lucy Liu", "https://image.ibb.co/dWurrd/Lucy_Liu.jpg"),
                    Actor("Matthew McConaughey", "https://image.ibb.co/e3JHWd/Matthew_Mc_Conaughey.jpg"),
                )
            ),
            Movie(
                "Black Widow", 2021, 102, "13+", 5, 68,
                "A film about Natasha Romanoff in her quests between the films Civil War and Infinity War.",
                "https://pbs.twimg.com/media/EixRtYEWoAgNzQP.jpg",
                listOf(
                    Actor("Morgan Freeman", "https://image.ibb.co/h9GhxJ/Morgan_Freeman.jpg"),
                    Actor("Ryan Gosling", "https://image.ibb.co/buLLjy/Ryan_Gosling.jpg"),
                    Actor("Will Smith", "https://image.ibb.co/gxoUcJ/Will_Smith.jpg"),
                )
            ),
            Movie(
                "Wonder Woman 1984", 2020, 120, "13+", 4, 54,
                "Fast forward to the 1980s as Wonder Woman's next big screen adventure finds her facing two all-new foes: Max Lord and The Cheetah.",
                "https://www.cervenykoberec.cz/wp-content/uploads/2019/06/06/wonder_woman_1984_poster_01-768x1138.jpg",
                listOf(
                    Actor("Ryan Gosling", "https://image.ibb.co/buLLjy/Ryan_Gosling.jpg"),
                    Actor("Will Smith", "https://image.ibb.co/gxoUcJ/Will_Smith.jpg"),
                    Actor("Robert de Niro", "https://image.ibb.co/e6T6Py/Robert_de_Niro.jpg"),
                    Actor("Zoe Saldana", "https://image.ibb.co/i9WRPy/Zoe_Saldana.jpg")
                )
            ),
            Movie(
                "Morbius", 2021, 130, "16+", 5, 850,
                "Biochemist Michael Morbius tries to cure himself of a rare blood disease, but he inadvertently infects himself with a form of vampirism instead.",
                "https://images92.fotosik.pl/338/031d1d10a7949caa.jpg",
                listOf(
                    Actor("Anne Hathaway", "https://image.ibb.co/euy6Py/Anne_Hathaway.jpg"),
                    Actor("Emma Stone", "https://image.ibb.co/dJY6Py/Emma_Stone.jpg"),
                    Actor("Keira Knightley", "https://image.ibb.co/cxX0jy/Keira_Knightley.jpg"),
                    Actor("George Clooney", "https://image.ibb.co/ce1t4y/George_Clooney.jpg"),
                )
            ),
            Movie(
                "Doctor Strange and the Multiverse of Madness", 2022, 125, "16+", 4, 354,
                "Plot unknown. Sequel to the 2016 Marvel film 'Doctor Strange'.",
                "https://pbs.twimg.com/media/EM64KrAXUAAPaml.jpg",
                listOf(
                    Actor("George Clooney", "https://image.ibb.co/ce1t4y/George_Clooney.jpg"),
                    Actor("Lucy Liu", "https://image.ibb.co/dWurrd/Lucy_Liu.jpg"),
                    Actor("Matthew McConaughey", "https://image.ibb.co/e3JHWd/Matthew_Mc_Conaughey.jpg"),
                    Actor("Morgan Freeman", "https://image.ibb.co/h9GhxJ/Morgan_Freeman.jpg"),
                    Actor("Ryan Gosling", "https://image.ibb.co/buLLjy/Ryan_Gosling.jpg"),
                )
            ),
        )
    }
}
