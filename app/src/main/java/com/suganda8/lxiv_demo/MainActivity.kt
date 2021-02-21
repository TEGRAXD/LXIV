/*
 *    Copyright 2021 Tegar Bangun Suganda, ASTARIA.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.suganda8.lxiv_demo

import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.suganda8.lxiv.DecoderBuilder
import com.suganda8.lxiv.LXIV
import com.suganda8.lxiv_demo.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNC()
        setUpUI()
    }

    private fun setUpNC() {
        navHostFragment = supportFragmentManager.findFragmentById(binding.fcvMainContainerAcMain.id) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun setUpUI() {
        NavigationUI.setupWithNavController(binding.bnvMainBottomNavigationAcMain, navController)

        // Setup Top Level Destination + ActionBar
        /*
            val appBarConfiguration = AppBarConfiguration(
                topLevelDestinationIds = setOf (
                    R.id.welcomeFragment,
                    R.id.encoderFragment,
                    R.id.decoderFragment
                )
            )

            NavigationUI.navigateUp(navHostFragment.navController, appBarConfiguration)
        */
    }
}