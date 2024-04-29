package com.anangkur.synrgychapter3.activity.navigationcomponent.fragment.second

import com.anangkur.synrgychapter3.activity.navigationcomponent.fragment.second.data.Movie
import org.junit.Assert
import org.junit.Test

class SecondNavigationViewModelTest {

    private val secondNavigationViewModel = SecondNavigationViewModel()

    @Test
    fun retrieveMovieDataTest() {
        // given
        val expected = listOf(
            Movie(
                image = "https://media.themoviedb.org/t/p/w440_and_h660_face/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg",
                title = "Dune: Part Two",
                description = "Follow the mythic journey of Paul Atreides as he unites with Chani and the Fremen while on a path of revenge against the conspirators who destroyed his family. Facing a choice between the love of his life and the fate of the known universe, Paul endeavors to prevent a terrible future only he can foresee."
            ),
            Movie(
                image = "https://media.themoviedb.org/t/p/w440_and_h660_face/AnsSKR9LuK0T9bAOcPVA3PUvyWj.jpg",
                title = "Fallout",
                description = ""
            ),
            Movie(
                image = "https://media.themoviedb.org/t/p/w440_and_h660_face/fdZpvODTX5wwkD0ikZNaClE4AoW.jpg",
                title = "Immaculate",
                description = ""
            ),
            Movie(
                image = "https://media.themoviedb.org/t/p/w440_and_h660_face/d5NXSklXo0qyIYkgV94XAgMIckC.jpg",
                title = "Dune",
                description = "Paul Atreides, a brilliant and gifted young man born into a great destiny beyond his understanding, must travel to the most dangerous planet in the universe to ensure the future of his family and his people. As malevolent forces explode into conflict over the planet's exclusive supply of the most precious resource in existence-a commodity capable of unlocking humanity's greatest potential-only those who can conquer their fear will survive."
            ),
            Movie(
                image = "https://media.themoviedb.org/t/p/w440_and_h660_face/7O4iVfOMQmdCSxhOg1WnzG1AgYT.jpg",
                title = "Shōgun",
                description = ""
            ),
            Movie(
                image = "https://media.themoviedb.org/t/p/w440_and_h660_face/sh7Rg8Er3tFcN9BpKIPOMvALgZd.jpg",
                title = "Civil War",
                description = ""
            ),
            Movie(
                image = "https://media.themoviedb.org/t/p/w440_and_h660_face/zAxObqiOEooIuQtH338b8zOaFEu.jpg",
                title = "The Sympathizer",
                description = ""
            ),
            Movie(
                image = "https://media.themoviedb.org/t/p/w440_and_h660_face/v9sk7CPhDXJKSkQIegVSBQ5nJnV.jpg",
                title = "Kung Fu Panda 4",
                description = "Po is gearing up to become the spiritual leader of his Valley of Peace, but also needs someone to take his place as Dragon Warrior. As such, he will train a new kung fu practitioner for the spot and will encounter a villain called the Chameleon who conjures villains from the past. movie HD QUALITY, open this link leakedcinema.com"
            ),
            Movie(
                image = "https://media.themoviedb.org/t/p/w440_and_h660_face/6faYaQyiBPhqAizldJKq21mIVaE.jpg",
                title = "Ghostbusters: Frozen Empire",
                description = ""
            ),
            Movie(
                image = "https://media.themoviedb.org/t/p/w440_and_h660_face/8BYT4D0E0f1qFb9WfJPH4YUirL.jpg",
                title = "Woody Woodpecker Goes to Camp",
                description = ""
            )
        )
        // when
        val actual = secondNavigationViewModel.retrieveMovieData()

        // then
        Assert.assertEquals(expected, actual)
    }
}