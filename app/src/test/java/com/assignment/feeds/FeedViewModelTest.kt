package com.assignment.feeds

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import com.assignment.feeds.data.remote.db.dao.RowDataDao
import com.assignment.feeds.ui.search.FeedViewModel
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.test.KoinTest
import org.mockito.Mockito
import org.koin.standalone.get

class FeedViewModelTest : KoinTest {
    private lateinit var viewModel: FeedViewModel

    @Before fun before() {
        startKoin(listOf(testApiModule))

        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) = runnable.run()
            override fun isMainThread(): Boolean  = true
            override fun postToMainThread(runnable: Runnable) = runnable.run()
        })

        viewModel = FeedViewModel(get(), Mockito.mock(RowDataDao::class.java))
    }

    @After fun after() {
        ArchTaskExecutor.getInstance().setDelegate(null)
        stopKoin()
    }

    @Test fun viewModelNotNull() {
        assertThat(viewModel, notNullValue())
    }
}