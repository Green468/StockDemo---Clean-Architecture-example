package com.green.stockdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.green.domain.usecase.GetPortfolioListUseCase
import com.green.stockdemo.entity.PortfolioItemViewModel
import com.green.stockdemo.entity.PortfolioItemViewModelFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    val getPortfolioListUseCase: GetPortfolioListUseCase,
    val portfolioItemViewModelFactory: PortfolioItemViewModelFactory) : ViewModel() {
    val portfolioList = MutableLiveData<List<PortfolioItemViewModel>>()
    val compositeDisposable = CompositeDisposable()

    init {
        compositeDisposable.add(
            getPortfolioListUseCase.invoke()
                .map {
                    it.map {
                        portfolioItemViewModelFactory.create(it)
                    }
                }
                .subscribe {
                    portfolioList.value = it
                }
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}