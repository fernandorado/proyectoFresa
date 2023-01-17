package com.example.fresaproyecto.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fresaproyecto.fragments.CosechaCultivoFragment
import com.example.fresaproyecto.fragments.InsumoCultivoFragment
import com.example.fresaproyecto.fragments.JornalCultivoFragment

class MyViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                return JornalCultivoFragment()

            }
            1 -> {
                return InsumoCultivoFragment()

            }
            2 -> {
                return CosechaCultivoFragment()

            }
            else -> { return JornalCultivoFragment()

            }
        }
    }
}