package com.example.bottomnav.presentation.di

import androidx.room.Room
import com.example.bottomnav.data.db.MovieBase
import com.example.bottomnav.data.repositories.CategoryRepository
import com.example.bottomnav.data.repositories.ProductRepository
import com.example.bottomnav.domain.repository.CategoriesCall
import com.example.bottomnav.domain.repository.ProductsCall
import com.example.bottomnav.domain.useCase.CategoriesUseCase
import com.example.bottomnav.domain.useCase.ProductsUseCase
import com.example.bottomnav.presentation.Tabs.categories.CategoryViewModel
import com.example.bottomnav.presentation.Tabs.products.ProductViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val moduleDBProd = module {
    single{
        Room.databaseBuilder(androidContext(), MovieBase::class.java,
            "dbProd").build()
    }

    single { get<MovieBase>().ProductDAO }
}


val moduleDBCa = module {
    single{
        Room.databaseBuilder(androidContext(), MovieBase::class.java,
        "dbCate").build()
    }

    single { get<MovieBase>().CategoryDAO }
}


val moduleProd = module {

    moduleDBProd

    single<ProductsCall> {
        ProductRepository(get())
    }

    single {
        ProductsUseCase(get())
    }

    viewModel {
        ProductViewModel(get())
    }

}


val moduleCa = module {

    moduleDBCa

    single<CategoriesCall> {
        CategoryRepository(get())
    }

    single{
        CategoriesUseCase(get())
    }

    viewModel { CategoryViewModel(get()) }
}