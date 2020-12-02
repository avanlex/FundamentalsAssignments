package com.github.avanlex.fundamentalsassignments.domain

import com.github.avanlex.fundamentalsassignments.data.models.Movie

class MoviesDataSource {
    fun getList(): List<Movie> {
        return listOf(
            Movie(
                "Avengers: Endgame", 2019, 137, "13+", 4, 129,
                "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos\' actions and restore balance to the universe.",
                "https://avatars.mds.yandex.net/get-kinopoisk-image/1600647/ae22f153-9715-41bb-adb4-f648b3e16092/600x900"
            ),
            Movie(
                "Tenet", 2020, 97, "16+", 4, 156,
                "Armed with only one word, Tenet, and fighting for the survival of the entire world, a Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.",
                "https://ordu.net.tr/uploads/sinema/2020/08/tenet.jpg"
            ),
            Movie(
                "Black Widow", 2021, 102, "13+", 5, 68,
                "A film about Natasha Romanoff in her quests between the films Civil War and Infinity War.",
                "https://pbs.twimg.com/media/EixRtYEWoAgNzQP.jpg"
            ),
            Movie(
                "Wonder Woman 1984", 2020, 120, "13+", 4, 54,
                "Fast forward to the 1980s as Wonder Woman's next big screen adventure finds her facing two all-new foes: Max Lord and The Cheetah.",
                "https://www.cervenykoberec.cz/wp-content/uploads/2019/06/06/wonder_woman_1984_poster_01-768x1138.jpg"
            ),
            Movie(
                "Morbius", 2021, 130, "16+", 5, 850,
                "Biochemist Michael Morbius tries to cure himself of a rare blood disease, but he inadvertently infects himself with a form of vampirism instead.",
                "https://images92.fotosik.pl/338/031d1d10a7949caa.jpg"
            ),
            Movie(
                "Doctor Strange and the Multiverse of Madness", 2022, 125, "16+", 4, 354,
                "Plot unknown. Sequel to the 2016 Marvel film 'Doctor Strange'.",
                "https://pbs.twimg.com/media/EM64KrAXUAAPaml.jpg"
            ),
            )
    }
}
