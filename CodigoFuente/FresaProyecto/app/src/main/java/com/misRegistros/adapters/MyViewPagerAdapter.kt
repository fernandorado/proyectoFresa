package com.misRegistros.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.misRegistros.fragments.CosechaCultivoFragment
import com.misRegistros.fragments.InsumoCultivoFragment
import com.misRegistros.fragments.JornalCultivoFragment

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