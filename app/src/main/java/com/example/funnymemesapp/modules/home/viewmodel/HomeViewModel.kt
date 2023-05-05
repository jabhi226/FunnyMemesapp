package com.example.funnymemesapp.modules.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.funnymemesapp.db.memedb.daos.MemesDao
import com.example.funnymemesapp.modules.core.models.CommonResponse
import com.example.funnymemesapp.modules.home.adapters.MemesPagingSource
import com.example.funnymemesapp.modules.home.models.network.Memes
import com.example.funnymemesapp.modules.home.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val generalRepository: HomeRepository,
    private val memesDao: MemesDao,
    private val coroutineExceptionHandler: CoroutineExceptionHandler
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<HomeUiEvents>()
    val uiEvent = _uiEvent.asSharedFlow()

    var currentMemeList: Flow<PagingData<Memes>>? = null
    val queryStateFlow = MutableStateFlow(0)

    init {
    }

    fun getDataFromApi(isNew: Boolean) {
        val psf = MemesPagingSource(generalRepository, isNew)
        currentMemeList = Pager(
            config = PagingConfig(pageSize = 5, enablePlaceholders = false),
            pagingSourceFactory = { psf }
        ).flow
    }

    fun getDataFromSavedDb() {

    }

    fun saveMeme(memes: Memes) {
        CoroutineScope(Dispatchers.IO).launch {
            _uiEvent.emit(HomeUiEvents.OnMemeSaved(generalRepository.saveMeme(memes)))
        }
    }

    sealed class HomeUiEvents {
        data class OnGetMedicineSuccess(val data: Flow<PagingData<Memes>>?) : HomeUiEvents()
        data class OnCommonError(val error: String) : HomeUiEvents()
        data class OnMemeSaved(val res: CommonResponse<String>) : HomeUiEvents()
    }

}