package com.github.avanlex.fundamentalsassignments.domain

import com.github.avanlex.fundamentalsassignments.R
import com.github.avanlex.fundamentalsassignments.data.models.Actor
import com.github.avanlex.fundamentalsassignments.data.models.Movie

class MoviesDataSource {
    fun getList(): List<Movie> {
        return listOf(
            Movie(
                "Avengers: Endgame", 2019,
                "Action, Advenrure, Fantasy",
                137, "13+", 4, 129,
                "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos\' actions and restore balance to the universe.",
                R.drawable.poster_avengers_endgame,
                listOf(
                    Actor("Robert Downey Jr.", R.drawable.actor_downey),
                    Actor("Chris Evans", R.drawable.actor_evans),
                    Actor("Chris Hemsworth", R.drawable.actor_hemsworth),
                    Actor("Mark Ruffalo", R.drawable.actor_ruffalo),
                )
            ),
            Movie(
                "Tenet", 2020,
                "Action, Sci-Fi, Thriller",
                97, "16+", 4, 156,
                "Armed with only one word, Tenet, and fighting for the survival of the entire world, a Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.",
                R.drawable.poster_tenet,
                listOf(
                    Actor("Robert Downey Jr.", R.drawable.actor_downey),
                    Actor("Chris Evans", R.drawable.actor_evans),
                    Actor("Chris Hemsworth", R.drawable.actor_hemsworth),
                    Actor("Mark Ruffalo", R.drawable.actor_ruffalo),
                )
            ),
            Movie(
                "Black Widow", 2021,
                "Action, Adventure, Sci-Fi",
                102, "13+", 5, 68,
                "A film about Natasha Romanoff in her quests between the films Civil War and Infinity War.",
                R.drawable.poster_black_widow,
                listOf(
                    Actor("Robert Downey Jr.", R.drawable.actor_downey),
                    Actor("Chris Evans", R.drawable.actor_evans),
                    Actor("Chris Hemsworth", R.drawable.actor_hemsworth),
                    Actor("Mark Ruffalo", R.drawable.actor_ruffalo),
                )
            ),
            Movie(
                "Wonder Woman 1984", 2020,
                "Action, Advenrure, Fantasy",
                120, "13+", 4, 54,
                "Fast forward to the 1980s as Wonder Woman's next big screen adventure finds her facing two all-new foes: Max Lord and The Cheetah.",
                R.drawable.poster_wonder_woman_1984,
                listOf(
                    Actor("Robert Downey Jr.", R.drawable.actor_downey),
                    Actor("Chris Evans", R.drawable.actor_evans),
                    Actor("Chris Hemsworth", R.drawable.actor_hemsworth),
                    Actor("Mark Ruffalo", R.drawable.actor_ruffalo),
                )
            ),
            Movie(
                "Morbius", 2021,
                "Action, Advenrure, Sci-Fi",
                130, "16+", 5, 850,
                "Biochemist Michael Morbius tries to cure himself of a rare blood disease, but he inadvertently infects himself with a form of vampirism instead.",
                R.drawable.poster_morbius,
                listOf(
                    Actor("Jared Leto", R.drawable.actor_jared_leto),
                    Actor("Matt Smith", R.drawable.actor_matt_smith),
                    Actor("Jared Harris", R.drawable.actor_jared_harris),
                    Actor("Tyrese Gibson", R.drawable.actor_tyrese_gibson),
                )
            ),
            Movie(
                "Doctor Strange and the Multiverse of Madness", 2022,
                "Action, Advenrure, Fantasy",
                125, "16+", 4, 354,
                "Plot unknown. Sequel to the 2016 Marvel film 'Doctor Strange'.",
                R.drawable.poster_doctor_strange_in_the_multiverse_of_madness,
                listOf(
                    Actor("Robert Downey Jr.", R.drawable.actor_downey),
                    Actor("Chris Evans", R.drawable.actor_evans),
                    Actor("Chris Hemsworth", R.drawable.actor_hemsworth),
                    Actor("Mark Ruffalo", R.drawable.actor_ruffalo),
                )
            ),
        )
    }
}