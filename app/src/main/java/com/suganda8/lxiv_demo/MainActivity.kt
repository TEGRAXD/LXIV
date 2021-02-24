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

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.suganda8.lxiv_demo.databinding.ActivityMainBinding
import kotlinx.coroutines.*
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
        setUpTransitionBNV()

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

    private fun setUpTransitionBNV() {
        // Setup Enter, Exit, Pop Enter, and Pop Exit in NavOptions.Builder to override later.
        val navOptions = NavOptions.Builder().apply {
            setEnterAnim(R.anim.nav_default_enter_anim)
            setExitAnim(R.anim.nav_default_exit_anim)
            setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
            setPopExitAnim(R.anim.nav_default_pop_exit_anim)
        }.build()

        // Java using NavigationUI directly
        // NavigationUI.setupWithNavController(binding.bnvMainBottomNavigationAcMain, navController)

        // Kotlin using BottomNavigationView and calling setUpWithNavController
        binding.bnvMainBottomNavigationAcMain.setupWithNavController(navController)

        // Overriding transition
        binding.bnvMainBottomNavigationAcMain.setOnNavigationItemSelectedListener { item ->
            if (binding.bnvMainBottomNavigationAcMain.selectedItemId == item.itemId) {
                // Refreshing page
            } else {
                if (navController.graph.findNode(item.itemId) != null) {
                    navController.navigate(item.itemId, null, navOptions)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }
}