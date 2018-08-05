package br.com.dextra.ui

import android.support.v7.app.AppCompatActivity
import br.com.dextra.App
import br.com.dextra.extension.toast

abstract class DextraBaseActivity : AppCompatActivity() {

    protected fun repository() = App.injectRepository()

    protected fun showMessageError(){
        toast("Ops! tivemos um problema com nosso servidor, tente novamente mais tarde")
    }
}