package com.ianJung.doItStatus.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.database.FirebaseDatabase
import com.ianJung.doItStatus.R
import com.ianJung.doItStatus.adapter.ViewPagerAdapter
import com.ianJung.doItStatus.databinding.ActivityMainBinding
import com.ianJung.doItStatus.databinding.FragmentStatBinding
import com.ianJung.doItStatus.sharedpre.App.Companion.prefs

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var lastTime = prefs.getLastTime()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 뷰 바인딩
        binding = ActivityMainBinding.inflate(layoutInflater)
        var binding2 = FragmentStatBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var currentTime = System.currentTimeMillis()
        if (lastTime != 0L) {
            val timeDif = currentTime - lastTime
            if (timeDif >= 86400000) { //하루이상 86400000
                binding2.imageView2.setImageResource(R.drawable.gold_medal)
            }
        }
        lastTime = currentTime
            // 탭 설정
            binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    // 탭이 선택 되었을 때
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    // 탭이 선택되지 않은 상태로 변경 되었을 때
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    // 이미 선택된 탭이 다시 선택 되었을 때
                }
            })

            // 뷰페이저에 어댑터 연결
            binding.pager.adapter = ViewPagerAdapter(this)

            // 탭과 뷰페이저 연결
            TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
                when (position) {
                    0 -> tab.text = "TodoList"
                    1 -> tab.text = "Shop"
                    2 -> tab.text = "Stat"
                }
            }.attach()

    }
}