package com.algebra.rxsum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class ReactiveActivity : AppCompatActivity( ) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_main )

        etText
            .textChanges( )
            .skip( 1 )
            .map { it.toString( ) }
            .map { it.split( "" ) }
            //.map { it.map{ if( it=="" || !it.isDigitsOnly( ) ) 0 else it.toInt( ) }.sum( ) }
            .map { it.map{ if( it.isEmpty( ) ) 0 else it.toInt( ) }.sum( ) }
            .debounce( 800, TimeUnit.MILLISECONDS )
            .observeOn( AndroidSchedulers.mainThread( ) )
            .doOnError { Toast.makeText( this, "Error while summing", Toast.LENGTH_SHORT ).show( ) }
            .retry( )
            .subscribe {
                tvRezultat.setText( "$it" )
            }
    }
}