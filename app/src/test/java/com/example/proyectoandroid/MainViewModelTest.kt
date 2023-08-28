package com.example.proyectoandroid

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.proyectoandroid.view.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.Rule

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class MainViewModelTest {
    private lateinit var viewModel: MainViewModel

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = MainViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun mainviewmodel_InitialValueCheck() = runTest {
        assertEquals(viewModel.getCompareResult().value, null)
        assertEquals(viewModel.getFirstField().value, null)
        assertEquals(viewModel.getSecondField().value, null)
    }

    @Test
    fun mainviewmodel_UnequalStringsCheck() = runTest {
        launch {
            viewModel.setFirstField("abc")
            viewModel.setSecondField("cba")
            viewModel.compare()
        }
        advanceUntilIdle()
        assertEquals(viewModel.getCompareResult().value, false)
        assertEquals(viewModel.getFirstField().value, "abc")
        assertEquals(viewModel.getSecondField().value, "cba")
    }

    @Test
    fun mainviewmodel_EqualStringsCheck() = runTest {
        launch {
            viewModel.setFirstField("abc")
            viewModel.setSecondField("abc")
            viewModel.compare()
        }
        advanceUntilIdle()
        assertEquals(viewModel.getCompareResult().value, true)
        assertEquals(viewModel.getFirstField().value, "abc")
        assertEquals(viewModel.getSecondField().value, "abc")
    }

    @Test
    fun mainviewmodel_EqualToUnequalStringsCheck() = runTest {
        launch {
            viewModel.setFirstField("abc")
            viewModel.setSecondField("abc")
            viewModel.compare()
        }
        advanceUntilIdle()
        assertEquals(viewModel.getCompareResult().value, true)
        assertEquals(viewModel.getFirstField().value, "abc")
        assertEquals(viewModel.getSecondField().value, "abc")
        launch {
            viewModel.setSecondField("def")
            viewModel.compare()
        }
        advanceUntilIdle()
        assertEquals(viewModel.getCompareResult().value, false)
        assertEquals(viewModel.getFirstField().value, "abc")
        assertEquals(viewModel.getSecondField().value, "def")
    }

    @Test
    fun mainviewmodel_UnequalToEqualStringsCheck() = runTest {
        launch {
            viewModel.setFirstField("abc")
            viewModel.setSecondField("def")
            viewModel.compare()
        }
        advanceUntilIdle()
        assertEquals(viewModel.getCompareResult().value, false)
        assertEquals(viewModel.getFirstField().value, "abc")
        assertEquals(viewModel.getSecondField().value, "def")
        launch {
            viewModel.setSecondField("abc")
            viewModel.compare()
        }
        advanceUntilIdle()
        assertEquals(viewModel.getCompareResult().value, true)
        assertEquals(viewModel.getFirstField().value, "abc")
        assertEquals(viewModel.getSecondField().value, "abc")
    }
}