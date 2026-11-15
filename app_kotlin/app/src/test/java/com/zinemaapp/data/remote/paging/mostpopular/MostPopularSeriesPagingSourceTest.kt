package com.zinemaapp.data.remote.paging.mostpopular

import androidx.paging.PagingSource
import com.zinemaapp.data.remote.datasource.tvserie.TvSerieDataSource
import com.zinemaapp.data.remote.dto.series.TvSerieDTO
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MostPopularSeriesPagingSourceTest {
    private lateinit var dataSource: FakeTvSerieDataSource
    private lateinit var pagingSource: MostPopularSeriesPagingSource

    @Before
    fun prepareTest() {
        dataSource = FakeTvSerieDataSource()
        pagingSource = MostPopularSeriesPagingSource(dataSource)
    }

    @Test
    fun firstPageWithResults_returnsDataAndNextKey() {
        dataSource.response = listOf(createTvSerieDTO())

        val params = PagingSource.LoadParams.Refresh<Int>(
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
        assertEquals(1, page.data.first().id)
        assertEquals("Serie de prueba", page.data.first().name)
        assertNull(page.prevKey)
        assertEquals(2, page.nextKey)
        assertEquals(1, dataSource.requestedPage)
    }

    private fun createTvSerieDTO(): TvSerieDTO {
        return TvSerieDTO(
            id = 1,
            name = "Serie de prueba",
            originCountry = listOf("ES"),
            overview = "Sinopsis",
            poster = "/poster.jpg",
            rating = 8.0,
            firstAireDate = "2026-01-01",
            genres = emptyList(),
            actors = emptyList()
        )
    }

    private class FakeTvSerieDataSource : TvSerieDataSource {
        var response: List<TvSerieDTO> = emptyList()
        var requestedPage: Int? = null

        override suspend fun getMostPopular(page: Int): List<TvSerieDTO> {
            requestedPage = page
            return response
        }

        override suspend fun getTopRated(page: Int): List<TvSerieDTO> {
            throw UnsupportedOperationException()
        }

        override suspend fun getSeriesByGenre(
            genreId: Int,
            page: Int
        ): List<TvSerieDTO> {
            throw UnsupportedOperationException()
        }

        override suspend fun getById(id: Int): TvSerieDTO {
            throw UnsupportedOperationException()
        }
    }
}