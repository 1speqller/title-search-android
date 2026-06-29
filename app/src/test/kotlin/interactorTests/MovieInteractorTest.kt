package interactorTests

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertThrows
import org.junit.Test
import shared.domain.interactor.MovieInteractorImpl
import shared.domain.model.DetailMovieModel
import shared.domain.model.ImageModel
import shared.domain.model.MovieModel
import shared.domain.repository.MovieRepository
import java.io.IOException
import kotlin.collections.listOf

class MovieInteractorTest {
    private val repository = mockk<MovieRepository>()
    private var interactor = MovieInteractorImpl(repository)

    @Test
    fun `searchMovies() should call repository with correct query`() = runBlocking {
        val expectedResult = listOf(
            MovieModel(
                id = 1,
                name = "Breaking bad",
                summary = "...",
                image = ImageModel(),
                genres = listOf()
            )
        )
        coEvery { repository.searchMovies("Breaking bad") } returns expectedResult

        val result = interactor.searchMovies("Breaking bad")

        coVerify { repository.searchMovies("Breaking bad") }
        assertEquals(expectedResult, result)
    }

    @Test
    fun `searchDetailsMovie() should call repository with correct query`() = runBlocking {
        val expectedResult = DetailMovieModel(
            id = 1,
            name = "Breaking bad",
            description = "...",
            image = ImageModel(),
            status = "",
            startAt = "2002-11-23",
            endAt = "2010-02-13"

        )

        coEvery { repository.searchDetailMovie(1) } returns expectedResult

        val result = interactor.searchDetailMovie(1)

        coVerify { repository.searchDetailMovie(1) }
        assertEquals(expectedResult, result)
    }

    @Test
    fun `searchDetailMovie() should throw exception when repository fails`() = runBlocking {
        val exception = IOException("Network error")
        coEvery { repository.searchDetailMovie(1) } throws exception

        val thrown = assertThrows(IOException::class.java) {
            runBlocking { interactor.searchDetailMovie(1) }
        }

        assertEquals("Network error", thrown.message)
    }

    @Test
    fun `searchMovies() should throw exception when repository fails`() = runBlocking {
        val exception = IOException("Network error")
        coEvery { repository.searchMovies("Breaking Bad") } throws exception

        val thrown = assertThrows(IOException::class.java) {
            runBlocking { interactor.searchMovies("Breaking Bad") }
        }

        assertEquals("Network error", thrown.message)
    }
}