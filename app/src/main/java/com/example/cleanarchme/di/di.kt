package com.example.cleanarchme.di

import android.app.Application
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import com.example.cleanarchme.R
import com.example.cleanarchme.data.*
import com.example.cleanarchme.data.database.LocalDataSourceImpl
import com.example.cleanarchme.data.database.MovieDataBase
import com.example.cleanarchme.data.server.RemoteDataSourceImpl1
import com.example.cleanarchme.data.server.Retrofit
import com.example.cleanarchme.views.detail.DetailActivity
import com.example.cleanarchme.views.detail.DetailContract
import com.example.cleanarchme.views.detail.DetailPresenterImpl
import com.example.cleanarchme.views.login.ContractLogin
import com.example.cleanarchme.views.login.PresenterImpl
import com.example.cleanarchme.views.login.LoginActivity
import com.example.cleanarchme.views.main.MainActivity
import com.example.cleanarchme.views.main.MainContract
import com.example.cleanarchme.views.main.MainPresenterImpl
import com.example.data.PermissionChecker
import com.example.data.UserPreferences
import com.example.data.auth.Auth
import com.example.data.auth.AuthValidator
import com.example.data.repository.movie.MovieRepository
import com.example.data.repository.movie.MoviesRepository
import com.example.data.repository.region.RegionRepository
import com.example.data.repository.region.RegionsRepository
import com.example.data.repository.user.UserRepository
import com.example.data.repository.user.UsersRepository
import com.example.data.source.LocalDataSource
import com.example.data.source.LocationDataSource
import com.example.data.source.RemoteDataSource
import com.example.usecases.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, scopesModule))
    }
}

private val appModule = module {
    single(named("apiKey")) { androidApplication().getString(R.string.api_key) }
    single(named("baseUrl")) { "https://api.themoviedb.org/3/" }
    single { MovieDataBase.build(get()).movieDao() }
    factory<UserPreferences> { UserPreferencesImpl(get()) }
    factory<Auth> { (fragAct: FragmentActivity, callback: BiometricPrompt.AuthenticationCallback) ->
        AuthImpl(fragAct, callback)
    }
    factory<AuthValidator> { AuthValidatorImpl(get()) }
    factory<LocalDataSource> { LocalDataSourceImpl(get()) }
    factory<RemoteDataSource> { RemoteDataSourceImpl1(Retrofit(get(named("baseUrl"))).service) }
    factory<LocationDataSource> { PlayServicesLocationDataSource(androidApplication()) }
    factory<PermissionChecker> { AndroidPermissionChecker(androidApplication()) }
    single<CoroutineDispatcher> { Dispatchers.Main }
}

val dataModule = module {
    factory<RegionRepository> { RegionsRepository(get(), get()) }
    factory<MovieRepository> {
        MoviesRepository(
            get(),
            get(),
            get(named("apiKey")),
            get()
        )
    }
    factory<UserRepository> {
        UsersRepository(
            get(),
            get(),
            get()
        )
    }
}

private val scopesModule = module {
    scope(named<MainActivity>()) {

        scoped<MainContract.MainPresenter> {
            MainPresenterImpl(GetMovies(get()), get())
        }
    }

    scope(named<DetailActivity>()) {
        scoped<DetailContract.DetailPresenter> { (id: Int) ->
            DetailPresenterImpl(GetMovieById(get()), ToggleMovieFavorite(get()), id, get())
        }
    }

    scope(named<LoginActivity>()) {
        scoped<ContractLogin.Presenter> {
            PresenterImpl(
                Login(get()),
                GetUser(get()),
                ToggleFingerPrint(get()),
                GetAuthMethod(get()),
                SupportBiometrics(get()),
                get()
            )
        }
    }
}