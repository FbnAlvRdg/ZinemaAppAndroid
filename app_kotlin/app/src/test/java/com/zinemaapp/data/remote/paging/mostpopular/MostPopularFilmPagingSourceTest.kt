package com.zinemaapp.data.remote.paging.mostpopular

import androidx.paging.PagingSource
import com.zinemaapp.data.remote.datasource.film.FilmDataSource
import com.zinemaapp.data.remote.dto.film.DetailsFilmDTO
import com.zinemaapp.data.remote.dto.film.FilmDTO
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

class MostPopularFilmPagingSourceTest {
    private lateinit var dataSource: FakeFilmDataSource
    private lateinit var pagingSource: MostPopularFilmPagingSource

    @Before
    fun prepareTest() {
        dataSource = FakeFilmDataSource()
        pagingSource = MostPopularFilmPagingSource((dataSource))
    }

    @Test
    fun firstPageWithResults_returnsDataAndNextKey() {
        dataSource.response = listOf(createFilmDTO())

        val params =
            PagingSource.LoadParams.Refresh<Int>(
                key = null,
                loadSize = 20,
                placeholdersEnabled = false
            )

        val result = runBlocking {
            pagingSource.load(params)
        }

        assertTrue(result is PagingSource.LoadResult.Page)

        val page = result as PagingSource.LoadResult.Page

        assertEquals(1, page.data.size)
        assertNull(page.prevKey)
        assertEquals(2, page.nextKey)
        assertEquals(1, dataSource.requestedPage)
    }

    @Test
    fun emptyResponse_returnsNullNextKey() {
        dataSource.response = emptyList()

        val params = PagingSource.LoadParams.Refresh<Int>(
            key = 2,
            loadSize = 20,
            placeholdersEnabled = false
        )

        val result = runBlocking {
            pagingSource.load(params)
        }

        val page = result as PagingSource.LoadResult.Page

        assertTrue(page.data.isEmpty())
        assertEquals(1, page.prevKey)
        assertNull(page.nextKey)
        assertEquals(2, dataSource.requestedPage)
    }

    @Test
    fun dataSourceError_returnsLoadResultError() {
        val expectedError = IOException("Error de conexión")
        dataSource.error = expectedError

        val params = PagingSource.LoadParams.Refresh<Int>(
            key = null,
            loadSize = 20,
            placeholdersEnabled = false
        )

        val result = runBlocking { pagingSource.load(params) }

        assertTrue(result is PagingSource.LoadResult.Error)

        val errorResult = result as PagingSource.LoadResult.Error

        assertEquals(expectedError, errorResult.throwable)
        assertEquals(1, dataSource.requestedPage)
    }

    @Test
    fun cancellation_isRethrown() {
        val expectedError = CancellationException("Carga cancelada")
        dataSource.error = expectedError

        val params = PagingSource.LoadParams.Refresh<Int>(
            key = null,
            loadSize = 20,
            placeholdersEnabled = false
        )

        val thrownError = assertThrows(CancellationException::class.java) {
            runBlocking {
                pagingSource.load(params)
            }
        }

        assertEquals(expectedError, thrownError)
    }

    private fun createFilmDTO(): FilmDTO {
        return FilmDTO(
            id = 1,
            title = "Película de prueba",
            originalTitle = "Test film",
            releaseDate = "2026-01-01",
            synopsis = "Sinopsis",
            poster = "/poster.jpg",
            rating = 8.0
        )
    }

    private class FakeFilmDataSource : FilmDataSource {
        var response: List<FilmDTO> = emptyList()
        var error: Exception? = null
        var requestedPage: Int? = null

        override suspend fun getPopularFilms(page: Int): List<FilmDTO> {
            requestedPage = page
            error?.let { throw it }

            return response
        }

        override suspend fun getTopRated(page: Int): List<FilmDTO> {
            throw UnsupportedOperationException()
        }

        override suspend fun getFilmById(id: Int): DetailsFilmDTO {
            throw UnsupportedOperationException()
        }

        override suspend fun getFilmByGenre(idGenre: Int, page: Int): List<FilmDTO> {
            throw UnsupportedOperationException()
        }
    }
}