package com.deme.domain.di

import com.deme.domain.preferences.Preferences
import com.deme.domain.repository.TrackerRepo
import com.deme.domain.usecase.CalculateMealNutrients
import com.deme.domain.usecase.DeleteFood
import com.deme.domain.usecase.GetFoodsForDate
import com.deme.domain.usecase.SearchFood
import com.deme.domain.usecase.TrackFood
import com.deme.domain.usecase.TrackerUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @Provides
    @ViewModelScoped
    fun provideTrackerUseCases(
        trackerRepo: TrackerRepo,
        preferences: Preferences
    ): TrackerUseCases {
        return TrackerUseCases(
            trackFood = TrackFood(repository = trackerRepo),
            deleteFood = DeleteFood(repository = trackerRepo),
            searchFood = SearchFood(repository = trackerRepo),
            getFoodsForDate = GetFoodsForDate(repository = trackerRepo),
            calculateMealNutrients = CalculateMealNutrients(preferences = preferences)
        )
    }
}
