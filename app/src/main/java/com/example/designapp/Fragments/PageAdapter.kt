package com.example.designapp.Fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.designapp.Fragments.JewleryFragment
import com.example.designapp.Fragments.MenFragment
import com.example.designapp.Fragments.WomenFragment


class PageAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return MenFragment()
            1 -> return WomenFragment()
            2 -> return JewleryFragment()
        }
        return MenFragment()
    }

}