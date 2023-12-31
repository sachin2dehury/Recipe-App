package github.sachin2dehury.recipeapp

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import github.sachin2dehury.recipeapp.data.RecipeService
import github.sachin2dehury.recipeapp.data.listing.RecipeListingRepository
import github.sachin2dehury.recipeapp.data.listing.RecipeListingRepositoryImpl
import github.sachin2dehury.recipeapp.domain.listing.RecipeFilterUseCase
import github.sachin2dehury.recipeapp.domain.listing.RecipeListingUseCase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesOkHttp() = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun providesMoshi() = Moshi.Builder().build()

    @Provides
    @Singleton
    fun providesMoshiConverterFactory(moshi: Moshi) = MoshiConverterFactory.create(moshi)

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, moshiConverterFactory: MoshiConverterFactory) =
        Retrofit.Builder()
            .addConverterFactory(moshiConverterFactory)
            .client(okHttpClient)
            .baseUrl(RecipeService.BASE_URL)
            .build()

    @Provides
    @Singleton
    fun providesRecipeService(retrofit: Retrofit) = retrofit.create(RecipeService::class.java)

    @Provides
    @Singleton
    fun providesRecipeListingRepository(service: RecipeService): RecipeListingRepository =
        RecipeListingRepositoryImpl(service)

    @Provides
    @Singleton
    fun providesRecipeListingUseCase(repository: RecipeListingRepository) =
        RecipeListingUseCase(repository)

    @Provides
    @Singleton
    fun providesRecipeFilterUseCase(repository: RecipeListingRepository) =
        RecipeFilterUseCase(repository)
}
