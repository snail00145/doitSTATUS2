package com.ianJung.doItStatus.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ianJung.doItStatus.ui.fragment.ShopFragment
import com.ianJung.doItStatus.ui.fragment.StatFragment
import com.ianJung.doItStatus.ui.fragment.TodoListFragment

class ViewPagerAdapter (fragment : FragmentActivity) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TodoListFragment()
            1 -> ShopFragment()
            else -> StatFragment()
        }
    }
}