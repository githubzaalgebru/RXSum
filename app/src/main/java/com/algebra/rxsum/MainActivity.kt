package com.algebra.rxsum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.text.isDigitsOnly
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity( ) {

    private val TAG = "MainActivity"

    override fun onCreate( savedInstanceState : Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_main )

        watchForEditTextChanges( )
    }

    private fun watchForEditTextChanges( ) {
        etText.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged( s: CharSequence?, start : Int, count : Int, after: Int ) { }
                override fun afterTextChanged( p0: Editable? ) { }
                override fun onTextChanged( s:CharSequence, start:Int, before:Int, count:Int ) {
                    val x = s.split( "" )
                                .filter{ it!="" && it.isDigitsOnly( ) }
                                .map{ it.toInt( ) }
                                .sum( )

                    tvRezultat.setText( "$x" )
                }
        } )
    }

    override fun onCreateOptionsMenu( menu: Menu? ): Boolean {
        menuInflater.inflate( R.menu.menu, menu )
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if( item.itemId==R.id.reaktivno ) {
            startActivity( Intent( this, ReactiveActivity::class.java ) )
        }
        return true
    }
}