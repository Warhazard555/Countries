package com.example.task1.di.dagger.module

import android.content.Context
import com.example.data.model.TransformNewsDataToNewsDto
import com.example.data.repository.network.NetworkNewsRepositoryImpl
import com.example.data.retrofit.NewsRetrofitInterface
import com.example.domain.repository.NetworkNewsRepository
import com.example.task1.base.mvi.RootBaseFragment
import com.example.task1.di.dagger.common.AppRouter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule constructor(private val fragment: RootBaseFragment){

    @Provides
    fun providesFragmentContext(): Context = fragment.requireContext()

    @Provides
    fun providesFragment(): RootBaseFragment {
        return fragment
    }

    @Provides
    fun providesRouterComponent(): AppRouter = AppRouter(fragment)

    @Provides
    fun providesTransformer(): TransformNewsDataToNewsDto = TransformNewsDataToNewsDto()

    @Provides
    fun providesCharactersManager(
        apiService: NewsRetrofitInterface,
        transformer: TransformNewsDataToNewsDto
    ): NetworkNewsRepository {
        return NetworkNewsRepositoryImpl(apiService, transformer)
    }
}