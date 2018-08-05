package br.com.dextra

import android.app.Application
import android.content.Context
import br.com.dextra.api.Api
import br.com.dextra.api.IApi
import br.com.dextra.api.IRoute
import br.com.dextra.api.RetrofitService
import br.com.dextra.repository.RepositorySandwiches
import br.com.dextra.repository.IRepositorySandwiches
import java.lang.ref.WeakReference

class App : Application() {

    companion object {
        private lateinit var route: IRoute
        private lateinit var api: IApi
        private lateinit var repository: IRepositorySandwiches.Presenter
        private lateinit var context: WeakReference<Context>
        fun injectRepository() = repository
        fun context() = context.get()
    }

    override fun onCreate() {
        super.onCreate()
        route = RetrofitService.get(IRoute::class)
        api = Api(route)
        repository = RepositorySandwiches(api)
        context = WeakReference(applicationContext)
    }
}